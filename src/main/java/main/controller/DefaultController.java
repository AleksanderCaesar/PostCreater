package main.controller;

import main.api.response.InitResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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
}
