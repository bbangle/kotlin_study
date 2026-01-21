package com.example.mvc.advice

import com.example.mvc.controller.exception.ExceptionApiController
import com.example.mvc.controller.put.PutApiController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

//@RestControllerAdvice(basePackageClasses = [PutApiController::class]) //특정 클래스에 바인딩
//@RestControllerAdvice
class GlobalControllerAdvice {

    @ExceptionHandler(RuntimeException::class)
    fun exception(e : RuntimeException) : String {
        return "Service Error"
    }

    @ExceptionHandler(IndexOutOfBoundsException::class)
    fun indexOutOfBoundsException(e : IndexOutOfBoundsException) : ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
    }
}