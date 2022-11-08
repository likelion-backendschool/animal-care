package com.codelion.animalcare.domain.appointment.controller;

import com.codelion.animalcare.domain.appointment.service.AppointmentCommandService;
import com.codelion.animalcare.domain.appointment.service.AppointmentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("usr/appointment")
public class AppointmentRestController {
    private final AppointmentQueryService appointmentQueryService;

    @GetMapping("times")
    public ResponseEntity<List<LocalDateTime>>  findDates(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam("doctorId") Long doctorId
    ){
        return ResponseEntity
                .ok(appointmentQueryService.findDateTimesByDateAndDoctor(date, doctorId));
    }
}
