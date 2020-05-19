package com.example.contentresolverapp.activity

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.LinearLayout

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.credenceid.connect.appadapter.UserListAdapter
import com.example.contentresolverapp.R
import com.example.contentresolverapp.models.PersonalData
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var userListAdapter: UserListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setData()
    }

    private fun setData() {
        val data = ArrayList<PersonalData>()
        val cursor: Cursor? = contentResolver.query(
            Uri.parse("content://com.example.contentProviderApp.contentProvider.CredenceContentProvider/personalData"),
            null,
            null,
            null,
            null
        )
        if (cursor == null) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
        } else {
            if (cursor.moveToFirst()) {
                progressBar.visibility = View.VISIBLE
                do {
                    val personalData = PersonalData()
                    personalData.id = cursor.getInt(cursor.getColumnIndex("id"))
                    personalData.name = cursor.getString(cursor.getColumnIndex("name"))
                    personalData.username = cursor.getString(cursor.getColumnIndex("username"))
                    personalData.email = cursor.getString(cursor.getColumnIndex("email"))
                    personalData.profileImage =
                        cursor.getString(cursor.getColumnIndex("profileImage"))
                    personalData.street = cursor.getString(cursor.getColumnIndex("street"))
                    personalData.suite = cursor.getString(cursor.getColumnIndex("suite"))
                    personalData.zipcode = cursor.getString(cursor.getColumnIndex("zipCode"))
                    personalData.city = cursor.getString(cursor.getColumnIndex("city"))
                    personalData.lat = cursor.getString(cursor.getColumnIndex("lat"))
                    personalData.lng = cursor.getString(cursor.getColumnIndex("lng"))
                    personalData.phone = cursor.getString(cursor.getColumnIndex("phone"))
                    personalData.website = cursor.getString(cursor.getColumnIndex("website"))
                    personalData.companyName =
                        cursor.getString(cursor.getColumnIndex("companyName"))
                    personalData.catchPhrase =
                        cursor.getString(cursor.getColumnIndex("catchPhrase"))
                    personalData.bs = cursor.getString(cursor.getColumnIndex("bs"))

                    data.add(personalData)
                } while (cursor.moveToNext())
                progressBar.visibility = View.GONE
                rvData.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                userListAdapter = UserListAdapter(this, data)
                rvData.adapter = userListAdapter
                etSearch.visibility = View.VISIBLE
                etSearch.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        userListAdapter.filter.filter(etSearch.text.toString())
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {

                    }

                })
            } else {
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
                cursor.close()

            }
        }
    }
}
