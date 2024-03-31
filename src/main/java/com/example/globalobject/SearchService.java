package com.example.globalobject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SearchService {
    private static List<SearchEntry> searchHistory = new ArrayList<>();

    public static void addSearch(String searchTerm) {
        SearchEntry searchEntry = new SearchEntry(searchTerm);
        searchHistory.add(searchEntry);
    }

    public static List<SearchEntry> getSearchHistory() {
        return searchHistory;
    }

    public static List<SearchEntry> getSearchHistory(int maxItems) {
        int startIndex = Math.max(0, searchHistory.size() - maxItems);
        return searchHistory.subList(startIndex, searchHistory.size());
    }
}
