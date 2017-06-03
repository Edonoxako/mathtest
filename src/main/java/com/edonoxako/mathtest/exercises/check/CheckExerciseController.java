package com.edonoxako.mathtest.exercises.check;

import com.edonoxako.mathtest.exercises.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by edono on 15.05.2017.
 */
@RestController
public class CheckExerciseController extends BaseController {

    private CheckAnswerService service;

    @Autowired
    public CheckExerciseController(CheckAnswerService service) {
        this.service = service;
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<CheckResultDto> checkAnswer(@RequestBody AnswerDto answerDto) {
        return ResponseEntity.ok(new CheckResultDto(service.check(answerDto.getTaskId(), answerDto.getAnswerFormula())));
    }

}
