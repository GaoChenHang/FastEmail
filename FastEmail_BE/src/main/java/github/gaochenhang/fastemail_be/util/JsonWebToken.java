/*
 * Copyright (c) 2024 GaoChenHang.
 * This code is licensed under the Apache 2.0 open source license.
 * You can find usage information or restrictions at https://www.apache.org/licenses/LICENSE-2.0.html or in the project's LICENSE file.
 * GitHub https://github.com/GaoChenHang/FastEmail
 */

package github.gaochenhang.fastemail_be.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JsonWebToken {
    @Value("${fast.email.jwt.secret}")
    private String JWT_SECRET;

    public String getTokenByUserId(Object userId) {
        if (userId == null) {
            return null;
        }
        return JWT.create()
                .withClaim("userId", userId.toString())
                .withJWTId(UUID.randomUUID().toString())
                .sign(Algorithm.HMAC256(JWT_SECRET));


    }
}
