package org.kruijff.sorting.util;

import org.junit.jupiter.api.extension.*;

import java.lang.annotation.*;

@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.METHOD)
@ExtendWith (MyExecutionCondition.class)
public @interface MyAnnotation {
    int number();
}
