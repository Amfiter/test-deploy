package com.example.testdeploy.controllers

import com.example.testdeploy.services.TestService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/public/api")
class TestController(
    private val testService: TestService
) {

    @GetMapping("/sum")
    fun getSum(
        @RequestParam val1: Int,
        @RequestParam val2: Int
    ): ResponseEntity<Int> {
        return ResponseEntity.ok(testService.testMethod(val1,val2))
    }
}