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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // 登录接口
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        // 根据用户名查询用户信息
        user.setPassword(SHAUtil.SHA256(user.getPassword()));
        // 判断用户是否已经注册


        return null;

    }

    //todo:2.Register
}

