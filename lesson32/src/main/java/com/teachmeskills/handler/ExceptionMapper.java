package com.teachmeskills.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
@Slf4j
public class ExceptionMapper {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(final Exception exception) {
        ModelAndView model = new ModelAndView();
        log.error("Unexpected Exception: ", exception);
        model.addObject("message", "Internal server error");
        model.setViewName("error");
        return model;
    }

    @ExceptionHandler(SQLException.class)
    public ModelAndView handleSqlException(HttpServletRequest request, Exception ex) {
        ModelAndView model = new ModelAndView();
        log.error("SQL Exception :: URL=" + request.getRequestURL(), ex);
        model.addObject("message", "Database error");
        model.setViewName("databaseError");
        return model;
    }
}
