/*
 * Copyright (c) 2024 GaoChenHang.
 * This code is licensed under the Apache 2.0 open source license.
 * You can find usage information or restrictions at https://www.apache.org/licenses/LICENSE-2.0.html or in the project's LICENSE file.
 * GitHub https://github.com/GaoChenHang/FastEmail
 */

package github.gaochenhang.fastemail_be;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 工具类，用于生成不同类型的 SHA 哈希值
 *
 * @author Gch
 */
public class SHAUtil {
    private static final Logger log = LoggerFactory.getLogger(SHAUtil.class);
    /**
     * 传入文本内容，返回 SHA-256 哈希值
     *
     * @param strText 要加密的文本内容
     * @return SHA-256 哈希值
     */
    public static String SHA256(final String strText) {
        return SHA(strText, "SHA-256");
    }

    /**
     * 传入文本内容，返回 SHA-512 哈希值
     *
     * @param strText 要加密的文本内容
     * @return SHA-512 哈希值
     */
    public static String SHA512(final String strText) {
        return SHA(strText, "SHA-512");
    }

    /**
     * 传入文本内容，返回 MD5 哈希值
     *
     * @param strText 要加密的文本内容
     * @return MD5 哈希值
     */
    public static String MD5(final String strText) {
        return SHA(strText, "MD5");
    }

    /**
     * 根据指定的算法对文本内容进行哈希处理
     *
     * @param strText 要加密的文本内容
     * @param strType 哈希算法类型（如 SHA-256, SHA-512, MD5）
     * @return 指定算法的哈希值
     * @throws RuntimeException 如果指定的哈希算法不存在，则抛出运行时异常
     */
    private static String SHA(final String strText, final String strType) {
        // 返回值
        String strResult = null;

        // 检查输入字符串是否为空或为空字符串
        if (strText != null && !strText.isEmpty()) {
            try {
                // 创建消息摘要对象并指定算法类型
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // 更新摘要对象以处理输入字符串的字节数组
                messageDigest.update(strText.getBytes());
                // 获取哈希值的字节数组
                byte[] byteBuffer = messageDigest.digest();

                // 将字节数组转换为十六进制字符串
                StringBuilder strHexString = new StringBuilder();
                for (byte b : byteBuffer) {
                    // 将每个字节转换为两位十六进制数
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 设置最终的哈希字符串结果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                // 抛出运行时异常，指示指定的哈希算法不存在
                log.error("Hash algorithm not found: {}", strType);
            }
        }

        return strResult;
    }
}
