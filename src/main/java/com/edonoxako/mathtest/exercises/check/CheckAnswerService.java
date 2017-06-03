package com.edonoxako.mathtest.exercises.check;

import com.edonoxako.mathtest.evaluator.Evaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by edono on 15.05.2017.
 */
@Service
public class CheckAnswerService {

    private ExpressionSource source;

    @Autowired
    public CheckAnswerService(ExpressionSource source) {
        this.source = source;
    }

    public CheckResult check(Integer taskId, String formulaToCheck) {
        Evaluator answerExpression = source.getExpression(taskId);
        return answerExpression.isEqual(formulaToCheck) ? CheckResult.SUCCESS : CheckResult.FAILURE;
    }
}
