package io.matin.isolatedipc

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.isolatedsdk.IRemoteService

class MainActivity : AppCompatActivity() {

    private var remoteService: IRemoteService? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            Log.d("MainActivity", "onServiceConnected: Service connected")
            remoteService = IRemoteService.Stub.asInterface(binder)
            if (remoteService == null) {
                Log.e("MainActivity", "onServiceConnected: remoteService is null after asInterface")
                return
            }
            try {
                val message = remoteService?.getMessage()
                Log.d("MainActivity", "onServiceConnected: Message from service: $message")
                findViewById<TextView>(R.id.textView).text = message
            } catch (e: Exception) {
                Log.e("MainActivity", "Error getting message or setting text", e)
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("MainActivity", "onServiceDisconnected: Service disconnected")
            remoteService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.bindServiceButton).setOnClickListener {
            Log.d("MainActivity", "Bind button clicked")
            val intent = Intent("com.example.isolatedsdk.REMOTE_SERVICE")
            intent.setPackage("io.matin.isolatedipc")
            val success = bindService(intent, connection, BIND_AUTO_CREATE)
            Log.d("MainActivity", "bindService call returned: $success")
        }
    }
}