package com.chengziting.razor.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by user on 2018/7/13.
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class NoPermissionException extends RuntimeException {
}
