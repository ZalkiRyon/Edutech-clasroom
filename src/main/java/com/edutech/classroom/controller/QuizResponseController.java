package com.edutech.classroom.controller;

import com.edutech.classroom.dto.QuizResponseDTO;
import com.edutech.classroom.service.QuizResponseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz-responses")
@RequiredArgsConstructor
public class QuizResponseController {
    private final QuizResponseService service;

    @GetMapping
    public ResponseEntity<List<QuizResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResponseDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<QuizResponseDTO> create(@Valid @RequestBody QuizResponseDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody QuizResponseDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}