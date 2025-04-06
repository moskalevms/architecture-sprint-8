package ru.moskalevms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/reports", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ReportController {

    @GetMapping
    @PreAuthorize("hasRole('PROTHETIC_USER')")
    public Report getReport() {
        return new Report(
                UUID.randomUUID().toString(),
                LocalDate.now(),
                generateReport()
        );
    }

    private Map<String, Object> generateReport() {
        return Map.of(
                "activity", ThreadLocalRandom.current().nextInt(1000),
                "duration", ThreadLocalRandom.current().nextInt(60),
                "status", List.of("OK", "WARNING", "ERROR").get(new Random().nextInt(3))
        );
    }

    record Report(String id, LocalDate date, Map<String, Object> data) {}

}
