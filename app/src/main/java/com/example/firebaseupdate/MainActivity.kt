package com.example.firebaseupdate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseupdate.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener {
            val firstName = binding.etName.text.toString()
            val lastName = binding.etLast.text.toString()
            val age = binding.etAge.text.toString()
            val userName = binding.etUser.text.toString()

            updateData(userName,firstName,lastName,age)

        }
    }

    private fun updateData(userName:String,firstName:String,lastName:String,age:String) {

        database=FirebaseDatabase.getInstance().getReference("Users")

        val user= mapOf<String, String>(
            "firstName" to firstName,
            "lastName" to lastName,
            "age" to age
        )
        database.child(userName).updateChildren(user).addOnSuccessListener {


            binding.etUser.text.clear()
            binding.etName.text.clear()
            binding.etLast.text.clear()
            binding.etAge.text.clear()

            Toast.makeText(this,"Fields Upadated",Toast.LENGTH_LONG).show()

        }.addOnFailureListener {
            Toast.makeText(this,"Failed to Upadate Fields",Toast.LENGTH_LONG).show()
        }

    }
}