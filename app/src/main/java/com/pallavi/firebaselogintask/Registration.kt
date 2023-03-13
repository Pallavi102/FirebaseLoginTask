package com.pallavi.firebaselogintask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pallavi.firebaselogintask.databinding.ActivityRegistrationBinding



class Registration : AppCompatActivity() {
    lateinit var binding: ActivityRegistrationBinding
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConfirm.setOnClickListener {
            if (binding.etName.text.toString().isNullOrEmpty()) {
                binding.etName.error = "Enter name"
            } else if (binding.etPhone.text.toString().isNullOrEmpty()) {
                binding.etPhone.error = "Enter your phone no"
            } else if (binding.etAddress.text.toString().isNullOrEmpty()) {
                binding.etAddress.error = "Enter address"
            } else {
                var userModel = UserModel()
                userModel.name = binding.etName.text.toString()
                userModel.phoneNo = binding.etPhone.text.toString()
                userModel.address = binding.etAddress.text.toString()

                db.collection("users").document(auth.currentUser?.uid ?: "")
                    .set(userModel)
                    .addOnSuccessListener { documentReference ->
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener { e ->

                    }
            }
        }

    }
}