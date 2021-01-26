package com.ChanhTin.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateHelper {

    public ValidateHelper() {
    }

    public static boolean validateString(String str, int maxLength, int minLength) {
        int length = str.length();

        return minLength < length && length < maxLength;
    }

    public static boolean validatePositiveNum(float num) {
        return num >= 0;
    }

    public static boolean validateFloat(String str) {
        try {
            float fl = Float.parseFloat(str);
            return true;
        } catch (NumberFormatException ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    public static boolean regexName(String name, int maxLength, int minLenght) {
        name = standardized(name);
        if (validateString(name, maxLength, minLenght)) {
            Pattern pattern = Pattern.compile("^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\W|_]+$");
            Matcher matcher = pattern.matcher(name);

            return matcher.matches();
        }
        return false;
    }

    public static boolean regexEmail(String email) {
        String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9]" +
                "(?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean regexPhoneNum(String phone) {
        String regex = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?" +
                "(\\d{3})(\\s|\\.)?(\\d{3})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();
    }

    public static boolean regexPeopleID(String peopleID) {
        Pattern pattern = Pattern.compile("^\\d{9}|\\d{12}$");
        Matcher matcher = pattern.matcher(peopleID);

        return matcher.matches();
    }

    public static boolean regexURL(String url) {
        Pattern pattern = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]$");
        Matcher matcher = pattern.matcher(url);

        return matcher.matches();
    }

    public static String standardized(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        return str;
    }


}
