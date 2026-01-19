package com.example.mvc.controller.delete

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
@Validated
class DeleteApiController {
    // 1. path variable
    // 2. request param

    @DeleteMapping("/delete-mapping")
    fun deleteMapping(
        @RequestParam(value = "name", required = false) name: String,

        @NotNull(message = "age값이 누락되었습니다.")
        @Min(value = 20, message = "age는 20보다 커야 합니다.")
        @RequestParam(value = "age", required = false) age: Int
    ): String {
        return "Deleted $name with $age"
    }

    @DeleteMapping("/delete-mapping/name/{name}/age/{age}")
    fun deleteMappingPath(@PathVariable("name")
                          @Size(min = 2, max = 5, message = "name의 길이는 2~5")
                          name:String,

                          @NotNull(message = "age값이 누락되었습니다.")
                          @Min(value = 20, message = "age는 20보다 커야 합니다.")
                          @PathVariable age:Int):String {
        return "Deleted $name with $age"
    }
}