package com.krossmanzs.learnauthspringfirebase.controller

import com.google.firebase.auth.FirebaseAuthException
import com.krossmanzs.learnauthspringfirebase.security.Permission
import com.krossmanzs.learnauthspringfirebase.service.UserManagementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.Throws

@RestController
@RequestMapping("/admin")
class AdminController(
    @Autowired private val userManagementService: UserManagementService
) {
    @Secured("ROLE_ANONYMOUS") // for demo, its ok. But don't try it on production!
    @PostMapping("/user-claims/{uid}")
    @Throws(FirebaseAuthException::class)
    fun setUserClaims(
        @PathVariable uid: String,
        @RequestBody requestedClaims: List<Permission>
    ) = userManagementService.setUserClaims(uid, requestedClaims)

    @Secured("ROLE_ANONYMOUS") // for demo, its ok. But don't try it on production!
    @GetMapping("/user-claims/{uid}")
    fun getUserClaims(
        @PathVariable uid: String
    ): MutableMap<String, Any> = userManagementService.getUserClaims(uid)
}