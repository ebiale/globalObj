package com.example.globalobject;

import java.time.LocalDateTime;

public class SearchEntry {
    private String searchTerm;
    private LocalDateTime timestamp;

    public SearchEntry(String searchTerm) {
        this.searchTerm = searchTerm;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y setters (opcional)
    public String getSearchTerm() {
        return searchTerm;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
