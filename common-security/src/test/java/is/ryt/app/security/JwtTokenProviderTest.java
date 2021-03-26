package is.ryt.app.security;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {
    private final JwtTokenProvider provider;

    JwtTokenProviderTest() {
        var props = new JwtProperties();
        props.setSecret("secret");
        props.setExpirationInMs(1000*60*60);
        props.setMaxExpirationInMs(1000*60*60);

        provider = new JwtTokenProvider(props);
    }

    @Test
    void generateAndValidateToken() {
        var userPrincipal = new UserPrincipal(123L, "useriux", new ArrayList<>());

        String jwt = provider.generateDefaultToken(userPrincipal);
        var extractedPrincipal = provider.getUserPrincipalFromToken(jwt);

        assertEquals(userPrincipal, extractedPrincipal);
    }

    @Test
    void invalidSecret() {
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjMiLCJhIjpbXSwidSI6InVzZXJpdXgiLCJleHAiOjE2MTY3MTQzNTUsImlhdCI6MTYxNjcxMDc1NX0.fQl88Qyj2zxOnotkNdtMdWBkIRCWQ1coxGZncnzaAFotNZz8AAhBU_AGInjmX20FC5VV1nfCXTzDbALRMdVRQw";
        assertThrows(SignatureVerificationException.class, () -> provider.getUserPrincipalFromToken(jwt));
    }

    @Test
    void invalidAlgorithm() {
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJhIjpbXSwidSI6InVzZXJpdXgiLCJleHAiOjE2MTY3MTQzNTUsImlhdCI6MTYxNjcxMDc1NX0.OAvDmZuDp0nTja667LkDv88KgjIB3CmbmdZX0pUpN3E";
        assertThrows(AlgorithmMismatchException.class, () -> provider.getUserPrincipalFromToken(jwt));
    }

    @Test
    void badToken() {
        String jwt = "blabla.bla.bla";
        assertThrows(JWTDecodeException.class, () -> provider.getUserPrincipalFromToken(jwt));
    }

    @Test
    void expiredToken() {
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjMiLCJhIjpbXSwidSI6InVzZXJpdXgiLCJleHAiOjE2MTY2MTQzNTUsImlhdCI6MTYxNjYxMDc1NX0.HneiU_X09ewPPdGgag3mSzw256vpGccvYt1RYIfjorCi1LduvZAoPOIieFh_xIomY0YLKxgTE8yefymfLP88xA";
        assertThrows(TokenExpiredException.class, () -> provider.getUserPrincipalFromToken(jwt));
    }

    @Test
    void nonExpiringToken() {
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjMiLCJhIjpbXSwidSI6InVzZXJpdXgifQ.JjSdzVgPyh9K_miTnj2Z6iR-1_MZawa5YGNXDoZCjogTRY4SRRChH5Q7-sEWkb5FjNxmLt7ht_tAsLC5jeCKVA";
        assertThrows(Exception.class, () -> provider.getUserPrincipalFromToken(jwt));
    }

    @Test
    void expirationTooLong() {
        var userPrincipal = new UserPrincipal(123L, "useriux", new ArrayList<>());

        String jwt = provider.generateToken(userPrincipal,1000*60*60+1000);
        assertThrows(Exception.class, () -> provider.getUserPrincipalFromToken(jwt));
    }

    @Test
    void issuedInTheFuture() {
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjMiLCJhIjpbXSwidSI6InVzZXJpdXgiLCJleHAiOjI2MTY2MTQzNTUsImlhdCI6MjYxNjYxMDc1NX0.efLB-nLHXJNDGsTci9Fy_tI2YvWp4Gc4TtMW6azHuoTF9gxGZChu-an_w3BIPnzMgXZEuiyDPQ5VcnFzELhZ5Q";
        assertThrows(Exception.class, () -> provider.getUserPrincipalFromToken(jwt));
    }
}