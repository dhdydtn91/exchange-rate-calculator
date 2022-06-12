package wooahan.youth.exchange.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wooahan.youth.exchange.presentation.ExchangeRequestDto.CurrencyDataRequest;
import wooahan.youth.exchange.presentation.ExchangeResponseDto.CurrencyDataDto;

@Service
@RequiredArgsConstructor
public class CurrencyDataApiService {

    private final CurrencyDataApiCaller currencyDataApiCaller;

    public CurrencyDataDto getCurrencyData(CurrencyDataRequest request) {
        return currencyDataApiCaller.call(request);
    }
}
