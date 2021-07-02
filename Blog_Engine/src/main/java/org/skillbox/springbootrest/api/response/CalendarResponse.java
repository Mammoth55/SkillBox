package org.skillbox.springbootrest.api.response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CalendarResponse {

    private final Set<Integer> years = new TreeSet<>();
    private final Map<String, Integer> posts = new HashMap<>();

    public Set<Integer> getYears() {
        return years;
    }

    public Map<String, Integer> getPosts() {
        return posts;
    }
}