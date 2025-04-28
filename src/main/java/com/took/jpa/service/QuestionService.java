package com.took.jpa.service;

import com.took.jpa.dto.QuestionDto;
import com.took.jpa.entity.Question;
import com.took.jpa.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public void write(QuestionDto questionDto) {
//        Question question = Question.builder()
//                .title(questionDto.getTitle())
//                .content(questionDto.getContent())
//                .build();

        Question question = QuestionDto.toEntity(questionDto);
        questionRepository.save(question);
    }

    public List<Question> list() {
        //orm 오브젝트와 매핑해주는 것
        return questionRepository.findAll();
    }

    public Question view(Integer id) {
        Optional<Question>  optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            return optionalQuestion.get();
        }
        return null;
    }

//    public Question modify(QuestionDto questionDto) {
//        //update 쿼리가 나가야 한다.
//        Optional<Question> optionalQuestion = questionRepository.findById(questionDto.getId());
//        if (optionalQuestion.isPresent()) {
//            return questionRepository.save(QuestionDto.toEntity(questionDto));
//        }
////        return questionRepository.save(QuestionDto.toEntity(questionDto));
//        return null;
//    }

@Transactional
    public Question modify(QuestionDto questionDto) {
        Question question = questionRepository.findById(questionDto.getId()).orElseThrow();
        question.changeTitle(questionDto.getTitle());
        question.changeContent(questionDto.getContent());
//        optionalQuestion.ifPresent(question -> {
//            Question updateQuestion = optionalQuestion.get();
//            updateQuestion = QuestionDto.toEntity(questionDto);
//        });
        return null;
    }
}
