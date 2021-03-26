package is.ryt.app.web_core.auth.controller;

import is.ryt.app.security.JwtTokenProvider;
import is.ryt.app.security.UserPrincipal;
import is.ryt.app.web_core.auth.model.AuthTokenResponse;
import is.ryt.app.web_core.auth.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public AuthTokenResponse login(@RequestBody LoginRequest request) {
        UserPrincipal principal = new UserPrincipal(123L, "Bla", Collections.emptyList());

        var response = new AuthTokenResponse();
        response.setToken(tokenProvider.generateDefaultToken(principal));
        return response;
    }
}
