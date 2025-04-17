package exercises.exercise6;

import javax.crypto.SecretKey;
import java.security.KeyPair;

public class Main {
    public static void main(String[] args) throws Exception {

        //AES symmetric encryption
        SecretKey aesKey = AESUtil.generateKey();
        byte[] iv = AESUtil.generateIV();
        String aesMessage = "Hello Bob! - from Alice(AES)";

        String aesCipherText = AESUtil.encrypt(aesMessage, aesKey, iv);
        String aesPlainText = AESUtil.decrypt(aesCipherText, aesKey, iv);

        System.out.println("Original message: " + aesMessage);
        System.out.println("After encryption: " + aesCipherText);
        System.out.println("After decryption: " + aesPlainText);

        //RSA asymmetric encryption
        KeyPair bobKeyPair = RSAUtil.generateKeyPair();
        String rsaMessage = "Hello Bob! (using RSA)";
        String rsaCipher = RSAUtil.encrypt(rsaMessage, bobKeyPair.getPublic());
        String rsaPlain = RSAUtil.decrypt(rsaCipher, bobKeyPair.getPrivate());
        System.out.println("Original message: " + rsaMessage);
        System.out.println("After encryption: " + rsaCipher);
        System.out.println("After decryption: " + rsaPlain);

        //RSA Signature and Verification
        KeyPair aliceKeyPair = RSAUtil.generateKeyPair(); //Alice has the private key

        String signedMessage = "This is a signed message from Alice";
        String signature = SignatureUtil.sign(signedMessage, aliceKeyPair.getPrivate());

        boolean isValid = SignatureUtil.verify(signedMessage, signature, aliceKeyPair.getPublic());

        System.out.println("Signature Test: ");
        System.out.println("Original message: " + signedMessage);
        System.out.println("signature: " + signature);
        System.out.println("Signature verification results: " + (isValid? "Valid" : "Invalid"));
    }

}
