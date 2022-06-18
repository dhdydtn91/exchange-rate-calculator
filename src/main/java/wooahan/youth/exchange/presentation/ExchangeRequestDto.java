package wooahan.youth.exchange.presentation;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class ExchangeRequestDto {


    @Getter
    @Setter
    @AllArgsConstructor
    public static class CurrencyDataRequest {
        private final String source;
        private final String currencies;
    }

    @Getter
    @AllArgsConstructor
    public static class ExchangeRequest {
        private final String source;
        private final String currency;
        @NotNull
        private Integer remittanceAmount;
        private double exchangeRate;
    }
}
