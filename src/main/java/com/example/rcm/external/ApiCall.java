package com.example.rcm.external;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * https://alexwohlbruck.github.io/cat-facts/docs/
 */

public class ApiCall {

    /**
      curl --location --request GET 'https://cat-fact.herokuapp.com/facts/random?animal_type=dog&amount=3' \
      --header 'Cookie: connect.sid=s%3Amn1-3YLLTKfduUMxVZFIhP8TH10I3CZ-.BCiX6eN5xI2hwQqrdVyn%2BHPuOVdEActpI6EC%2BCeKoOk'
     * @param args
     */

    public static void main(String [] args){
        RestTemplate restTemplate = new RestTemplate();

        // URL and base path
        String baseUrl = "https://cat-fact.herokuapp.com/facts/random";
        String path = "";

        // Path variable
//        String id = "123";

        // Query parameters
        String param1 = "dog";
        String param2 = "3";

        // Building the URL with path variable and query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + path)
                .queryParam("animal_type", param1)
                .queryParam("amount", param2);

        String urlWithParams = builder
//                .buildAndExpand(id)
                .toUriString();

        // Request headers
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        // Add other headers if needed

        // Request body
//        String requestBody = "{\"key\": \"value\"}";

        // Create HTTP entity with headers and request body
//        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        // Make the POST request
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                urlWithParams,
                HttpMethod.GET,
                new HttpEntity<>(null, null),
                String.class);

        // Get the response body
        String responseBody = responseEntity.getBody();
        System.out.println(responseBody);
    }

}
