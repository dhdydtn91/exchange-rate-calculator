package wooahan.youth.exchange.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import lombok.Getter;
import org.springframework.util.StringUtils;
import wooahan.youth.exchange.common.exception.BusinessException;

public class Money {

    private final BigDecimal exchangeRate;
    @Getter
    private final String currency;

    public Money(double exchangeRate, String currency) {
        this(BigDecimal.valueOf(exchangeRate), currency);
    }

    public Money(BigDecimal exchangeRate, String currency) {
        validate(exchangeRate, currency);
        this.exchangeRate = exchangeRate;
        this.currency = currency;
    }

    private void validate(BigDecimal exchangeRate, String currency) {
        if (!StringUtils.hasText(currency) || isNull(exchangeRate)) {
            throw new BusinessException();
        }
    }

    private boolean isNull(BigDecimal resultExchangeRate) {
        return resultExchangeRate == null;
    }

    public Money exchange(int remittanceAmount, String currency) {
        BigDecimal resultExchangeRate = exchangeRate.multiply(new BigDecimal(remittanceAmount))
                .setScale(2, RoundingMode.DOWN);
        return new Money(resultExchangeRate, currency);
    }

    public String getExchangeRate() {
        return makeCommaAndScale();
    }

    private String makeCommaAndScale() {
        DecimalFormat df = new DecimalFormat("###,###,###.00");
        return df.format(this.exchangeRate.doubleValue());
    }
}
