package com.example.demo.scheduling;

import java.util.Objects;

public record Interview(Attendee attendee, Interviewer interviewer, Room room, TimeSlot timeSlot) {

    public Interview {
        Objects.requireNonNull(attendee, "attendee is required");
        Objects.requireNonNull(interviewer, "interviewer is required");
        Objects.requireNonNull(room, "room is required");
        Objects.requireNonNull(timeSlot, "timeSlot is required");
    }
}
