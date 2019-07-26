package com.stuffshuf.firebaseapp

import android.icu.util.TimeUnit
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential?) {
                //for instant verification and auto vertification --> this fun automatically call
                Toast.makeText(this@MainActivity, "Verification Completed", Toast.LENGTH_LONG).show()
                signUpWithPhone(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException?) {
                Toast.makeText(this@MainActivity, "Exception Completed ${p0?.localizedMessage}", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onCodeSent(p0: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(p0, p1)
                // recent token -->
                Toast.makeText(this@MainActivity, "Code Sent Completed", Toast.LENGTH_LONG).show()

            }



        }

        button.setOnClickListener {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91${edtvEail.text}", // Phone number to verify
                60, // Timeout duration
                java.util.concurrent.TimeUnit.SECONDS, // Unit of timeout
                this, // Activity (for callback binding)
                callbacks
            ) // OnVerificationStateChangedCallbacks
        }
    }

    private fun signUpWithPhone(p0: PhoneAuthCredential?) {
        auth.signInWithCredential(p0 as AuthCredential)
            .addOnCompleteListener {

            }.addOnSuccessListener {

            }.addOnFailureListener {

            }
    }
}


/*
        if (auth.currentUser != null) {
            button.setOnClickListener {
                auth.signInWithEmailAndPassword(edtvEail.text.toString(), edtvPass.text.toString())
                    .addOnCompleteListener {
                        button.isEnabled = false
                    }.addOnSuccessListener {
                        button.isEnabled = true
                        btnout.isVisible=true
                        btnout.isEnabled=true
                        Toast.makeText(this, "Signed In Successfully", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                    }


            }

        } else {

            button.setOnClickListener {
                auth.createUserWithEmailAndPassword(edtvEail.text.toString(), edtvPass.text.toString())
                    .addOnCompleteListener {
                        button.isEnabled = false
                    }.addOnSuccessListener {
                        button.isEnabled = true
                        Toast.makeText(this, "Created Account Successfully", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
            }
        }
        */


