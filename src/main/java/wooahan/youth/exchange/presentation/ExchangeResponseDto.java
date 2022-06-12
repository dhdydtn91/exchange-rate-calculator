package wooahan.youth.exchange.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import wooahan.youth.exchange.application.Quotes;

public class ExchangeResponseDto {

    @Getter
    @AllArgsConstructor
    public static class CurrencyDataDto {

        private final Quotes quotes;
        private final String source;
        private final String success;
    }
}
