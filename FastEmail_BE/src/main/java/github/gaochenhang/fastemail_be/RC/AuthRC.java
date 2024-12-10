/*
 * Copyright (c) 2024 GaoChenHang.
 * This code is licensed under the Apache 2.0 open source license.
 * You can find usage information or restrictions at https://www.apache.org/licenses/LICENSE-2.0.html or in the project's LICENSE file.
 * GitHub https://github.com/GaoChenHang/FastEmail
 */

package github.gaochenhang.fastemail_be.RC;
import github.gaochenhang.fastemail_be.SHAUtil;
import github.gaochenhang.fastemail_be.entity.User;
import github.gaochenhang.fastemail_be.mapper.UserMapper;
import github.gaochenhang.fastemail_be.util.JsonWebToken;
import github.gaochenhang.fastemail_be.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Login and Register for rest api
 *
 * @author Gch
 * @create 2024/12/9
 */
@RestController
@RequestMapping("/auth")
public class AuthRC {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private JsonWebToken jsonWebToken;

    // 登录接口
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        // 根据用户名查询用户信息
        user.setPassword(SHAUtil.SHA256(user.getPassword()));
        String userPasswordByUsername = userMapper.getUserPasswordByUsername(user.getUsername());
        if (Objects.equals(userPasswordByUsername, user.getPassword())){
//            分发jwt秘钥
            String tokenByUserId = jsonWebToken.getTokenByUserId(user.getUserId());
            return ResponseEntity.ok(tokenByUserId);
        }
        return ResponseEntity.ok("Login Failed");
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        user.updatePasswordToSha256();
        user.setRole("user");
        user.setUserId(snowflakeIdWorker.nextId());
        if (!user.checkUser()){
            return ResponseEntity.badRequest().body("Invalid User");
        }
        userMapper.addNewUser(user);
        return ResponseEntity.ok("Register Success");
    }
}

