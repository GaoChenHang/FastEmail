/*
 * Copyright (c) 2024 GaoChenHang.
 * This code is licensed under the Apache 2.0 open source license.
 * You can find usage information or restrictions at https://www.apache.org/licenses/LICENSE-2.0.html or in the project's LICENSE file.
 * GitHub https://github.com/GaoChenHang/FastEmail
 */

package github.gaochenhang.fastemail_be.entity;

import github.gaochenhang.fastemail_be.SHAUtil;

import java.util.Date;


public class User {
    public User() {
    }

    public User(Long userId, String username, String password, Date createTime, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.createTime = createTime;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private Long userId;
    private String username;
    private String password;
    private Date createTime;
    private String role;


    public void updatePasswordToSha256(){
        password = SHAUtil.SHA256(password);
    }
    /**
     * 检查用户名和密码是否合法
     * 1.用户名密码是否为空
     * 2.用户名的标识符是否以‘user_’开头
     * 3.密码是否为6-16位
     * @return boolean
     */
    public boolean checkUser(){
        if (username == null||password==null) {
            return false;
        }
        if (!username.startsWith("user_")){
            return false;
        }
        return true;

    }


}
