package com.krossmanzs.learnauthspringfirebase.config

import com.google.api.client.util.Value
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.krossmanzs.learnauthspringfirebase.LearnAuthSpringFirebaseApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.Objects
import kotlin.jvm.Throws

@Configuration
class FirebaseAuthConfiguration {
    @Bean
    @Throws(IOException::class)
    fun firebaseAuth(): FirebaseAuth {
        val file: File = File(Objects.requireNonNull(this::class.java.classLoader.getResource("service-account.json")).file)
        val serviceAccount = FileInputStream(file.absolutePath)

        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()

        val firebaseApp = FirebaseApp.initializeApp(options)

        return FirebaseAuth.getInstance(firebaseApp)
    }

}