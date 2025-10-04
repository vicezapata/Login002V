package com.example.login002v.data.repository

import com.example.login002v.data.model.Credential

class AuthRepository (
    private val validCredential: Credential =Credential.Admin
){
    fun login(username:String,password:String):Boolean{
        return username == validCredential.username && password==validCredential.password
    }

}