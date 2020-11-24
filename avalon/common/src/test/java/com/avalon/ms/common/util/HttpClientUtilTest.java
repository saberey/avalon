package com.avalon.ms.common.util;


public class HttpClientUtilTest {

    @org.junit.Test
    public void doGet() {
        String uri = "http://www.google.com";
        Object[] objects = HttpClientUtil.doGet(uri);
        for (Object object : objects) {
            System.out.println(object);
        }
    }

    @org.junit.Test
    public void doPost() {
    }
}