package de.cehlers88.utils.crypton.encoder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;
import java.util.stream.IntStream;

public class CryptonEncoder {
    private Random _random = new Random();
    private String[] _fillWords = new String[]{
            "[-CryptedByC.Ehlers-]",
            "(CryptedByC.Ehlers)",
            "(Crypted@C.Ehlers)",
            "{-=CryptedByC.Ehlers=-}",
            "[~C.Ehlers~]",
            "{~C.Ehlers~}",
            "[~CE-Crypted~]",
            "{~>CE-Crypted<~}",
            "(~>CE-Crypted<~)",
            "(=>CE-Crypted<=)"
    };
    private static CaesarCipherEncoder caesarCipherEncoder = new CaesarCipherEncoder();
    private String _doEncrypt(String originalText, int runCounter){
        try {
            int internalShiftsCounter = this._random.nextInt(originalText.length())+1;
            int mainShift = this._random.nextInt(9)+2;
            StringBuilder valuePrefix = new StringBuilder();
            StringBuilder valueSuffix = new StringBuilder();

            int[] internalShifts = new int[internalShiftsCounter];
            JSONObject jsonObject = new JSONObject();

            for(int i = 0; i<internalShiftsCounter; i++){
                internalShifts[i] = _random.nextInt(caesarCipherEncoder.getCharacters().length)+1;
            }

            for(int i = 0; i<this._random.nextInt(30); i++){
                String tmpAdd = _random.nextInt(10)>5 ?
                        _fillWords[_random.nextInt(_fillWords.length)] :
                        caesarCipherEncoder.encode(_fillWords[_random.nextInt(_fillWords.length)],internalShifts);

                if(_random.nextInt(10)>5){
                    valuePrefix.append(tmpAdd);
                }else{
                    valueSuffix.append(tmpAdd);
                }
            }

            jsonObject.put("extendedEncryption",true);
            jsonObject.put("shifts",internalShifts);
            jsonObject.put("value", caesarCipherEncoder.encode(
                    valuePrefix.toString() + originalText + valueSuffix.toString(),
                    internalShifts
            ));

            String result = mainShift + caesarCipherEncoder.encode(jsonObject.toString(), new int[]{mainShift});
            if(!this.validateEncryption(originalText, result)){
                if(runCounter>999){
                    // abort
                    return "error";
                }
                return _doEncrypt(originalText, runCounter+1);
            }
            return result;
        }catch (Exception e){
            System.out.println("Error while encrypting value: " + e.getMessage());
            return _doEncrypt(originalText, runCounter+1);
        }
    }
    private Boolean validateEncryption(String decryptedValue, String encryptedValue){
        try {
            return decryptedValue.equals(decrypt(encryptedValue));
        }catch (Exception e){
            return false;
        }
    }
    public String encrypt(String originalText){
        int retryRestCounter = 500;

        String result = _doEncrypt(originalText, 1);

        while(result.equals("error")){
            if(retryRestCounter<=0){
                return "error";
            }
            result = _doEncrypt(originalText, 1);
            retryRestCounter--;
        }

        return result;
    }
    public String decrypt(String encryptedText){
        int mainShift  = Character.getNumericValue(encryptedText.charAt(0));
        JSONObject cryptObject = new JSONObject(
                this.caesarCipherEncoder.encode(encryptedText.substring(1), new int[]{mainShift*-1})
        );
        JSONArray originalShifts = cryptObject.getJSONArray("shifts");
        int[] invertedShifts = IntStream.range(0, originalShifts.length())
                .map(i -> -originalShifts.getInt(i))
                .toArray();
        String decryptedValue = this.caesarCipherEncoder.encode(cryptObject.getString("value"),invertedShifts);

        for(int i = 0; i<=this._fillWords.length-1; i++){
            String tmpValueRaw = this._fillWords[i];
            String tmpValueEncoded = this.caesarCipherEncoder.encode(tmpValueRaw, IntStream.range(0, originalShifts.length())
                    .map(j -> originalShifts.getInt(j))
                    .toArray());
            while(decryptedValue.indexOf(tmpValueRaw)>=0){
                decryptedValue = decryptedValue.replace(tmpValueRaw, "");
            }
            while(decryptedValue.indexOf(tmpValueEncoded)>=0){
                decryptedValue = decryptedValue.replace(tmpValueEncoded, "");
            }
        }

        return decryptedValue;
    }
}
