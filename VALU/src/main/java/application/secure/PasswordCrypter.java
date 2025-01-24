package application.secure;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/* Everything in this class was made with the
   help of a youtube video (phillipinian with 5 followers)
 */

public class PasswordCrypter {

    // Base64 encoded key and IV
    private static final String secretKeyBase64 = "EGdQeSoFvdlmmiIjwAx46g==";  // 128-bit key
    private static final String ivBase64 = "JiZXxPYMtynvPklWDCNegw==";        // 16-byte IV

    // Convert Base64-encoded key and IV
    private static final SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(secretKeyBase64), "AES");
    private static final IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(ivBase64));

    // AES Encryption
    public static String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error during encryption", e);
        }
    }

    // AES Decryption
    public static String decrypt(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Error during decryption", e);
        }
    }
}
