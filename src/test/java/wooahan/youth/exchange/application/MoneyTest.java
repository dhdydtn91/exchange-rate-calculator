package wooahan.youth.exchange.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import wooahan.youth.exchange.common.exception.BusinessException;
import wooahan.youth.exchange.domain.Money;

class MoneyTest {

    @ParameterizedTest(name = "빈값이나 null이 파라미터로 Moeny객체 생성시 BusinessException 발생")
    @CsvSource(value = {"null,''", "null,null"}, emptyValue = "''", nullValues = "null")
    void createMoney_Exception(BigDecimal amount, String currency) {
        assertThatThrownBy(() -> new Money(amount, currency))
                .isInstanceOf(BusinessException.class);
    }

    @ParameterizedTest(name = "머니 객체 생성 성공")
    @MethodSource("provideSuccessMoneyParameter")
    void createMoney(BigDecimal amount, String currency) {
        //given
        DecimalFormat format = new DecimalFormat("###,###,###.00");
        String exchangeRate = format.format(amount);

        //when
        Money money = new Money(amount, currency);

        //then
        assertThat(money.getCurrency()).isEqualTo(currency);
        assertThat(money.getExchangeRate()).isEqualTo(exchangeRate);
    }

    @DisplayName("송금액에 맞춰 환율을 바꿔준다.")
    @Test
    void exchangeCurrency(){
        //given
        Money beforeCurrency = new Money(BigDecimal.valueOf(34.231), "PHP");

        //when
        Money afterCurrency = beforeCurrency.exchange(100, "USD");

        //then
        assertThat(afterCurrency.getCurrency()).isEqualTo("USD");
        assertThat(afterCurrency.getExchangeRate()).isEqualTo("3,423.10");
    }

    private static Stream<Arguments> provideSuccessMoneyParameter() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(123.2313), "USD"),
                Arguments.of(BigDecimal.valueOf(123.2313), "KRW"),
                Arguments.of(BigDecimal.valueOf(123.2313), "JPY"),
                Arguments.of(BigDecimal.valueOf(123.2313), "PHP")
        );
    }
}