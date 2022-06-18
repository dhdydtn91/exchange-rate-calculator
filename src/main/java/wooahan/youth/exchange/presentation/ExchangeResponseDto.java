package wooahan.youth.exchange.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import wooahan.youth.exchange.domain.Money;
import wooahan.youth.exchange.application.Quotes;

public class ExchangeResponseDto {

    @Getter
    @AllArgsConstructor
    public static class CurrencyDataDto {

        private final Quotes quotes;
        private final String source;
        private final boolean success;
    }

    @Getter
    public static class ExchangeResponse  {

        private final String currency;
        private final String exchangeAmount;

        public ExchangeResponse(Money money) {
            this.currency = money.getCurrency();
            this.exchangeAmount = money.getExchangeRate();
        }
    }
}
