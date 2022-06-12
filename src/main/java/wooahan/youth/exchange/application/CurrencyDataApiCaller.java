package wooahan.youth.exchange.application;

import wooahan.youth.exchange.presentation.ExchangeRequestDto.CurrencyDataRequest;
import wooahan.youth.exchange.presentation.ExchangeResponseDto.CurrencyDataDto;

public interface CurrencyDataApiCaller {
    CurrencyDataDto call(CurrencyDataRequest request);
}
