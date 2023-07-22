package danekerscode.server.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import danekerscode.server.utils.TokenType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;

import static danekerscode.server.utils.AppConstants.*;
import static danekerscode.server.utils.TokenType.ACCESS;
import static danekerscode.server.utils.TokenType.REFRESH;

@Component
public class JwtService {

    @Value("${jwt.token.issuer}")
    private String issuer;

    @Value("${jwt.token.subject}")
    private String subject;

    @Value("${jwt.token.secret}")
    private String SECRET;


    public String validateToken(final String token, TokenType type) {
        JWTVerifier verifier = JWT
                .require(getAlgorithm.apply(type,SECRET))
                .withSubject("email")
                .withIssuer(issuer)
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }


    public String generateToken(String email, TokenType type) {
        return createToken(email, type);
    }

    private String createToken(
            String email,
            TokenType token
    ) {
        return JWT.create()
                .withClaim("email", email)
                .withSubject(subject)
                .withIssuer(issuer)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + getIssuedTime.apply(token)))
                .sign(getAlgorithm.apply(token,SECRET));
    }


    private final BiFunction<TokenType,String,Algorithm> getAlgorithm = (type,SECRET)  ->
            type == ACCESS ? Algorithm.HMAC256(SECRET) :
                    type == REFRESH ? Algorithm.HMAC512(SECRET)
                    : Algorithm.HMAC384(SECRET);

    private final Function<TokenType,Integer> getIssuedTime = token ->
            token == ACCESS ? ACCESS_TOKEN_EXPIRATION :
                    token == REFRESH ? REFRESH_TOKEN_EXPIRATION :
                            VERIFICATION_TOKEN_EXPIRATION;

}