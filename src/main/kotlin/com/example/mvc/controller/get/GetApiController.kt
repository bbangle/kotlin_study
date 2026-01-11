package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class GetApiController {

    @GetMapping("/hello")
    fun hello(): String {
        return "hello kotlin"
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    @GetMapping("get-mapping/path-variable/{name}/{age}") //GET http;//localhost:8080/api/get-mapping/pah-variable/bbangle
    fun pathVariables(@PathVariable name: String, @PathVariable age:Int): String {
        return "Hello $name, your age is $age"
    }

    @GetMapping("get-mapping/query-parameter")
    fun queryParams(name:String, age:Int): String {
        return "Hello $name, your age is $age"
    }

    //name, age, address, email 객체로 받기
    @GetMapping("get-mapping/query-parameter/object")
    fun queryParamObject(userRequest: UserRequest):UserRequest{
        return userRequest
    }

    @GetMapping("get-mapping/query-parameter/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>):Map<String,Any>{
        return map
    }
}