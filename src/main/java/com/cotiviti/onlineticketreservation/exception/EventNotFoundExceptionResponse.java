package com.cotiviti.onlineticketreservation.exception;

public class EventNotFoundExceptionResponse {

    private String eventNotFound;

    public EventNotFoundExceptionResponse(String eventNotFound) {
        this.eventNotFound = eventNotFound;
    }

    public String getEventNotFound() {
        return eventNotFound;
    }

    public void setEventNotFound(String eventNotFound) {
        this.eventNotFound = eventNotFound;
    }
}
