package com.amcones.nocv.tencentapi;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpUtils {
    /**
     * send http request util
     * @return
     */
    public String getData() throws Exception{
        RequestConfig requestConfig= RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .build();
        CloseableHttpClient httpClient=null;
        HttpGet request=null;
        CloseableHttpResponse response=null;
        try {
            httpClient = HttpClients.createDefault();
            request = new HttpGet("https://c.m.163.com/ug/api/wuhan/app/data/list-total");
            request.setConfig(requestConfig);
            response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "utf-8");
            }
        }
        finally {

            if(response!=null)response.close();
            if(request!=null)request.releaseConnection();
            if(httpClient!=null)httpClient.close();
        }
        return null;
    }
}
