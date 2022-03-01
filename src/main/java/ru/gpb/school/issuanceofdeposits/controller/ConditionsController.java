package ru.gpb.school.issuanceofdeposits.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gpb.school.issuanceofdeposits.exception.NotFoundException;
import ru.gpb.school.issuanceofdeposits.model.dto.ConditionsDto;
import ru.gpb.school.issuanceofdeposits.service.ConditionsService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class ConditionsController {
    private final ConditionsService conditionsService;

    // получить все условия
    @GetMapping("/conditions")
    public ResponseEntity<Collection<ConditionsDto>> getAllConditions() {
        return ResponseEntity.ok(conditionsService.findAll());
    }

    // получить условеи по id
    @GetMapping("/conditions/{id}")
    public ResponseEntity<ConditionsDto> getAllConditions(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(conditionsService.getById(id));
    }

    // добавить новое условие для депозита
    @PostMapping("/conditions")
    public ResponseEntity<ConditionsDto> addConditions(@RequestBody ConditionsDto conditions) {
        return ResponseEntity.ok(conditionsService.addCondition(conditions));

    }
}
