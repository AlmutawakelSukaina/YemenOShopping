package com.yemen.oshopping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreen : AppCompatActivity() , View.OnClickListener{
    private val TAG = "FirebaseEmailPassword"

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        supportActionBar?.hide()
        login_button.setOnClickListener(this)
        signUp.setOnClickListener(this)
        forgot_password.setOnClickListener(this)
        mAuth = FirebaseAuth.getInstance()

    }
    override fun onStart() {
        super.onStart()

        val currentUser = mAuth!!.currentUser

    }

    override fun onClick(v: View?) {
        val i = v!!.id

        if (i == R.id.signUp) {

            val intent = Intent(this, SignUp::class.java)

            startActivity(intent)


        } else if (i == R.id.login_button) {

            signIn(email.text.toString(), password.text.toString())

        }
        else if(i==R.id.forgot_password)
        {
            val intent = Intent(this, ResetPassword::class.java)

            startActivity(intent)
        }
    }


    private fun signIn(email: String, password: String) {
        Log.e(TAG, "signIn:" + email)
        if (!validateForm(email, password)) {
            return
        }

        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.e(TAG, "signIn: Success!")


                    val user = mAuth!!.currentUser
//                    val intent = Intent(this, MainScreen::class.java)
                    val intent = Intent(this, ChangePassword::class.java)
                    startActivity(intent)


                } else {
                    Log.e(TAG, "signIn: Fail!", task.exception)
                    Toast.makeText(applicationContext, "Authentication failed!", Toast.LENGTH_SHORT).show()

                }

                if (!task.isSuccessful) {
                    Toast.makeText(applicationContext, "Authentication failed!", Toast.LENGTH_SHORT).show()

                }
            }
    }
    private fun validateForm(email: String, password: String): Boolean {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 6) {
            Toast.makeText(applicationContext, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }


}