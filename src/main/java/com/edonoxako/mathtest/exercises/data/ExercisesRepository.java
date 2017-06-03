package com.edonoxako.mathtest.exercises.data;

import java.util.List;

/**
 * Created by edono on 29.05.2017.
 */
public interface ExercisesRepository {
    Exercise getExercise(int exerciseId);
    List<Exercise> getAllExercises();
}
