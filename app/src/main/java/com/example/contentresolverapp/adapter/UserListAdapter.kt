package com.credenceid.connect.appadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import android.view.View
import android.widget.Filter
import android.widget.Filterable
import com.bumptech.glide.Glide
import com.example.contentresolverapp.R
import com.example.contentresolverapp.activity.DetailsActivity
import com.example.contentresolverapp.models.PersonalData
import kotlinx.android.synthetic.main.layout_data_item.view.*
import org.jetbrains.anko.startActivity

import kotlin.collections.ArrayList


class UserListAdapter(private val context: Context, val userDetails: ArrayList<PersonalData>) :
    RecyclerView.Adapter<UserListAdapter.ActivityListHolder>(), Filterable {

    var filterList = ArrayList<PersonalData>()

    init {
        filterList = userDetails
    }

    override fun onBindViewHolder(holder: ActivityListHolder, position: Int) {
        holder.bindItems(filterList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityListHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_data_item, parent, false)

        return ActivityListHolder(v)
    }

    /*
        Activity List Variables location, time and Activity name.
     */

    inner class ActivityListHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bindItems(item: PersonalData?) {
            itemView.tvName.text = item!!.name
            itemView.tvCompanyName.text = item.companyName
            Glide.with(context).load(item.profileImage).into(itemView.ivProfilePic);
            itemView.clMain.setOnClickListener {
                context.startActivity<DetailsActivity>("data" to item)
            }

        }
    }


    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterList = userDetails
                } else {
                    val resultList = ArrayList<PersonalData>()
                    for (row in userDetails) {
                        if (row.name!!.toLowerCase()
                                .contains(charSearch.toLowerCase()) || row.email!!.toLowerCase()
                                .contains(
                                    charSearch.toLowerCase()
                                )
                        ) {
                            resultList.add(row)
                        }
                    }
                    filterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<PersonalData>
                notifyDataSetChanged()
            }

        }
    }
}


