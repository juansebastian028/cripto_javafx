package test;

import cipher.SymmetricCipher;
import util.Util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class SymmetricCipherTest01 {
    public static void main(String[] args) {
        long timebefore = System.currentTimeMillis();
        SecretKey secretKey = null;

        try {
            secretKey = KeyGenerator.getInstance("DES").generateKey();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        SymmetricCipher s = new SymmetricCipher(secretKey, "DES/ECB/PKCS5Padding");
        String clearText = "Bienvenidos a la criptografia!";
        /* char[] text = new char[300];
        for (int i = 0; i < text.length; i++) {
            text[i] = 'a';
        }
        String clearText = new String(text); */
        byte[] cipherText = null;
        System.out.println(clearText);
        cipherText = s.encriptMessage(clearText);
        Util.printByteArrayInt(cipherText);

        clearText = s.decryptMessage(cipherText);
        System.out.println(clearText);

        long timeafter = System.currentTimeMillis();
        System.out.println("Tiempo trancurrido: " + (timeafter - timebefore));
    }
}
