package com.edonoxako.mathtest.exercises.data;

import org.springframework.stereotype.Repository;

/**
 * Created by edono on 13.05.2017.
 */
public class DummyRepository implements ExercisesRepository {

    private static final String DUMMY_EXERCISE = "Дано выражение `a^2 - b^2`. Разверните его используя формулу сокращённого умножения";
    private static final String DUMMY_EXPRESSION = "(a - b) * (a + b)";

    @Override
    public Exercise getExercise(int exerciseId) {
        return new Exercise(exerciseId, DUMMY_EXERCISE, DUMMY_EXPRESSION);
    }

}
