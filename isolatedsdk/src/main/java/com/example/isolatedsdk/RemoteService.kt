package com.example.isolatedsdk

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.annotation.RestrictTo


@RestrictTo(RestrictTo.Scope.LIBRARY) //restricts access from the app
internal class RemoteService : Service() {
    private val binder = object : IRemoteService.Stub() {
        override fun getMessage(): String {
            Log.d("RemoteService", "getMessage() called")
            return "Hello from Isolated SDK in separate process!"
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d("RemoteService", "onBind() called")
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("RemoteService", "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("RemoteService", "onDestroy")
    }
}