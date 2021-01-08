package com.yemen.oshopping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPassword : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        supportActionBar?.hide()
        Reset_button.setOnClickListener(this)





    }
    private  fun resetPassword( email:String)
    {


        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Toast.makeText(this,"Success ", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, LoginScreen::class.java)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this,"Failed ", Toast.LENGTH_LONG).show()

            }


    }

    override fun onClick(v: View?) {
        val i = v!!.id
        if(i==R.id.Reset_button)
            resetPassword(emailReset.text.toString())
    }
}