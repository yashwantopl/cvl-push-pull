package com.capitaworld.service.loans.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MyErrorController implements ErrorController {
    /*@RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div><body></html>",
                statusCode, exception==null? "N/A": exception.getMessage());
    }*/

    @RequestMapping(value = {"/error"})
    public ModelAndView handleError(HttpServletRequest httpServletRequest,HttpServletResponse response) {
        Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        System.out.println(response.getStatus());
        ModelAndView modelAndView = new ModelAndView();
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                modelAndView.setViewName("404");
                modelAndView.setStatus(HttpStatus.NOT_FOUND);
                return modelAndView;
            }else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                modelAndView.setViewName("500");
                modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                return modelAndView;
            }else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
                modelAndView.setViewName("405");
                modelAndView.setStatus(HttpStatus.METHOD_NOT_ALLOWED);
                return modelAndView;
            }
        }
        modelAndView.setViewName("404");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
