package com.example.topchef.Activities

import android.content.Intent
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.topchef.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Sign_upActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val inscriptionClick = findViewById<Button>(R.id.inscription_button)
        inscriptionClick.setOnClickListener {
            auth = Firebase.auth

            val email = findViewById<EditText>(R.id.Mail)
            val mdp = findViewById<EditText>(R.id.MotdePasse)

            auth.createUserWithEmailAndPassword(email.text.toString(), mdp.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        val intent = Intent(this@Sign_upActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
        val retourClick = findViewById<Button>(R.id.retour_button)
        retourClick.setOnClickListener {
            val intent = Intent(this@Sign_upActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}