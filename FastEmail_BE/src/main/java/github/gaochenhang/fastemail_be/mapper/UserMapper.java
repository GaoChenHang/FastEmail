/*
 * Copyright (c) 2024 GaoChenHang.
 * This code is licensed under the Apache 2.0 open source license.
 * You can find usage information or restrictions at https://www.apache.org/licenses/LICENSE-2.0.html or in the project's LICENSE file.
 * GitHub https://github.com/GaoChenHang/FastEmail
 */

package github.gaochenhang.fastemail_be.mapper;

import github.gaochenhang.fastemail_be.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    void addNewUser(User user);
    String getUserPasswordByUsername(String username);
    User getUserByUsername(@Param("username") String username);
}
