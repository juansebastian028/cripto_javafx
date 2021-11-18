package cipher;

import util.Util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.InvalidKeyException;

public class SymmetricCipher {
    private SecretKey secretKey;
    private Cipher cipher;

    public SymmetricCipher(SecretKey secretKey, String transformation) {
        this.secretKey = secretKey;

        try {
            cipher = Cipher.getInstance(transformation);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public byte[] encriptMessage(String input) {
        byte[] cipherText = null;
        byte[] clearText = input.getBytes();
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            cipherText = cipher.doFinal(clearText);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cipherText;
    }

    public String decryptMessage(byte[] input) {
        String output = "";
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] clearText = cipher.doFinal(input);
            output = new String(clearText);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return  output;
    }

    public byte[] encryptObject(Object input) {
        byte [] cipherObject = null;
        byte [] clearObject = null;

        try {
            clearObject = Util.objectToByteArray(input);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            cipherObject = cipher.doFinal(clearObject);

        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        } catch (IOException | InvalidKeyException e) {
            System.err.println( e.getMessage() );
        }
        return cipherObject;
    }

    public Object decryptObject(byte[] input) {
        Object output = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] clearObject = cipher.doFinal(input);
            output = Util.byteArrayToObject(clearObject);
        } catch (Exception e) {
            System.err.println( e.getMessage() );
        }
        return output;
    }

    public SecretKey getKey() {
        return secretKey;
    }
}
