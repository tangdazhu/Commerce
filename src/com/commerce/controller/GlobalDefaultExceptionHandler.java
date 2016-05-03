package com.commerce.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/*
 * Handle all the exception here.
 */
@EnableWebMvc
@ControllerAdvice
class GlobalDefaultExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "exception";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception exception) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class) != null)
            throw exception;

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView("exception");
		java.util.Date date = new java.util.Date();

		mav.addObject("timestamp", new Timestamp(date.getTime()));
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("exception");

        return mav;
    }
}