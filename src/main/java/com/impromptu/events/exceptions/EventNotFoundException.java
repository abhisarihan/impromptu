package com.impromptu.events.exceptions;

public class EventNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 2071487148955443830L;

    public EventNotFoundException(String eventId) {
        super("No event with id: " + eventId);
    }
}
