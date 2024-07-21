package de.cehlers88.utils.crypton.encoder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaesarCipherEncoderTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void testEncode() {
        CaesarCipherEncoder encoder = new CaesarCipherEncoder();
        String originalValue = "Hello World!";
        String encodedValue = encoder.encode(originalValue, new int[]{1});

        assertEquals(encoder.encode(encodedValue, new int[]{-1}), originalValue);
    }

    @Test
    void testUnknownCharactersShouldNotManipulated() {
        CaesarCipherEncoder encoder = new CaesarCipherEncoder();
        String originalValue = "\"Hello World!\"";
        char[] encodedChars = encoder.encode(originalValue, new int[]{1}).toCharArray();
        char testChar = originalValue.charAt(0);

        assertSame(encodedChars[0], testChar);
        assertSame(encodedChars[encodedChars.length-1], testChar);
    }
}