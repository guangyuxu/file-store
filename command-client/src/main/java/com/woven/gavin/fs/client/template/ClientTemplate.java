package com.woven.gavin.fs.client.template;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ClientTemplate{

    @Value("${file-store.service.url:http://localhost:10001}")
    private String rootUrl;

    private final RestTemplate restTemplate;

    public <T> ResponseEntity<T> postFile(String path, String key, FileSystemResource file, Class<T> responseType) {
        String url = generateURL(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, FileSystemResource> body = new LinkedMultiValueMap<>();
        body.add(key, file);

        HttpEntity<MultiValueMap<String, FileSystemResource>> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.postForEntity(url, requestEntity, responseType);
    }

    public <T> ResponseEntity<T> get(String uri, ParameterizedTypeReference<T> responseType) {
        String url = generateURL(uri);
        return restTemplate.exchange(url, HttpMethod.GET, null, responseType);
    }

    public <T> ResponseEntity<T> delete(String path, Class<T> responseType) {
        String url = generateURL(path);
        return restTemplate.exchange(url, HttpMethod.DELETE, null, responseType);
    }

    private String generateURL(String path) {
        return rootUrl + path;
    }
}
