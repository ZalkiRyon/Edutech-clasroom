package com.edutech.classroom.service;

import com.edutech.classroom.dto.CourseQuizQuestionDTO;
import com.edutech.classroom.entity.CourseQuizQuestion;
import com.edutech.classroom.entity.CourseQuiz;
import com.edutech.classroom.exception.ResourceNotFoundException;
import com.edutech.classroom.repository.CourseQuizQuestionRepository;
import com.edutech.classroom.repository.CourseQuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseQuizQuestionService {
    private final CourseQuizQuestionRepository repo;
    private final CourseQuizRepository quizRepo;

    public List<CourseQuizQuestionDTO> findAll() {
        return repo.findAll().stream()
                .map(CourseQuizQuestionDTO::fromEntity)
                .toList();
    }

    public CourseQuizQuestionDTO findById(Integer id) {
        return CourseQuizQuestionDTO.fromEntity(
                repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Pregunta no encontrada"))
        );
    }

    public CourseQuizQuestionDTO create(CourseQuizQuestionDTO dto) {
        CourseQuiz quiz = quizRepo.findById(dto.getQuizId())
                .orElseThrow(() -> new ResourceNotFoundException("Quiz no encontrado"));

        CourseQuizQuestion entity = CourseQuizQuestionDTO.toEntity(dto);
        entity.setQuiz(quiz);

          if (entity.getCreatedAt() == null) {
        entity.setCreatedAt(java.time.Instant.now());
      }

        CourseQuizQuestion saved = repo.save(entity);
        return CourseQuizQuestionDTO.fromEntity(saved);
    }

    public CourseQuizQuestionDTO update(Integer id, CourseQuizQuestionDTO dto) {
        CourseQuizQuestion entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta no encontrada"));

        CourseQuiz quiz = quizRepo.findById(dto.getQuizId())
                .orElseThrow(() -> new ResourceNotFoundException("Quiz no encontrado"));

        entity.setQuiz(quiz);
        entity.setQuestionText(dto.getQuestionText());
        entity.setOptionA(dto.getOptionA());
        entity.setOptionB(dto.getOptionB());
        entity.setOptionC(dto.getOptionC());
        entity.setOptionD(dto.getOptionD());
        entity.setOptionE(dto.getOptionE());
        entity.setCorrectAnswer(dto.getCorrectAnswer());
        entity.setCorrectOption(dto.getCorrectOption());
        entity.setOrderIndex(dto.getOrderIndex());
        

        CourseQuizQuestion updated = repo.save(entity);
        return CourseQuizQuestionDTO.fromEntity(updated);
    }

    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Pregunta no encontrada");
        }
        repo.deleteById(id);
    }
}