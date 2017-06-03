package com.edonoxako.mathtest.exercises.check;

import com.edonoxako.mathtest.evaluator.Evaluator;
import com.edonoxako.mathtest.exercises.data.Exercise;
import com.edonoxako.mathtest.exercises.data.ExercisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by edono on 24.05.2017.
 */
@Component
public class ExpressionSource {

    private ExercisesRepository repository;

    private Map<String, Evaluator> expressionsCache;

    @Autowired
    public ExpressionSource(ExercisesRepository repository) {
        this.repository = repository;
        this.expressionsCache = new HashMap<>();
    }

    public Evaluator getExpression(Integer taskId) {
        Evaluator expression = expressionsCache.get(taskId);

        if (expression == null) {
            expression = getAndSaveInCache(taskId);
        }

        return expression;
    }

    private Evaluator getAndSaveInCache(Integer taskId) {
        Exercise exercise = repository.getExercise(taskId);
        String expression = exercise.getExpression();
        if (expression != null) {
            return Evaluator.from(expression);
        } else {
            //todo: throw exception if exercise is without expression
            return null;
        }
    }
}
