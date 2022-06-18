package wooahan.youth.exchange.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExchangeRateViewController {

    @GetMapping("/")
    public String index (){
        return "index";
    }
}
