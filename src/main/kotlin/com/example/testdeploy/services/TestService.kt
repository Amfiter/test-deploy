package com.example.testdeploy.services

import org.springframework.stereotype.Service

@Service
class TestService {

    fun testMethod(val1: Int, val2: Int): Int {
        return val1 + val2
    }
}
