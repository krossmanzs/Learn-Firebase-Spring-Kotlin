package com.krossmanzs.learnauthspringfirebase.controller

import com.krossmanzs.learnauthspringfirebase.security.Permission
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/app")
class AppController {
    // TODO: This READ Authority is not working, idk why. Maybe 'future me' will comeback later on :/ 
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('READ')")
    fun test(principal: Principal): String = "It Works!\n $principal"
}