package com.example.homework3

import android.app.Application
import android.content.Intent
import com.example.homework3.repository.Repository

class App : Application() {

    companion object{
        val repository = Repository()

    }

}