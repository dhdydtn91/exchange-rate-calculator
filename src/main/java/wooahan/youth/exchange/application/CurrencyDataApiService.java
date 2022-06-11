package wooahan.youth.exchange.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyDataApiService {

    private final CurrencyDataApiCaller currencyDataApiCaller;

    public CurrencyDataDto getCurrencyData() {
        return currencyDataApiCaller.call();
    }
}
