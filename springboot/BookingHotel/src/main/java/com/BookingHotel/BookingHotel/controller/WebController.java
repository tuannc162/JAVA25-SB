package com.BookingHotel.BookingHotel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WebController<HttpServletResquest> {

    @GetMapping("/")
    public String getHomePage(Model model) {
        return "web/index";
    }
}
