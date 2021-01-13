package com.yemen.oshopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_change_password.*


class ChangePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        changePass.setOnClickListener{
            if(NewPass.text.isNotBlank()&&NewPass.text.isNotEmpty()&&confirm.text.isNotEmpty()&&confirm.text.isNotBlank()&&NewPass.text.toString()==confirm.text.toString())
            {
                val user = FirebaseAuth.getInstance().currentUser

                user!!.updatePassword(NewPass.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                    Toast.makeText(this,"Successfully Changed Password",Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this,"Error Update",Toast.LENGTH_LONG).show()

                    }
                }
            }
            else if(NewPass.text.isBlank()||NewPass.text.isEmpty())
            {
                NewPass.setError("Enter Password")


            }
            else if(confirm.text.isBlank()||confirm.text.isEmpty())
            {
                confirm.setError("Enter Confirm")


            }
            else if(confirm.text.toString()!=NewPass.text.toString())
            {
                NewPass.setError("Not Match")
                confirm.setError("Not Match")

            }
        }

    }
}