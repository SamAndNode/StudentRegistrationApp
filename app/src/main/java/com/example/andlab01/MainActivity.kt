package com.example.andlab01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.andlab01.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            val stId = binding.stId.text.toString()
            val stName = binding.stName.text.toString()
            val stAge = binding.stAge.text.toString()
            val stAddress = binding.stAddress.text.toString()
            val stEmail = binding.stEmail.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Students")
            val User = User(stId, stName, stAge, stAddress, stEmail)
            database.child(stId).setValue(User).addOnSuccessListener {
                binding.stId.text.clear()
                binding.stName.text.clear()
                binding.stAge.text.clear()
                binding.stAddress.text.clear()
                binding.stEmail.text.clear()

                Toast.makeText(this, "Student Registration Successful!", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,"Sorry! Couldn't Register", Toast.LENGTH_SHORT).show()
            }
        }
    }
}