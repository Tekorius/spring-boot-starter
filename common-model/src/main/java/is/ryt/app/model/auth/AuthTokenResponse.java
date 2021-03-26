package is.ryt.app.model.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthTokenResponse {
    @Schema(description = "JWT Token to be included in Authorization: Bearer header")
    private String token;
}
