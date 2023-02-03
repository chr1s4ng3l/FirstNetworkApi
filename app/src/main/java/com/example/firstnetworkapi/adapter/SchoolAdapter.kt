package com.example.firstnetworkapi.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.firstnetworkapi.R
import com.example.firstnetworkapi.databinding.LetterItemBinding
import com.example.firstnetworkapi.databinding.SchoolItemBinding
import com.example.firstnetworkapi.model.SchoolsItem
import com.example.firstnetworkapi.view.DetailsFragment
import kotlinx.coroutines.NonDisposableHandle.parent


class SchoolAdapter(
    private val schoolSet: MutableList<ViewType> = mutableListOf(),
    private val onClickedSchool: (SchoolsItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    fun updateSchools(newSchools: List<SchoolsItem>) {
        var tempChar = '+'
        newSchools.sortedBy { it.schoolName }.forEach { school ->
            val firstLetter = school.schoolName?.first() ?: '+'
            if (firstLetter != tempChar) {
                schoolSet.add(ViewType.LETTER(firstLetter.toString()))
                tempChar = firstLetter
            }
            schoolSet.add(ViewType.SCHOOL(school))
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        return if (viewType == 0) {
            LetterViewHolder(
                LetterItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            SchoolViewHolder(
                SchoolItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (val item = schoolSet[position]) {
            is ViewType.SCHOOL -> {
                (holder as SchoolViewHolder).schoolBinding(item.schoolItem, onClickedSchool)
            }
            is ViewType.LETTER -> {
                (holder as LetterViewHolder).bindLetter(item.letter)
            }
        }
    }

    override fun getItemCount(): Int = schoolSet.size

    override fun getItemViewType(position: Int): Int {
        return when (schoolSet[position]) {
            is ViewType.SCHOOL -> 1
            is ViewType.LETTER -> 0
        }
    }

}

class SchoolViewHolder(
    private val binding: SchoolItemBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun schoolBinding(school: SchoolsItem, onClickedSchool: (SchoolsItem) -> Unit) {
        binding.schoolName.text = school.schoolName
        val address = school.location?.substringBefore("(")
        binding.schoolAddress.text = address
        binding.schoolPhone.text = school.phoneNumber


        binding.schoolPhone.setOnClickListener {

            call(binding.schoolPhone.text.toString(), it.context)
        }

        binding.schoolAddress.setOnClickListener {
            //Get the location
            val location = school.location?.substringAfterLast("(")
            val latitude = location?.substringBefore(",")
            println("This is the longitude : $latitude")

            val longitude = school.location?.substringAfterLast(",")?.substringBefore(")")
            println("This is the latitude : $longitude")

            goToGoogleMaps(latitude, longitude, address.toString(), it.context)

        }

        binding.moreBtn.setOnClickListener {
            onClickedSchool(school)

        }
    }
}

// Go to call when you click the tvPhone
fun call(phone: String, context: Context) {
    val dialIntent = Intent(Intent.ACTION_DIAL)
    dialIntent.data = Uri.parse("tel:$phone")
    context.startActivity(dialIntent)
}


// Go to Google maps whe u click on teh tvAddress
fun goToGoogleMaps(lat: String?, lon: String?, labelLocation: String, context: Context) {
    val urlAddress =
        "http://maps.google.com/maps?q=$lat,$lon($labelLocation)&iwloc=A&hl=es"
    val gmmIntentUri = Uri.parse(urlAddress)
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    context.startActivity(mapIntent)
}


class LetterViewHolder(
    private val binding: LetterItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindLetter(letter: String) {
        binding.letterName.text = letter
    }


}