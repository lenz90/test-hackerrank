package com.example.demo.scheduling;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScheduleServiceTest {

    private final ScheduleService scheduleService = new ScheduleService();

    @Test
    void scheduleShouldFillAvailableCapacityAndReturnUnscheduledAttendees() {
        List<Attendee> attendees = List.of(
                new Attendee("A1", "Ana"),
                new Attendee("A2", "Luis"),
                new Attendee("A3", "Marta"),
                new Attendee("A4", "Pablo"),
                new Attendee("A5", "Lucia"),
                new Attendee("A6", "Tomas"),
                new Attendee("A7", "Sofia")
        );
        List<Interviewer> interviewers = List.of(new Interviewer("I1", "Irene"));
        List<Room> rooms = List.of(new Room("R1"), new Room("R2"));

        List<Interview> scheduled = scheduleService.scheduleInterviews(attendees, interviewers, rooms);

        assertEquals(6, scheduled.size());
        assertEquals(1, scheduleService.getLastUnscheduledAttendees().size());
        assertEquals("A7", scheduleService.getLastUnscheduledAttendees().get(0).id());

        assertTrue(scheduled.stream().allMatch(interview ->
                !interview.timeSlot().endTime().isAfter(LocalTime.of(18, 0))));
    }

    @Test
    void scheduleShouldNotProduceOverlappingAssignmentsForSameInterviewerAndRoom() {
        List<Attendee> attendees = List.of(
                new Attendee("A1", "Ana"),
                new Attendee("A2", "Luis"),
                new Attendee("A3", "Marta")
        );
        List<Interviewer> interviewers = List.of(
                new Interviewer("I1", "Irene"),
                new Interviewer("I2", "Rene")
        );
        List<Room> rooms = List.of(new Room("R1"));

        List<Interview> scheduled = scheduleService.scheduleInterviews(attendees, interviewers, rooms);

        for (int i = 0; i < scheduled.size(); i++) {
            for (int j = i + 1; j < scheduled.size(); j++) {
                Interview first = scheduled.get(i);
                Interview second = scheduled.get(j);

                if (first.interviewer().equals(second.interviewer())) {
                    assertTrue(!first.timeSlot().overlaps(second.timeSlot()));
                }
                if (first.room().equals(second.room())) {
                    assertTrue(!first.timeSlot().overlaps(second.timeSlot()));
                }
            }
        }
    }
}
