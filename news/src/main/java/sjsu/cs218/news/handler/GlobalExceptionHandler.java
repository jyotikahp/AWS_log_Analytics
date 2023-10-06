//SJSU CS 218 SPRING 2023 TEAM4
package sjsu.cs218.news.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import sjsu.cs218.news.exception.CrashServerException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(CrashServerException.class)
    public void handleCrashServer(HttpServletResponse response,Exception e) throws IOException, InterruptedException{
        logger.error(e.getMessage());
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
        System.exit(1);
    }
}
