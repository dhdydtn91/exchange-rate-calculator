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
        validEmptyAndRange(request);
        Money currencyMoney = new Money(request.getExchangeRate(), request.getReceptionCurrency());
        Money exchangeMoney = currencyMoney.exchange(request.getReceptionAmount(), request.getRemittanceCurrency());
        return new ExchangeResponse(exchangeMoney);
    }

    private void validEmptyAndRange(ExchangeRequest request) {
        Integer remittanceAmount = request.getReceptionAmount();
        Integer amount = validEmptyAmount(remittanceAmount);
        validRangeAmount(amount);
    }

    private void validRangeAmount(Integer amount) {
        if (amount < 0 || amount > 10000) {
            throw new BusinessException(ErrorCode.INVALID_INPUT_VALUE);
        }
    }

    private Integer validEmptyAmount(Integer remittanceAmount) {
        return Optional.ofNullable(remittanceAmount)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INPUT_VALUE));
    }
}
