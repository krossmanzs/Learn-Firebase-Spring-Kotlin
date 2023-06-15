package com.krossmanzs.learnauthspringfirebase.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.krossmanzs.learnauthspringfirebase.security.Permission
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Service
import java.util.stream.Collectors


@Service
class UserManagementService(
    @Autowired private val firebaseAuth: FirebaseAuth
){

    @Throws(FirebaseAuthException::class)
    fun setUserClaims(uid: String, requestedPermission: List<Permission>) {
        val permissions: List<String> = requestedPermission
            .stream()
            .map(Permission::toString)
            .collect(Collectors.toList())

        val claims: Map<String, Any> = mapOf("custom_claims" to permissions)

        firebaseAuth.setCustomUserClaims(uid, claims)
    }

    @Throws(FirebaseAuthException::class)
    fun getUserClaims(uid: String) = firebaseAuth.getUser(uid).customClaims
}