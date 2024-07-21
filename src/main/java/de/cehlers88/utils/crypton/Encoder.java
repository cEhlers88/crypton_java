package de.cehlers88.utils.crypton;

import de.cehlers88.utils.crypton.encoder.CryptonEncoder;

public class Encoder {
    private static CryptonEncoder cryptonEncoder = new CryptonEncoder();
    public static String encrypt(String originalText){
        return Encoder.cryptonEncoder.encrypt(originalText);
    }
    public static String decrypt(String encryptedText){
        return Encoder.decrypt(encryptedText);
    }
}
