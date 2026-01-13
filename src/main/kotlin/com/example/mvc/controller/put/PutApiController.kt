package com.example.mvc.controller.put

import com.example.mvc.model.http.Result
import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping():String {
        return "put-mapping"
    }

    @RequestMapping(value = ["/request-mapping"], method = [RequestMethod.PUT])
    fun requestMapping():String {
        return "request-mapping - put mapping"
    }

    @PutMapping("/put-mapping/object")
    fun putMappingObject(@RequestBody userRequest: UserRequest):UserResponse {
        // 0. Response
        return UserResponse().apply {
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply {
            this.description = "testtestest"
        }.apply {
            val userList = mutableListOf<UserRequest>()

            userList.add(userRequest)

            userList.add(UserRequest().apply {
                this.name = "a"
                this.age = 10
                this.email = "testtestest@gmail.com"
                this.address = "a address"
                this.phoneNumber = "010-1111-1111"
            })

            userList.add(UserRequest().apply {
                this.name = "b"
                this.age = 20
                this.email = "destdesdest@gmail.com"
                this.address = "b address"
                this.phoneNumber = "010-2222-2222"
            })

            this.userRequest = userList
        }
    }
}