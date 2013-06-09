package com.impromptu.events;

import java.util.List;


public interface EventSearchService {
    
    List<Event> findByNameOrDescription(String text);
    
}
