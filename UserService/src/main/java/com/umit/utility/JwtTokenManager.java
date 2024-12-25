package com.umit.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JwtTokenManager {
    /**
     * 1. Kullanicilara Token olusturmak
     * 2. Gelen Token bilgisini dogrulamak
     */

    private final String SECRETKEY = "123";
    private final String ISSUER = "Java13Auth";
    private final Long EXDATE = 1000L * 40; //40sn

    public Optional<String> createToken(Long authId){
        String token;
        try{
            token = JWT.create()
                    .withAudience()
                    .withClaim("authid",authId) //key value seklinde acik degerler tutmak icin kullanilir
                    .withClaim("ahmet_amca","selam nasilsin ?")
                    .withClaim("liste", List.of("ali","veli","deniz"))
                    .withIssuer(ISSUER)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+EXDATE)) //token ne zaman suresini dolacagi
                    .sign(Algorithm.HMAC512(SECRETKEY));
            return Optional.of(token);
        }catch (Exception e){
            return Optional.empty();
        }
    }


    public Optional<Long> validateToken(String token){
        try {
            /**
             * Token dogrularken ve icinden bilgileri cekerken ilk olarak
             * 1- sifreleme algoritmasini kullanarak token verify edilmeli.
             * 2- bu dogrulama isleminde suresinin dolup dolmadigini kontrol edilmeli
             * 3- sahipligi bizim mi
             * bunlar tamam ise token decode deilmis oluyor. Sonrasinda ise claim nesnelerinin icinden
             * bilgiler alinarak donus yapilir.
             */
            Algorithm algorithm= Algorithm.HMAC512(SECRETKEY);
            JWTVerifier verifier= JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT decodedJWT= verifier.verify(token);
            if (decodedJWT == null)
                return Optional.empty();
            Long authId= decodedJWT.getClaim("authid").asLong();
            return Optional.of(authId);
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
