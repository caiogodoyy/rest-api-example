package com.caio.alura.api.model.meeting;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record ScheduleMeetingData(
                Long teacherId,
                @NotNull Long studentId,
                @NotNull @Future LocalDateTime dateTime,
                String department) {

}
