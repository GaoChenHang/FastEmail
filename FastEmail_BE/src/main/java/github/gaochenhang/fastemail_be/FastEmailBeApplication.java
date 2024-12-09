/*
 * Copyright (c) 2024 GaoChenHang.
 * This code is licensed under the Apache 2.0 open source license.
 * You can find usage information or restrictions at https://www.apache.org/licenses/LICENSE-2.0.html or in the project's LICENSE file.
 * GitHub https://github.com/GaoChenHang/FastEmail
 */
package github.gaochenhang.fastemail_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * FastEmail后端主启动类
 *
 * @author Gch
 */
@SpringBootApplication
@RestController
public class FastEmailBeApplication {
    /**
     * 启动方法
     *
     * @param args 接受的参数 默认为空数组
     */
    public static void main(String[] args) {
        SpringApplication.run(FastEmailBeApplication.class, args);
    }

}
