package wooahan.youth.exchange.presentation;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wooahan.youth.exchange.application.CurrencyDataApiService;
import wooahan.youth.exchange.application.ExchangeRateCalculatorService;
import wooahan.youth.exchange.common.response.CommonResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ExchangeRateCalculatorController {

    private final CurrencyDataApiService currencyDataApiService;
    private final ExchangeRateCalculatorService exchangeRateCalculatorService;

    @GetMapping("/live")
    public ResponseEntity<CommonResponse> getCurrencyData(ExchangeRequestDto.CurrencyDataRequest request) {
        var response = currencyDataApiService.getCurrencyData(request);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    @PostMapping("/exchange")
    public ResponseEntity<CommonResponse> exchange(@RequestBody @Valid ExchangeRequestDto.ExchangeRequest request) {
        var response = exchangeRateCalculatorService.calculate(request);
        return ResponseEntity.ok(CommonResponse.success(response));
    }
}
