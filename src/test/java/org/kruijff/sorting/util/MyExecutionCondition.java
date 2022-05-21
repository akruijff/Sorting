package org.kruijff.sorting.util;

import org.junit.jupiter.api.extension.*;

import java.lang.reflect.*;

public class MyExecutionCondition implements ExecutionCondition {
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        Method method = context.getRequiredTestMethod();
        MyAnnotation annotation = method.getDeclaredAnnotation(MyAnnotation.class);
        if (annotation == null)
            return ConditionEvaluationResult.enabled("annotation not used");
        return RunTest.get(annotation.number())
                ? ConditionEvaluationResult.enabled("enabled")
                : ConditionEvaluationResult.disabled("disabled");
    }
}
