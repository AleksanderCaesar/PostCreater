package main.controller;

import main.api.response.InitResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class DefaultController {
    private final InitResponse initResponse;

    public DefaultController(InitResponse initResponse) {
        this.initResponse = initResponse;
    }

    @GetMapping("/")
    public String index(Model model)
    {
        return "index";
    }

    @GetMapping(value = "/**/{path:[^\\\\.]*}")
    public String redirectToIndex(@PathVariable String path) {
        return "forward:/"; //делаем перенаправление
    }
}
