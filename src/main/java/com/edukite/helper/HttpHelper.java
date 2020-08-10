package com.edukite.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class HttpHelper {
    public static <T> T getResponse(String url, Class<T> tClass) throws IOException {
        HttpGet request = new HttpGet(url);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(request);
        String content = EntityUtils.toString(response.getEntity());

        ObjectMapper mapper = new ObjectMapper();
        T dto = mapper.readValue(content, tClass);
        return dto;
    }
}
