package com.example.contentresolverapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.contentresolverapp.R
import com.example.contentresolverapp.models.PersonalData
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val personData=intent.getParcelableExtra<PersonalData>("data")
        Glide.with(this).load(personData.profileImage).into(imageView2)
        tvName.text = "Name :${personData.name}"
        textView3.text = "UserName :${personData.username}"
        textView4.text = "Email :${personData.email}"
        textView5.text = "Phone :${personData.phone}"
        textView6.text = "Address :${personData.street}"
        textView7.text = "Company Name :${personData.companyName}"
        textView8.text = "Website :${personData.website}"

    }
}
