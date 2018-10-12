package io.github.carlosthe19916.utils;

public class StringUtils {
    public static String padLeft(String str, int length, String padChar) {
        String pad = "";
        for (int i = 0; i < length; i++) {
            pad += padChar;
        }
        return pad.substring(str.length()) + str;
    }

    public static String leftPad(String originalString, int length, char padCharacter) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() + originalString.length() < length) {
            sb.append(padCharacter);
        }
        sb.append(originalString);
        String paddedString = sb.toString();

        return paddedString;
    }
}
