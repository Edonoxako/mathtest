package com.edonoxako.mathtest.exercises.check;

/**
 * Created by edono on 15.05.2017.
 */
public class AnswerDto {

    private Integer taskId;
    private String answerFormula;

    public AnswerDto() {
    }

    public AnswerDto(Integer taskId, String answerFormula) {
        this.taskId = taskId;
        this.answerFormula = answerFormula;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getAnswerFormula() {
        return answerFormula;
    }

    public void setAnswerFormula(String answerFormula) {
        this.answerFormula = answerFormula;
    }
}
