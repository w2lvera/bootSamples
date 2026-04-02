package w2l.inspired.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping({"/tmp"})
    public String index(){return "index";}
}
