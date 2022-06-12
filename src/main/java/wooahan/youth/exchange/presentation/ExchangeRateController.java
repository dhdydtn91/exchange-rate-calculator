package wooahan.youth.exchange.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wooahan.youth.exchange.application.CurrencyDataApiService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ExchangeRateController {

    private final CurrencyDataApiService currencyDataApiService;

    @PostMapping("/live")
    public ResponseEntity<ExchangeResponseDto.CurrencyDataDto> exchange(ExchangeRequestDto.CurrencyDataRequest request){
        var response = currencyDataApiService.getCurrencyData(request);
        return ResponseEntity.ok().body(response);
    }
}
