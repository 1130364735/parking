package com.zp.parking.tools;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomHelper {

    private static final char[] ALPHA_NUMERIC_POOL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    /**
     * generate random text
     *
     * @param len     the length of the generated text
     * @return random text
     */
    public static String generateNumericAlpha(int len) {
        return generateWithPool(len, ALPHA_NUMERIC_POOL);
    }

    /**
     * generate a text with all its characters as alpha + numeric char
     *
     * @return alpha + numeric code
     */
    private static String generateWithPool(int len, char[] pool) {
        StringBuilder builder = new StringBuilder(len);
        final Random random = new Random();

        for (int i = 0; i < len; ++i) {
            builder.append(pool[random.nextInt(pool.length)]);
        }

        return builder.toString();
    }
}
