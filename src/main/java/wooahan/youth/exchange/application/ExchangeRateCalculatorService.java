package wooahan.youth.exchange.application;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wooahan.youth.exchange.common.exception.BusinessException;
import wooahan.youth.exchange.common.exception.ErrorCode;
import wooahan.youth.exchange.domain.Money;
import wooahan.youth.exchange.presentation.ExchangeRequestDto.ExchangeRequest;
import wooahan.youth.exchange.presentation.ExchangeResponseDto.ExchangeResponse;

@Service
@RequiredArgsConstructor
public class ExchangeRateCalculatorService {

    public ExchangeResponse calculate(ExchangeRequest request) {
        validateSourceAmount(request);
        Money currencyMoney = new Money(request.getExchangeRate(), request.getSource());
        Money exchangeMoney = currencyMoney.exchange(request.getRemittanceAmount(), request.getSource());
        return new ExchangeResponse(exchangeMoney);
    }

    private void validateSourceAmount(ExchangeRequest request) {
        Integer remittanceAmount = request.getRemittanceAmount();
        Integer amount = getAmount(remittanceAmount);
        if(amount < 0 || amount > 10000){
           throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
    }

    private Integer getAmount(Integer remittanceAmount) {
        return Optional.ofNullable(remittanceAmount)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INPUT_VALUE));
    }
}
