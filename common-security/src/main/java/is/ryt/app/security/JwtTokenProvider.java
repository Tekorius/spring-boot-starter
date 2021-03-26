package is.ryt.app.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * This component contains JWT helper methods to generate, validate and extract tokens
 */
@Slf4j
@Component
public class JwtTokenProvider {
    private static final String CLAIMS_USERNAME = "u";
    private static final String CLAIMS_AUTHORITIES = "a";

    private final String jwtSecret;
    private final int jwtExpirationInMs;
    private final int maxExpirationInMs;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.jwtSecret = jwtProperties.getSecret();
        this.jwtExpirationInMs = jwtProperties.getExpirationInMs();
        this.maxExpirationInMs = jwtProperties.getMaxExpirationInMs();
    }

    public String generateDefaultToken(UserPrincipal principal) {
        return generateToken(principal, jwtExpirationInMs);
    }

    public String generateToken(UserPrincipal principal, int expirationInMs) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationInMs);


        return JWT.create()
                .withSubject(String.valueOf(principal.getUserId()))
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .withClaim(CLAIMS_AUTHORITIES, new ArrayList<>(principal.getAuthorities()))
                .withClaim(CLAIMS_USERNAME, principal.getUsername())
                .sign(Algorithm.HMAC512(jwtSecret));
    }

    public UserPrincipal getUserPrincipalFromToken(String token) {
        DecodedJWT claims = JWT.require(Algorithm.HMAC512(jwtSecret))
                .build()
                .verify(token);

        Date iat = claims.getClaim("iat").asDate();
        Date exp = claims.getClaim("exp").asDate();
        if (iat == null || exp == null || exp.getTime() - iat.getTime() > maxExpirationInMs)
            throw new InvalidClaimException("Bad dates");

        return new UserPrincipal(
                Long.parseLong(claims.getSubject()),
                claims.getClaim(CLAIMS_USERNAME).asString(),
                claims.getClaim(CLAIMS_AUTHORITIES).asList(String.class).stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList())
        );
    }
}
