package util;

import java.io.*;

public class Util {
    public static void printByteArrayInt(byte[] byteArray) {
        System.out.println("{" + byteArrayIntToString(byteArray) + "}");
    }

    public static String byteArrayIntToString(byte[] byteArray) {
        String out = "";
        int i = 0;
        for (; i < byteArray.length -1 ; i++) {
            out += byteArray[i] + 128 + " ";
        }
        out += byteArray[i] + 128;
        return out;
    }

    public static byte[] objectToByteArray(Object o) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(o);
        out.close();
        byte[] buffer = bos.toByteArray();
        return  buffer;
    }

    public  static Object byteArrayToObject(byte[] byteArray) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteArray));
        Object o = in.readObject();
        in.close();
        return o;
    }

    public static void printByteArrayHexadecimal(byte[] byteArray) {
        System.out.println("{" + byteArrayHexadecimalToString(byteArray) + "}");
    }

    public static String byteArrayHexadecimalToString(byte[] byteArray) {
        String out = "";
        int i = 0;
        for (; i < byteArray.length - 1 ; i++) {
            if((byteArray[i] & 0xff) <= 0xf) {
                out += "0";
            }
            out += Integer.toHexString(byteArray[i] & 0xff) + " ";
        }
        out += Integer.toHexString(byteArray[i] & 0xff);
        return out;
    }
}
