package exercises.exercise6;

import java.security.*;
import java.util.Base64;

public class SignatureUtil {

    //Sign the message using the private key
    public static String sign(String message, PrivateKey privateKey) throws Exception{
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        byte[] signedBytes = signature.sign();
        return Base64.getEncoder().encodeToString(signedBytes);
    }

    //Verify signature using public key
    public static boolean verify(String message, String signatureStr, PublicKey publicKey) throws Exception{
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes());
        byte[] signatureBytes = Base64.getDecoder().decode(signatureStr);
        return signature.verify(signatureBytes);
    }
}
