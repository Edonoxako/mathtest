package com.edonoxako.mathtest.exercises.data;

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

        Exercise exercise1 = repository.getExercise(0);
        Exercise exercise2 = repository.getExercise(1);

        Assert.assertEquals("dummy-1", exercise1.getDefinition());
        Assert.assertEquals("(a - b) * (a + b)", exercise1.getExpression());
        Assert.assertEquals("dummy-2", exercise2.getDefinition());
        Assert.assertEquals("a^2 + 2*a*b + b^2", exercise2.getExpression());
    }
}
