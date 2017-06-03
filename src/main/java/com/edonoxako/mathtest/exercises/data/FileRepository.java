package com.edonoxako.mathtest.exercises.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by edono on 29.05.2017.
 */
public class FileRepository implements ExercisesRepository {

    public static final String EXERCISES_FILE = "exercises.json";

    private ObjectMapper jsonMapper;

    @Autowired
    public FileRepository(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Override
    public Exercise getExercise(int exerciseId) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL exercisesResource = classLoader.getResource(EXERCISES_FILE);

            if (exercisesResource == null) return null;

            File file = new File(exercisesResource.getFile());
            CollectionType exercisesCollectionType = jsonMapper.getTypeFactory().constructCollectionType(List.class, Exercise.class);
            List<Exercise> exercises = jsonMapper.readValue(file, exercisesCollectionType);

            return exercises.stream()
                    .filter(exercise -> exercise.getId() == exerciseId)
                    .findFirst()
                    .orElse(null);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
