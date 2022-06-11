package wooahan.youth.exchange.infrastructure;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import wooahan.youth.exchange.application.CurrencyDataApiCaller;
import wooahan.youth.exchange.application.CurrencyDataDto;

@Component
@RequiredArgsConstructor
public class CurrencyDataApiCallerImpl implements CurrencyDataApiCaller {

    @Value("${currencyData.accessKey}")
    private final String accessKey;
    @Value("${currencyData.baseUrl}")
    private final String url;
    @Value("${currencyData.source}")
    private final String source;
    @Value("${currencyData.currencies}")
    private final String currencies;
    private final RestTemplate restTemplate;


    @Override
    public CurrencyDataDto call() {
        return restTemplate.exchange(
                        createEndPoint(),
                        HttpMethod.GET,
                        new HttpEntity<>(createHeader()),
                        CurrencyDataDto.class)
                .getBody();
    }

    private URI createEndPoint() {
        return UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("source", source)
                .queryParam("currencies", currencies)
                .build()
                .toUri();
    }

    private HttpHeaders createHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("apikey", accessKey);
        return httpHeaders;
    }

}
