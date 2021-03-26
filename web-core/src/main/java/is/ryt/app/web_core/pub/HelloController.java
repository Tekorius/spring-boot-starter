package is.ryt.app.web_core.pub;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Hello", description = "Just saying hi")
@RestController
public class HelloController {
    @GetMapping
    public String root() {
        return "Hello, world";
    }
}
