package com.yemen.oshopping.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.yemen.oshopping.R
import com.yemen.oshopping.model.User
import com.yemen.oshopping.viewmodel.OshoppingViewModel


class AddUserFragment : Fragment() {

    lateinit var fNameEditText: EditText
    lateinit var lNameEditText: EditText
    lateinit var emailEditText: EditText
    lateinit var latitudeEditText: EditText
    lateinit var longitudeEditText: EditText
    lateinit var detailsEditText: EditText
    lateinit var saveProfileBTN: Button

    lateinit var oshoppingViewModel: OshoppingViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        oshoppingViewModel = ViewModelProviders.of(this).get(OshoppingViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_user, container, false)
        fNameEditText = view.findViewById(R.id.first_name_edit_text)
        lNameEditText = view.findViewById(R.id.last_name_edit_text)
        emailEditText = view.findViewById(R.id.email_edit_text)
        latitudeEditText = view.findViewById(R.id.latitude_edit_text)
        longitudeEditText = view.findViewById(R.id.longitude_edit_text)
        detailsEditText = view.findViewById(R.id.details_edit_text)
        saveProfileBTN = view.findViewById(R.id.save_profile_btn)
        saveProfileBTN.setOnClickListener {
            val user = User(
                first_name = fNameEditText.text.toString(),
                last_name = lNameEditText.text.toString(),
                email = emailEditText.text.toString(),
                latitude = latitudeEditText.text.toString().toDouble(),
                longitude = longitudeEditText.text.toString().toDouble(),
                details = detailsEditText.text.toString()
            )
            oshoppingViewModel.pushUser(user)

        }
        return view
    }

    companion object {
        fun newInstance() =
            AddUserFragment()
    }
}