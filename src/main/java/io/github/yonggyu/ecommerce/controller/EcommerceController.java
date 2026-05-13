package io.github.yonggyu.ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
public class EcommerceController {
    @GetMapping("/main")
    @Operation(summary = "Main page")
    public String mainPage() {
        return "Main page";
    }
}