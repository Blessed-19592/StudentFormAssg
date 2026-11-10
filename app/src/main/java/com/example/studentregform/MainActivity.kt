package com.example.studentregform

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Link each widget to its XML id
        val etStudentName = findViewById<EditText>(R.id.etStudentName)
        val etRegNumber = findViewById<EditText>(R.id.etRegNumber)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val spnProgramme = findViewById<Spinner>(R.id.spnProgramme)
        val rgStudyMode = findViewById<RadioGroup>(R.id.rgStudyMode)
        val swNotifications = findViewById<SwitchCompat>(R.id.swNotifications)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            // Read the values
            val name = etStudentName.text.toString().trim()
            val regNumber = etRegNumber.text.toString().trim()
            val email = etEmail.text.toString().trim()

            // Validate, stopping at the first error
            if (name.isEmpty()) {
                etStudentName.error = "Please enter the student name"
                etStudentName.requestFocus()
                return@setOnClickListener
            }

            if (regNumber.isEmpty()) {
                etRegNumber.error = "Please enter the registration number"
                etRegNumber.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                etEmail.error = "Please enter the email address"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Enter a valid email address"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            // -1 means no radio button is selected
            if (rgGender.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Please select a gender option", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Position 0 is the placeholder "Select study programme"
            if (spnProgramme.selectedItemPosition == 0) {
                Toast.makeText(this, "Please select a study programme", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (rgStudyMode.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Please select a study mode", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // All valid: collect the remaining values
            val programme = spnProgramme.selectedItem.toString()
            val notifications = if (swNotifications.isChecked) "On" else "Off"

            Toast.makeText(
                this,
                "Registration successful!\n$name ($regNumber)\n$programme\nNotifications: $notifications",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}