package com.jeysine.services.common.util;

import java.util.UUID;

/**
 * @author jxyao
 * @date 2018-5-15
 */
public class UuidUtil {
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(UuidUtil.getUuid().length());
    }
}
