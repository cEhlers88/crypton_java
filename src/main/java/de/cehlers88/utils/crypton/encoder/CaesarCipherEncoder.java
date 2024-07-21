package de.cehlers88.utils.crypton.encoder;

public class CaesarCipherEncoder {
    private char[] characters = new String("aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789!@#$%^&*()-_=+[]{}|;:,.<>?/`~").toCharArray();
    private int[] shifts = new int[1];

    private char _shiftCharacter(char originalCharacter, int shift){
        int characterIndex=0;
        for(int i=0;i<this.characters.length;i++){
            if (originalCharacter == this.characters[i]) {
                characterIndex = i;
                break;
            }
        }
        characterIndex = characterIndex + shift;

        while(characterIndex>this.characters.length-1){
            characterIndex = characterIndex - this.characters.length;
        }
        while (characterIndex<0){
            characterIndex = characterIndex + this.characters.length;
        }
        return this.characters[characterIndex];
    }

    public char[] getCharacters() {
        return characters;
    }

    public String encode(String originalValue, int[] shifts){
        char[] value = originalValue.toCharArray();
        for (int i = 0; i < value.length; i++) {
            value[i] = this._shiftCharacter(value[i], shifts[i % shifts.length]);
        }
        return new String(value);
    }
    public String encode(String originalValue){
        return this.encode(originalValue, this.shifts);
    }

    public CaesarCipherEncoder setCharacters(String characters){
        this.characters = characters.toCharArray();
        return this;
    }
    public CaesarCipherEncoder setShifts(int[] shifts){
        this.shifts = shifts;
        return this;
    }

}
