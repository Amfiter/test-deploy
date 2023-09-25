package com.example.testdeploy

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.util.*
import kotlin.system.measureTimeMillis

@Component
class LoggingFilter : OncePerRequestFilter() {

    private companion object {
        val log = KotlinLogging.logger {}
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val requestWrapper = ContentCachingRequestWrapper(request)
        val responseWrapper = ContentCachingResponseWrapper(response)
        val client = getClientIpAddress(requestWrapper)

        val elapsedTime = measureTimeMillis {
            filterChain.doFilter(requestWrapper, responseWrapper)
        }

        log.info(
            "Method: {}, RequestURI: {}, Client: {}, ResponseStatus: {}, ElapsedTimeMillis: {}",
            request.method,
            request.requestURI,
            client,
            response.status,
            elapsedTime
        )
        responseWrapper.copyBodyToResponse()
    }

    fun getClientIpAddress(request: HttpServletRequest): String {
        val xForwardedForHeader = request.getHeader("X-Forwarded-For")
        return if (xForwardedForHeader.isNullOrBlank()) {
            request.remoteAddr
        } else {
            StringTokenizer(xForwardedForHeader, ",").nextToken().trim { it <= ' ' }
        }
    }
}
