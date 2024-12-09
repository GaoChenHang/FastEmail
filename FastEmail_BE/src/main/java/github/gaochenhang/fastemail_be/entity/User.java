/*
 * Copyright (c) 2024 GaoChenHang.
 * This code is licensed under the Apache 2.0 open source license.
 * You can find usage information or restrictions at https://www.apache.org/licenses/LICENSE-2.0.html or in the project's LICENSE file.
 * GitHub https://github.com/GaoChenHang/FastEmail
 */

package github.gaochenhang.fastemail_be.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long userId;
    private String username;
    private String password;
    private Date createTime;
    private String role;
}
