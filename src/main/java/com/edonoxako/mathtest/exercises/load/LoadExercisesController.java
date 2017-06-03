package com.edonoxako.mathtest.exercises.load;

import com.edonoxako.mathtest.exercises.BaseController;
import com.edonoxako.mathtest.exercises.data.Exercise;
import com.edonoxako.mathtest.exercises.data.ExerciseRestView;
import com.edonoxako.mathtest.exercises.data.ExercisesRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by edono on 13.05.2017.
 */
@RestController
public class LoadExercisesController extends BaseController {

    private ExercisesRepository repository;

    @Autowired
    public LoadExercisesController(ExercisesRepository repository) {
        this.repository = repository;
    }

    @JsonView(ExerciseRestView.UI.class)
    @RequestMapping("/load/{exerciseId}")
    public Exercise getExercise(@PathVariable int exerciseId) {
        return repository.getExercise(exerciseId);
    }

    @RequestMapping("/load")
    public List<Exercise> getAllExercises() {
        return repository.getAllExercises();
    }
}
