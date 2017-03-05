package collabr.infra;


import com.google.common.hash.Hashing;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;

public class CollabrSecurity {

    private static final String secKey = "*6721hja902eg21h";

    public static String hash(String password){
        return Hashing.sha256().
                hashString(password, StandardCharsets.UTF_8).toString();
    }

    public static String encrypt(String text) throws Exception{
        String encryptedText = Jwts.builder()
                .setSubject(text)
                .signWith(SignatureAlgorithm.HS512, secKey)
                .compact();
        return encryptedText;
    }

    public static String decrypt(String strEncrypted) throws Exception{
        return Jwts.parser().setSigningKey(secKey).parseClaimsJws(strEncrypted).getBody().getSubject();
    }
}
