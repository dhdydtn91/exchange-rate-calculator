package wooahan.youth.exchange.infrastructure;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import wooahan.youth.exchange.application.CurrencyDataApiCaller;
import wooahan.youth.exchange.presentation.ExchangeRequestDto.CurrencyDataRequest;
import wooahan.youth.exchange.presentation.ExchangeResponseDto.CurrencyDataDto;

@Component
public class CurrencyDataApiCallerImpl implements CurrencyDataApiCaller {

    @Value("${currencyData.accessKey}")
    private String accessKey;
    @Value("${currencyData.baseUrl}")
    private String url;

    private RestTemplate restTemplate;

    @Autowired
    public CurrencyDataApiCallerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CurrencyDataDto call(CurrencyDataRequest request) {
        return restTemplate.exchange(
                        createEndPoint(request),
                        HttpMethod.GET,
                        new HttpEntity<>(createHeader()),
                        CurrencyDataDto.class)
                .getBody();
    }

    private URI createEndPoint(CurrencyDataRequest request) {
        return UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("source", request.getSource())
                .queryParam("currencies", request.getCurrencies())
                .build()
                .toUri();
    }

    private HttpHeaders createHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("apikey", accessKey);
        return httpHeaders;
    }
}
