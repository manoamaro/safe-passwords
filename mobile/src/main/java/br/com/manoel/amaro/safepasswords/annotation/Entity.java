package br.com.manoel.amaro.safepasswords.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by manoel on 18/10/14.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
    String tableName() default "";
}
