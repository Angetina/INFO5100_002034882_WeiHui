package exercises.exercise6;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtil {
    private static final int AES_KEY_SIZE = 256; //Key length: 256 bits
    private static final int GCM_IV_LENGTH = 12; //IV length for GCM mode (bytes)
    private static final int GCM_TAG_LENGTH = 128; //Verification tag length (bits)

    //Generate AES key
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AES_KEY_SIZE); //AES-256
        return keyGen.generateKey();
    }

    //Generate IV (initialization vector) for GCM mode
    public static byte[] generateIV(){
        byte[] iv = new byte[GCM_IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    //Encrypted text
    public static String encrypt(String message, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encrypted = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    //Decrypting text
    public static String decrypt(String cipherText, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] decode = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decode);
        return new String(decrypted);
    }
}
