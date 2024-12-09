/*
 * Copyright (c) 2024 GaoChenHang.
 * This code is licensed under the Apache 2.0 open source license.
 * You can find usage information or restrictions at https://www.apache.org/licenses/LICENSE-2.0.html or in the project's LICENSE file.
 * GitHub https://github.com/GaoChenHang/FastEmail
 */

package github.gaochenhang.fastemail_be.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class SendEmail {
    private static final Logger logger = LoggerFactory.getLogger(SendEmail.class);

    @Value("${fast.email.account}")
    private String mailUsername;
    @Value("${fast.email.password}")
    private String mailPassword;
    @Value("${fast.email.port}")
    private String mailPort;
    @Value("${fast.email.smtp_host}")
    private String mailSmtpHost;

    private Session session;

    public SendEmail() {
        createSession();
    }

    private static String generateVerifyCode() {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int type = random.nextInt(3);
            switch (type) {
                case 0: // 生成大写字母
                    char ch = (char) (random.nextInt(26) + 'A');
                    captcha.append(ch);
                    break;
                case 1: // 生成小写字母
                    char chLower = (char) (random.nextInt(26) + 'a');
                    captcha.append(chLower);
                    break;
                case 2: // 生成数字
                    int num = random.nextInt(10);
                    captcha.append(num);
                    break;
            }
        }
        return captcha.toString();
    }

    private void createSession() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "SMTP"); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", mailSmtpHost); // 指定smtp服务器地址
        props.setProperty("mail.smtp.port", mailPort);
        props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
        props.setProperty("mail.smtp.connectiontimeout", "10000");// 与邮件服务器建立连接的时间限制
        props.setProperty("mail.smtp.timeout", "10000");// 邮件smtp读取的时间限制
        props.setProperty("mail.smtp.writetimeout", "10000");// 邮件内容上传的时间限制

        this.session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUsername, mailPassword);
            }
        });
    }

    public void sendVerifyCode(String toEmail) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("JavaMail验证码");
        message.setContent("Your Code " + generateVerifyCode(), "text/html; charset=utf-8");
        Transport.send(message);
        logger.info("Verification code sent to {}", toEmail);
    }
}