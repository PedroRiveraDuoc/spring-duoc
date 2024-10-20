package com.example.spring_duoc.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

  @RequestMapping("/error")
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String handleError(HttpServletRequest request, Model model) {
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    String errorMessage = (String) request.getAttribute("javax.servlet.error.message");

    model.addAttribute("statusCode", statusCode);
    model.addAttribute("errorMessage", errorMessage != null ? errorMessage : "An unexpected error occurred");

    return "error";
  }
}
