package com.chengziting.razor.core.annotations;

import java.lang.annotation.*;

/**
 * Created by user on 2018/5/3.
 */
@Target(value = {ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WithoutAuthorize {
}
