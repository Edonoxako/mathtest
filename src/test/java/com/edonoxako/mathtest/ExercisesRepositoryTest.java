package com.edonoxako.mathtest;

import com.edonoxako.mathtest.exercises.data.Exercise;
import com.edonoxako.mathtest.exercises.data.FileRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by edono on 29.05.2017.
 */
public class ExercisesRepositoryTest {

    @Test
    public void testGetExerciseFromFile() throws Exception {
        FileRepository repository = new FileRepository(new ObjectMapper());

        Exercise exercise = repository.getExercise(0);

        Assert.assertEquals("dummy definition", exercise.getDefinition());
        Assert.assertEquals("(a - b) * (a + b)", exercise.getExpression());
    }
}
