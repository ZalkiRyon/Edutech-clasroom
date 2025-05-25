package com.edutech.classroom.controller;

import com.edutech.classroom.dto.CourseCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course-categories")
@RequiredArgsConstructor

public class CourseCategoryController {
    private final CourseCategoryController service;

    @GetMapping
    public ResponseEntity<List<CourseCategoryDTO>> findAll(){
    return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseCategoryDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(service.findById(id));

    }


}
