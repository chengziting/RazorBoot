package com.chengziting.razor.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by user on 2018/7/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE_PARAMETER})
public @interface RazorModule {
    RazorModulePolicy module() default RazorModulePolicy.None;
}
