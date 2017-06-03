package com.edonoxako.mathtest.exercises.data;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by edono on 13.05.2017.
 */
public class Exercise {

    private Integer id;
    @JsonView(ExerciseRestView.UI.class)
    private String definition;
    private String expression;

    public Exercise() {}

    public Exercise(Integer id, String definition, String expression) {
        this.id = id;
        this.definition = definition;
        this.expression = expression;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
