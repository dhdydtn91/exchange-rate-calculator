package wooahan.youth.exchange.presentation;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class ExchangeRequestDto {

    @Setter
    @AllArgsConstructor
    public static class CurrencyDataRequest {
        @Getter
        private final String source;
        private final List<String> currencies;

        public String getCurrencies() {
            return String.join(",", currencies);
        }
    }
}
