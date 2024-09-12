package me.niloybiswas.elasticsearch_poc.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.niloybiswas.elasticsearch_poc.exception.ElasticSearchSSLException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class HttpClientConfigCallbackImpl implements HttpClientConfigCallback {

    private final Environment environment;

    @SneakyThrows
    @Override
    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
        try {
            final String username = environment.getProperty("elasticsearch-user-name");
            final String password = environment.getProperty("elasticsearch-password");
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            final UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(
                    Objects.requireNonNull(username),
                    password
            );
            credentialsProvider.setCredentials(AuthScope.ANY, usernamePasswordCredentials);
            httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);

            final String trustMaterialLocation = environment.getProperty("elasticsearch-trust-location");
            final File trustMaterialFile = new File(Objects.requireNonNull(trustMaterialLocation));
            final SSLContextBuilder sslContextBuilder = SSLContexts.custom().loadTrustMaterial(
                    trustMaterialFile,
                    Objects.requireNonNull(password).toCharArray()
            );
            final SSLContext sslContext = sslContextBuilder.build();
            httpAsyncClientBuilder.setSSLContext(sslContext);
        } catch (Exception ex) {
            throw new ElasticSearchSSLException(ex.getMessage());
        }
        return httpAsyncClientBuilder;
    }

}
