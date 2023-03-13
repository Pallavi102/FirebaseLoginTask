package com.pallavi.firebaselogintask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pallavi.firebaselogintask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        if(binding.etEmail.text.toString().isNullOrEmpty()){
            binding.etEmail.error = "Enter email"
        }else if(binding.etPassword.text.toString().isNullOrEmpty()){
            binding.etPassword.error = "Enter password"
        }else{
            auth.createUserWithEmailAndPassword(binding.etEmail.text.toString(),
                binding.etPassword.text.toString()).addOnSuccessListener {
                Toast.makeText(this, "Registered successfully", Toast.LENGTH_LONG).show()
                var intent = Intent(this, Registration::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                System.out.println(" in exception $it")
                Toast.makeText(this, "Cannot registered ${it.toString()}", Toast.LENGTH_LONG).show()
            }
        }
    }
}