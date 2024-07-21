package de.cehlers88.utils.crypton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncoderTest {

    @Test
    void testEncoding() {
        String originalText = "Hello World!";
        String encryptedText = Encoder.encrypt(originalText);
        String decryptedText = Encoder.decrypt(encryptedText);

        assertEquals(originalText, decryptedText);
    }
}