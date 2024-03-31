package com.example.globalobject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class GlobalObjectController {

    private static final String MEDIUM_BASE_URL = "https://medium.com/feed/";

    @GetMapping("/")
    public String home() {
        return "Welcome to my BE app";
    }

    @GetMapping("/getSearchHistory")
    public List<SearchEntry> getSearchHistory(@RequestParam(required = false) Integer maxItems) {
        if (maxItems != null) {
            return SearchService.getSearchHistory(maxItems);
        } else {
            return SearchService.getSearchHistory();
        }
    }

    @GetMapping("/fetchMediumData")
    public ResponseEntity<String> fetchMediumData(@RequestParam String mediumSource) {
        String mediumUrl = MEDIUM_BASE_URL + mediumSource;

        RestTemplate restTemplate = new RestTemplate();
        try {
            String mediumResponse = restTemplate.getForObject(mediumUrl, String.class);
            SearchService.addSearch(mediumSource);
            return ResponseEntity.ok(mediumResponse);
        } catch (HttpClientErrorException.NotFound exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or account not found: " + mediumSource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the request.");
        }
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleNotFoundException(HttpClientErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body("Error processing the request: " + ((HttpStatus) ex.getStatusCode()).getReasonPhrase());
    }
}