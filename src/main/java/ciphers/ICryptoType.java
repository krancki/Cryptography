package ciphers;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Krzysztof on 2019-03-17.
 */
public interface ICryptoType {

    byte[] encrypt(byte[] message) throws BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException;
    byte[] decrypt(byte[] message) throws BadPaddingException, IllegalBlockSizeException;
    Key generateKey(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException;
}
