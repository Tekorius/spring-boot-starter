package is.ryt.app.web_core.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import is.ryt.app.model.auth.AuthTokenResponse;
import is.ryt.app.model.auth.LoginRequest;
import is.ryt.app.security.JwtTokenProvider;
import is.ryt.app.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Tag(name = "Auth", description = "Endpoints used to generate authentication tokens")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Operation(summary = "Generate JWT token using credentials")
    @PostMapping("/login")
    public AuthTokenResponse login(@RequestBody @Validated LoginRequest request) {
        UserPrincipal principal = new UserPrincipal(123L, "Bla", Collections.emptyList());
//        UserPrincipal principal = new UserPrincipal(123L, "Bla", List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

        var response = new AuthTokenResponse();
        response.setToken(tokenProvider.generateDefaultToken(principal));
        return response;
    }
}
