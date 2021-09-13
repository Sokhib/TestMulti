package com.example.data.helper

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String
        get() = "Couldn't reach server. Please Check your internet connection"
}