package com.jeromemarshall.crypto2k18.modal

/**
 * Created by jeromemarshall on 3/3/18.
 */
data class RegisterModal(
        var name: String = "",
        var email: String = "",
        var phone: String = "",
        var college: String = "",
        var eventName: String = ""
)
