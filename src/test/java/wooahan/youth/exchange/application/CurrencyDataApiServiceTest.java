package wooahan.youth.exchange.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wooahan.youth.exchange.presentation.ExchangeRequestDto.CurrencyDataRequest;
import wooahan.youth.exchange.presentation.ExchangeResponseDto.CurrencyDataDto;


@ExtendWith(MockitoExtension.class)
class CurrencyDataApiServiceTest {

    @InjectMocks
    private CurrencyDataApiService currencyDataApiService;
    @Mock
    private CurrencyDataApiCaller currencyDataApiCaller;

    @DisplayName("CurrencyDateApi에서 USD에 대한 KRW, JPY, PHP의 수취금액을 가져온다.")
    @Test
    void getCurrencyData() {
        CurrencyDataRequest requestDto = new CurrencyDataRequest("USD", "KRW,PHP,JPY");

        CurrencyDataDto responseDto = new CurrencyDataDto(
                new Quotes(1231.1234, 2344.2323, 1111.111),
                "USD",
                true
        );

        given(currencyDataApiCaller.call(requestDto)).willReturn(responseDto);

        CurrencyDataDto currencyData = currencyDataApiService.getCurrencyData(requestDto);
        Quotes quotes = currencyData.getQuotes();
        assertThat(currencyData.getSource()).isEqualTo(requestDto.getSource());
        assertThat(quotes.getUSDJPY()).isEqualTo(1231.1234);
        assertThat(quotes.getUSDKRW()).isEqualTo(2344.2323);
        assertThat(quotes.getUSDPHP()).isEqualTo(1111.111);
    }

}