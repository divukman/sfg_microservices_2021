package guru.springframework.msscbreweryclient.web.config;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jt on 2019-08-08.
 */
@Component
@ConfigurationProperties(value = "http.client.config.connection", ignoreUnknownFields = false)
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

    private final Integer maxTotal;
    private final Integer maxPerRoute;
    private final Integer connectionTimeout;
    private final Integer socketTimeout;

    public BlockingRestTemplateCustomizer(
            @Value("${sfg.maxtotalconnections}") Integer maxTotal,
            @Value("${sfg.maxconnectionsperroute}") Integer maxPerRoute,
            @Value("${sfg.connectiontimeout}") Integer connectionTimeout,
            @Value("${sfg.sockettimeout}") Integer socketTimeout) {
        this.maxTotal = maxTotal;
        this.maxPerRoute = maxPerRoute;
        this.connectionTimeout = connectionTimeout;
        this.socketTimeout = socketTimeout;
    }

    public ClientHttpRequestFactory clientHttpRequestFactory(){
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(connectionTimeout)
                .setSocketTimeout(socketTimeout)
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}