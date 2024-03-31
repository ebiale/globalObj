package com.example.globalobject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class GlobalObjectController {

    @GetMapping("/")
    public String home() {
        return "¡Bienvenido a mi aplicación Spring Boot!";
    }

    @PostMapping("/addSearch")
    public void addSearch(@RequestBody String searchTerm) {
        SearchService.addSearch(searchTerm);
    }

    @GetMapping("/getSearchHistory")
    public List<SearchEntry> getSearchHistory(@RequestParam(required = false) Integer maxItems) {
        if (maxItems != null) {
            return SearchService.getSearchHistory(maxItems);
        } else {
            return SearchService.getSearchHistory();
        }
    }
}
