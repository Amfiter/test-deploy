package com.example.testdeploy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestDeployApplication

fun main(args: Array<String>) {
    runApplication<TestDeployApplication>(*args)
}
