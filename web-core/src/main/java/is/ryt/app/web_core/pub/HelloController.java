package is.ryt.app.web_core.pub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping
    public String root() {
        return "Hello, world";
    }
}
