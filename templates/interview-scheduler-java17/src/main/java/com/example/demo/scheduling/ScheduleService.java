package com.example.demo.scheduling;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScheduleService {

    private static final LocalTime WORKDAY_START = LocalTime.of(9, 0);
    private static final LocalTime WORKDAY_END = LocalTime.of(18, 0);
    private static final int INTERVIEW_DURATION_HOURS = 2;

    private List<Attendee> lastUnscheduledAttendees = List.of();

    public List<Interview> scheduleInterviews(
            List<Attendee> attendees,
            List<Interviewer> interviewers,
            List<Room> rooms
    ) {
        return schedule(attendees, interviewers, rooms).scheduledInterviews();
    }

    public ScheduleResult schedule(
            List<Attendee> attendees,
            List<Interviewer> interviewers,
            List<Room> rooms
    ) {
        if (attendees == null || attendees.isEmpty() || interviewers == null || interviewers.isEmpty() || rooms == null
                || rooms.isEmpty()) {
            lastUnscheduledAttendees = attendees == null ? List.of() : List.copyOf(attendees);
            return new ScheduleResult(List.of(), lastUnscheduledAttendees);
        }

        List<TimeSlot> timeSlots = generateValidTimeSlots();
        List<Interview> scheduled = new ArrayList<>();

        int attendeeIndex = 0;
        for (TimeSlot slot : timeSlots) {
            for (Interviewer interviewer : interviewers) {
                for (Room room : rooms) {
                    if (attendeeIndex >= attendees.size()) {
                        break;
                    }
                    if (isAvailable(interviewer, room, slot, scheduled)) {
                        scheduled.add(new Interview(attendees.get(attendeeIndex), interviewer, room, slot));
                        attendeeIndex++;
                    }
                }
                if (attendeeIndex >= attendees.size()) {
                    break;
                }
            }
            if (attendeeIndex >= attendees.size()) {
                break;
            }
        }

        lastUnscheduledAttendees = attendeeIndex >= attendees.size()
                ? List.of()
                : List.copyOf(attendees.subList(attendeeIndex, attendees.size()));

        return new ScheduleResult(List.copyOf(scheduled), lastUnscheduledAttendees);
    }

    public List<Attendee> getLastUnscheduledAttendees() {
        return lastUnscheduledAttendees;
    }

    List<TimeSlot> generateValidTimeSlots() {
        List<TimeSlot> candidateSlots = List.of(
                new TimeSlot(LocalTime.of(9, 0), LocalTime.of(11, 0)),
                new TimeSlot(LocalTime.of(11, 0), LocalTime.of(13, 0)),
                new TimeSlot(LocalTime.of(15, 0), LocalTime.of(17, 0)),
                new TimeSlot(LocalTime.of(17, 0), LocalTime.of(19, 0))
        );

        List<TimeSlot> validSlots = new ArrayList<>();
        for (TimeSlot slot : candidateSlots) {
            if (!slot.endTime().isAfter(WORKDAY_END) && !slot.startTime().isBefore(WORKDAY_START)) {
                validSlots.add(slot);
            }
        }
        return validSlots;
    }

    private boolean isAvailable(Interviewer interviewer, Room room, TimeSlot targetSlot, List<Interview> scheduled) {
        Set<TimeSlot> interviewerSlots = new HashSet<>();
        Set<TimeSlot> roomSlots = new HashSet<>();

        for (Interview interview : scheduled) {
            if (interview.interviewer().equals(interviewer)) {
                interviewerSlots.add(interview.timeSlot());
            }
            if (interview.room().equals(room)) {
                roomSlots.add(interview.timeSlot());
            }
        }

        return interviewerSlots.stream().noneMatch(slot -> slot.overlaps(targetSlot))
                && roomSlots.stream().noneMatch(slot -> slot.overlaps(targetSlot));
    }
}
