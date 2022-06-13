package wooahan.youth.exchange.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wooahan.youth.exchange.application.CurrencyDataApiService;
import wooahan.youth.exchange.common.response.CommonResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ExchangeRateController {

    private final CurrencyDataApiService currencyDataApiService;

    @GetMapping("/live")
    public ResponseEntity<CommonResponse> getCurrencyData(ExchangeRequestDto.CurrencyDataRequest request) {
        var response = currencyDataApiService.getCurrencyData(request);
        return ResponseEntity.ok(CommonResponse.success(response));
    }
}
