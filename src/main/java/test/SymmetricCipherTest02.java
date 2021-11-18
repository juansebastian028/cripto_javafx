package test;

import cipher.SymmetricCipher;
import util.Util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.ArrayList;

public class SymmetricCipherTest02 {
    public static void main(String[] args) {
        long timebefore = System.currentTimeMillis();
        SecretKey secretKey = null;

        try {
            secretKey = KeyGenerator.getInstance("DES").generateKey();
        } catch (Exception e) {
            System.err.println( e.getMessage() );
        }

        SymmetricCipher s = new SymmetricCipher(secretKey, "DES/ECB/PKCS5Padding");

        ArrayList<String> clearObject = new ArrayList<String>();
        clearObject.add("Ana");
        clearObject.add("Bety");
        clearObject.add("Carolina");
        clearObject.add("Daniela");
        clearObject.add("Elena");

        byte[] cipherObject = null;
        System.out.println(clearObject);
        cipherObject = s.encryptObject(clearObject);
        Util.printByteArrayHexadecimal(cipherObject);
        clearObject = (ArrayList<String>) s.decryptObject(cipherObject);
        System.out.println(clearObject);
        long timeafter = System.currentTimeMillis();
        System.out.println("Tiempo transcurrido: " + (timeafter - timebefore));
    }
}
