package com.martinchamarro.filmgoer.extensions

import android.app.Service
import android.widget.Toast


fun Service.toast(message: String) = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()