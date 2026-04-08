package w2l.inspired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import w2l.inspired.domain.Message;

@RestController
public class MyController {
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
    @GetMapping("/hello/{player}")
    public Message message(@PathVariable String player) {//REST Endpoint.
        Message msg = new Message(player, "Hello " + player);
        return msg;
    }
}
