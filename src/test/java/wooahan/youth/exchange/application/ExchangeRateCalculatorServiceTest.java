package wooahan.youth.exchange.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import wooahan.youth.exchange.presentation.ExchangeRequestDto.ExchangeRequest;
import wooahan.youth.exchange.presentation.ExchangeResponseDto.ExchangeResponse;

class ExchangeRateCalculatorServiceTest {

    private ExchangeRateCalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new ExchangeRateCalculatorService();
    }

    @DisplayName("정상적인 송금액 계산")
    @Test
    void calculate() {
        ExchangeRequest request = new ExchangeRequest(
                "KRW",
                "USD",
                1000,
                1234.123);
        ExchangeResponse response = calculatorService.calculate(request);

        assertThat(response.getCurrency()).isEqualTo("KRW");
        assertThat(response.getExchangeAmount()).isEqualTo("1,234,123.00");
    }

    @ParameterizedTest(name = "receptionAmount의 값이 null이거나 0보다 작거나 10000보다 클때 예외발생")
    @MethodSource("createExceptionRequest")
    void calculate_exception(ExchangeRequest request) {
        assertThatThrownBy(() -> calculatorService.calculate(request));
    }

    private static Stream<Arguments> createExceptionRequest() {
        return Stream.of(
                Arguments.of(new ExchangeRequest("KRW",
                        "USD",
                        null,
                        1234.123)),
                Arguments.of(new ExchangeRequest("KRW",
                        "USD",
                        -1,
                        1234.123)),
                Arguments.of(new ExchangeRequest("KRW",
                        "USD",
                        10001,
                        1234.123))
        );
    }
}