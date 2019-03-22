package ciphers;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * Created by Krzysztof on 2019-03-17.
 */
public class DESede implements ICryptoType {

    Cipher ecipher = null;
    Cipher decipher = null;

    static final String CRYPTONAME = "DESede";


    public DESede(String key) {

        try {
            ecipher = Cipher.getInstance(CRYPTONAME);
            decipher = Cipher.getInstance(CRYPTONAME);

            Key cryptoKey = generateKey(key);
            ecipher.init(Cipher.ENCRYPT_MODE, cryptoKey);
            decipher.init(Cipher.DECRYPT_MODE, cryptoKey);

        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }


    }


    @Override
    public byte[] encrypt(byte[] message) throws BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        return ecipher.doFinal(message);
    }

    @Override
    public byte[] decrypt(byte[] message) throws BadPaddingException, IllegalBlockSizeException {
        return decipher.doFinal(message);
    }

    @Override
    public Key generateKey(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newStr = Base64.getEncoder().encodeToString(md5.digest(key.getBytes()));

        System.out.println(md5.digest(key.getBytes()).length);
        System.out.println(newStr.getBytes().length);

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
        KeySpec spec = new DESedeKeySpec(newStr.getBytes());

        return secretKeyFactory.generateSecret(spec);
    }


}
