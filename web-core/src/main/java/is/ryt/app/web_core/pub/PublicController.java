package is.ryt.app.web_core.pub;

import io.swagger.v3.oas.annotations.tags.Tag;
import is.ryt.app.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Public", description = "Endpoints that can be reached without authorization")
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
