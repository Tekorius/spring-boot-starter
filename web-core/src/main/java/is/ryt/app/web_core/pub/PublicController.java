package is.ryt.app.web_core.pub;

import is.ryt.app.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @GetMapping
    public String root(@AuthenticationPrincipal UserPrincipal principal) {
        if (principal == null) {
            return "This is something public";
        } else {
            return "This is something public and user ID " + principal.getUserId() + " is logged in";
        }
    }
}
