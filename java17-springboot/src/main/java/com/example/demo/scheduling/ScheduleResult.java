package com.example.demo.scheduling;

import java.util.List;

public record ScheduleResult(List<Interview> scheduledInterviews, List<Attendee> unscheduledAttendees) {
}
