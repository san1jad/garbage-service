package com.garbage.controller;

import com.common.dto.garbage.ElectronicsGarbageDTO;
import com.common.exception.HandledInternalServerException;
import com.common.vo.garbage.ElectronicsGarbageVO;
import com.garbage.service.ElectronicsGarbageService;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/electronics-garbage")
@Slf4j
@Validated
public class ElectronicsGarbageController {

    private final ElectronicsGarbageService electronicsGarbageService;
    @Autowired
    private ObservationRegistry registry;

    @Autowired
    public ElectronicsGarbageController(ElectronicsGarbageService electronicsGarbageService) {
        this.electronicsGarbageService = electronicsGarbageService;
    }

    @GetMapping
    @Observed(
            name = "GARBAGE",
            contextualName = "Garbage service --> get all",
            lowCardinalityKeyValues = {"GarbageGetAll","V1"}
    )
    public ResponseEntity<List<ElectronicsGarbageVO>> getAllElectronicsGarbage() {
        return Optional.ofNullable(electronicsGarbageService.getAllElectronicsGarbage())
                .map(electronicsGarbageVOS ->  new ResponseEntity<>(electronicsGarbageVOS, HttpStatus.OK))
                .orElseThrow(() -> new HandledInternalServerException("Garbage form data not found any"));
    }

    @GetMapping("/{id}")
    @Observed(
            name = "GARBAGE",
            contextualName = "Garbage service --> get by id",
            lowCardinalityKeyValues = {"GarbageGetById","V1"}
    )
    public ResponseEntity<ElectronicsGarbageVO> getElectronicsGarbageById(@PathVariable Long id) {
        return new ResponseEntity<>(electronicsGarbageService.getElectronicsGarbageById(id), HttpStatus.OK);
    }

    @PostMapping
    @Observed(
            name = "GARBAGE",
            contextualName = "Garbage service --> create new",
            lowCardinalityKeyValues = {"GarbageCreateNew","V1"}
    )
    public ResponseEntity<ElectronicsGarbageVO> createElectronicsGarbage(@Valid @RequestBody ElectronicsGarbageDTO electronicsGarbageDTO) {
        return Optional.ofNullable(electronicsGarbageService.createElectronicsGarbage(electronicsGarbageDTO))
                .map(electronicsGar -> new ResponseEntity<>(electronicsGar, HttpStatus.CREATED))
                .orElseThrow(() -> new HandledInternalServerException("Error while save garbage form data"));
    }

    @PutMapping("/{id}")
    @Observed(
            name = "GARBAGE",
            contextualName = "Garbage service --> update by id",
            lowCardinalityKeyValues = {"GarbageUpdate","V1"}
    )
    public ResponseEntity<ElectronicsGarbageVO> updateElectronicsGarbage(@PathVariable Long id,@Valid @RequestBody ElectronicsGarbageDTO electronicsGarbageDTO) {

        return Optional.ofNullable(electronicsGarbageService.updateElectronicsGarbage(id, electronicsGarbageDTO))
                .map(electronicsGarbageVO -> new ResponseEntity<>(electronicsGarbageVO, HttpStatus.OK))
                .orElseThrow(() -> new HandledInternalServerException("Error while updating garbage details"));
    }
}

