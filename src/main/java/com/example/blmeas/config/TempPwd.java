package com.example.blmeas.config;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TempPwd {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPwd = "qqqqqq"; // 你想要设置的明文密码

        // 1. 生成密文
        String encoded = encoder.encode(rawPwd);
        System.out.println("===== 生成的密文如下（请完整复制）=====");
        System.out.println(encoded);

        // 2. 顺便验证一下（输出应该是 true）
        System.out.println("===== 验证结果（应为true）=====");
        System.out.println(encoder.matches(rawPwd, encoded));
    }
}

