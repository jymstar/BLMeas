package com.example.blmeas.controller;

import com.example.blmeas.entity.SysUser;
import com.example.blmeas.mapper.SysUserMapper;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final SysUserMapper sysUserMapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public LoginController(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }
    // ===== 登录 =====
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> params, HttpServletRequest request) {
        String username = params.get("username");
        String password = params.get("password");

        Map<String, Object> result = new HashMap<>();

        // 1. 查数据库
        SysUser user = sysUserMapper.findByUsername(username);
        if (user == null) {
            result.put("success", false);
            result.put("msg", "用户不存在");
            return result;
        }

        // 2. 验证密码
        if (!encoder.matches(password, user.getPassword())) {
            result.put("success", false);
            result.put("msg", "密码错误");
            return result;
        }

        // 3. 检查审核状态
        if (user.getStatus() == 0) {
            result.put("success", false);
            result.put("msg", "账号待审核，请联系管理员");
            return result;
        }
        if (user.getStatus() == 2) {
            result.put("success", false);
            result.put("msg", "账号已被驳回，请联系管理员");
            return result;
        }

        // 4. 登录成功
        result.put("success", true);
        result.put("role", "user");
        result.put("username", username);
        request.getSession().setAttribute("user", username);
        request.getSession().setAttribute("userId", user.getUserId());

        return result;
    }

    // ===== 注册 =====
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();

        String username = params.get("username");
        String password = params.get("password");
        String realName = params.get("realName");
        String email = params.get("email");
        String phone = params.get("phone");

        // 检查是否已存在
        if (sysUserMapper.findByUsername(username) != null) {
            result.put("success", false);
            result.put("msg", "该账号已存在");
            return result;
        }

        // 加密密码 + 入库（status=0 待审核）
        SysUser user = new SysUser();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRealName(realName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setStatus(0);

        sysUserMapper.insertUser(user);

        result.put("success", true);
        result.put("msg", "注册申请已提交，请等待管理员审核");
        return result;
    }
}