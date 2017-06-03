package com.edonoxako.mathtest.exercises.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by edono on 29.05.2017.
 */
public class ExercisesRepositoryTest {

    private FileRepository fileRepository;

    @Before
    public void setUp() throws Exception {
        fileRepository = new FileRepository(new ObjectMapper());
    }

    @Test
    public void testGetExerciseFromFile() throws Exception {
        Exercise exercise1 = fileRepository.getExercise(0);
        Exercise exercise2 = fileRepository.getExercise(1);

        Assert.assertEquals("dummy-1", exercise1.getDefinition());
        Assert.assertEquals("(a - b) * (a + b)", exercise1.getExpression());
        Assert.assertEquals("dummy-2", exercise2.getDefinition());
        Assert.assertEquals("a^2 + 2*a*b + b^2", exercise2.getExpression());
    }

    @Test
    public void testGetAllExercisesFromFile() throws Exception {
        List<Exercise> expected = Arrays.asList(
                new Exercise(0, "dummy-1", "(a - b) * (a + b)"),
                new Exercise(1, "dummy-2", "a^2 + 2*a*b + b^2"),
                new Exercise(2, "dummy-3", "(a - b)^2")
        );

        List<Exercise> actual = fileRepository.getAllExercises();

        Assert.assertEquals(expected, actual);
    }
}
