package com.avalon.ms.common.http;

import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianConnectionFactory;
import com.caucho.hessian.client.HessianProxyFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URL;

/**
 * @description
 * @author saberey
 * @date 2020/10/19 19:42
 * @version 1.0
 */
public class HttpClientConnectionFactory implements HessianConnectionFactory {

    private HttpClient httpClient;

    @Override
    public void setHessianProxyFactory(HessianProxyFactory factory) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout((int) factory.getConnectTimeout())
                .setSocketTimeout((int) factory.getReadTimeout())
                .build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    @Override
    public HessianConnection open(URL url) throws IOException {
        HttpClientConnection httpClientConnection = new HttpClientConnection(httpClient, url);

        return httpClientConnection;
    }
}
