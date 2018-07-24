package com.web.Annotation;

import java.lang.annotation.*;

/**
 * @Author: Jiang
 * @Date: Created in 17:19  2018\7\23 0023
 * @Description:
 * @Modified By:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Operation {
    boolean value() default true;
}
