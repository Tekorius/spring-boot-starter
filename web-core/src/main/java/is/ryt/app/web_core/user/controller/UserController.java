package is.ryt.app.web_core.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import is.ryt.app.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User")
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public String userInfo(@AuthenticationPrincipal UserPrincipal principal) {
        return "You are logged in as " + principal.getUserId();
    }
}
