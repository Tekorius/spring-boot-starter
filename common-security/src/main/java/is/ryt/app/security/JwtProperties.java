package is.ryt.app.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.NonNull;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.security.jwt")
public class JwtProperties {
    @NonNull
    private String secret;

    private int expirationInMs = 1000*60*60; // 1 hr
    private int maxExpirationInMs = 3000*60*60; // 3 hrs
}
