package com.example.topchef.Activities

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.topchef.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.gildor.coroutines.okhttp.await

class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.async {
            executeRequest("https://api.spoonacular.com/recipes/complexSearch")
            //?query dans l'url pour rechercher un ingrédient

        }

        auth = Firebase.auth

        val email = findViewById<EditText>(R.id.Login)
        val mdp = findViewById<EditText>(R.id.Mdp)

        val buttonClick = findViewById<Button>(R.id.connection_button)
        buttonClick.setOnClickListener {
            auth.signInWithEmailAndPassword(email.text.toString(), mdp.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Authentication succeeded, start new activity
                        val intent = Intent(this@MainActivity, RecetteActivity::class.java)
                        startActivity(intent)

                        // Log success message
                        Log.d(TAG, "Authentication succeeded, starting RecetteActivity")
                    } else {
                        // Authentication failed, show error message
                        val errorMessage = task.exception?.message ?: "Unknown error"
                        Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()

                        // Log error message
                        Log.d(TAG, "Authentication failed: $errorMessage")
                    }
                }
        }
        val inscripClick = findViewById<Button>(R.id.tosignup_button)
        inscripClick.setOnClickListener {
            val intent = Intent(this@MainActivity, Sign_upActivity::class.java)
            startActivity(intent)
        }
    }

    suspend fun executeRequest(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        val call = client.newCall(request).await()

        println(call.body?.toString())
    }
    //client.newCall(request).enqueue(object : Callback {
    //override fun onFailure(call: Call, e: IOException) {}
    //override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
    //})

}