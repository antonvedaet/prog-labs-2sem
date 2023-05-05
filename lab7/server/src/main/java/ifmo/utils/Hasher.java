package ifmo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

    String algorithm;

    public Hasher(String algorithm){
        this.algorithm = algorithm;
    }

    public String encode(String str){
        try{
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hash = digest.digest(str.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : hash) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        }catch(NoSuchAlgorithmException nsae){
            System.out.println(nsae.getMessage());
        }
        return null;
    }
}