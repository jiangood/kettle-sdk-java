package io.github.jiangood.kettle.sdk.test;

import io.github.jiangood.kettle.sdk.HttpUtil;

import java.io.IOException;

public class HttpUtilTest {

    public static void main(String[] args) throws IOException {
        String str = "H4sIAAAAAAAA/zMyMDLRN7DQNzBTMDSwMrW0AjJ0FbLyk4yA1NM9DU+Xdz/rXP5iYc+T3bufdi3k5TLC0GCOruHlwp3RyUWpiSWp8WmZOamxuDQhqQHyXIOC/IMUNMpSi4oz8/MULPVM9Az0DHSNTYx1 FJJKM3NSFIBchbSi/FwFoHFGuoaGugYWCgbmeqYGekbmCkmVEFXppZWaClYKbiBDo12sYkpyC2JKUotLDPVy8tNjFVIrMotLinUU0hIzczLz0vUIeWhdz7OOCU/2znmyYxa6txQ0nu+e/GzeHNvotMSc4tRYTQJmQUIQEpogc9dP5eUCAAnbkXN/AQAA";
        String st1 = "H4sIAAAAAAAA/zMyMDLRN7DQNzBTMDSwMrW0AjJ0FbLyk4yA1NM9DU+Xdz/rXP5iYc+T3bufdi3k\n5TLC0GCOruHlwp3RyUWpiSWp8WmZOamxuDQhqQHyXIOC/IMUNMpSi4oz8/MULPVM9Az0DHSNTYx1\nFJJKM3NSFIBchbSi/FwFoHFGuoaGugYWCgbmeqYGekbmCkmVEFXppZWaClYKbiBDo12sYkpyC2JK\nUotLDPVy8tNjFVIrMotLinUU0hIzczLz0vUIeWhdz7OOCU/2znmyYxa6txQ0nu+e/GzeHNvotMSc\n4tRYTQJmQUIQEpogc9dP5eUCAAnbkXN/AQAA\n";
        String s = HttpUtil.decodeBase64ZippedString(st1);
        System.out.println(s);
    }
}
