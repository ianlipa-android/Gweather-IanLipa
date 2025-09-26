package com.example.gweather.data.local

import org.mindrot.jbcrypt.BCrypt

object PasswordUtils {


    fun encrypt(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }


    fun verifyHash(password: String, storedHash: String): Boolean {
        return try {
            BCrypt.checkpw(password, storedHash)
        } catch (e: IllegalArgumentException) {
            // Handle cases where the storedHash is not a valid BCrypt hash
            false
        }
    }
}