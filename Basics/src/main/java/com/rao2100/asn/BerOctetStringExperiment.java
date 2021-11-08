package com.rao2100.asn;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BerOctetStringExperiment {

    public static void hexConversion() throws UnsupportedEncodingException {
        System.out.println( "Hello World!" );
//        byte[] val = "\u0012@��\u0011p1@\u0000".getBytes(StandardCharsets.UTF_8);
//        byte[] val = "\u0012@��\u0011p1@\u0000".getBytes(StandardCharsets.ISO_8859_1);
        byte[] val = "Hello World!".getBytes(StandardCharsets.ISO_8859_1);

//        byte[] val = "\u0081\u0010\u0011B\u0086s\u0093\u0014".getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(val));
        String output = DatatypeConverter.printHexBinary(val);
        System.out.println(output);

        //        "1240725150302B4000"
//        2D (-) or 2B (+)
//        String hexString = new String("1240725150302B4000".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
//        System.out.println(hexStringToByteArray(hexString));

        BigInteger n = new BigInteger(val);
        String hexa = n.toString(16);
        System.out.println(hexa);

        String s = new String(val, StandardCharsets.UTF_8);
        System.out.println(s);


//        System.out.println(fromHex("8110114286739314"));
//
//        System.out.println(fromHex("A17836207916"));


        System.out.println(fromHex("A17836207916"));

    }

//    public static String fromHex(String s) throws UnsupportedEncodingException {
//        byte bs[] = new byte[s.length() / 2];
//        for (int i=0; i<s.length(); i+=2) {
//            bs[i/2] = (byte) Integer.parseInt(s.substring(i, i+2), 16);
//        }
//        return new String(bs, StandardCharsets.ISO_8859_1);
//    }



    /*
    1st Octet: YY
    2nd Octet: MM
    3rd Octet: DD
    4th Octet: hh
    5th Octet: mm
    6th Octet: ss
    7th Octet: Sign (0 :- '', 1: '+')
    8th Octet: hour
    9th Octet: minute
     */
    public static String bcdTwistedToTimeStampString(String hexString) {

        // +2 to accommodate the year 20 prefix
        StringBuilder output = new StringBuilder(hexString.length() + 2);
        //  Add 20 to year
        output.append("20");

        for (int i = 0; i < hexString.length(); i += 2) {
            if (i == 12) {
                addSign(output, hexString.charAt(i+1));
                continue;
            }
            reverseChars(output, hexString.charAt(i), hexString.charAt(i+1));
        }
        return output.toString();
    }

    public static void addSign(StringBuilder str, char a) {
        String sign = "+";
        if (a == '0') {
            sign = "-";
        }
        str.append(sign);
    }

    public static void reverseChars(StringBuilder str, char a,  char b) {
        str.append(b);
        str.append(a);
    }

//    public static byte[] hexStringToByteArray(String s) {
//        int len = s.length();
//        byte[] data = new byte[len / 2];
//        for (int i = 0; i < len; i += 2) {
//            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
//                    + Character.digit(s.charAt(i+1), 16));
//        }
//        return data;
//    }



    public static String fromHex(String hexString) throws UnsupportedEncodingException {
        byte byteArr[] = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i += 2) {
            int number = Integer.parseInt(hexString.substring(i, i + 2), 16);
            if (number < 32) {
                throw new UnsupportedEncodingException(
                        String.format("String \"%1s\" contains unreadable character: \"%2s\"",
                                new String(byteArr, StandardCharsets.ISO_8859_1),
                                new String(new byte[] { (byte) number }, StandardCharsets.ISO_8859_1)));
            }
            byteArr[i / 2] = (byte) number;
        }
        return new String(byteArr, StandardCharsets.ISO_8859_1);
    }
//
//    public static String fromHex(String s) throws UnsupportedEncodingException {
//        byte bs[] = new byte[s.length() / 2];
//        for (int i=0; i<s.length(); i+=2) {
//            bs[i/2] = (byte) Integer.parseInt(s.substring(i, i+2), 16);
//        }
//        return new String(bs, "UTF8");
//    }


//    public static String convertUtf8NumbersToString(String[] numbers){
//        int length = numbers.length;
//        byte[] data = new byte[length];
//
//        for(int i = 0; i< length; i++){
//            data[i] = Byte.parseByte(numbers[i]);
//        }
//        return new String(data, Charset.forName("UTF-8"));
//    }

//    public static byte[] hexStringToByteArray(String s) {
//        int len = s.length();
//        byte[] data = new byte[len / 2];
//        for (int i = 0; i < len; i += 2) {
//            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
//                    + Character.digit(s.charAt(i+1), 16));
//        }
//        return data;
//    }


}
