package com.edutech.classroom;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-course-category", url = "http://localhost:9003/api/course-categories")
public interface CourseCategoryClient {
    @GetMapping("/{id}")
    Object getCourseCategoryById(@PathVariable("id") String id);

}
