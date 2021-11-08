package com.rickie.onlineshop

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rickie.onlineshop.databinding.ActivitySingleBinding
import kotlinx.android.synthetic.main.single_item.view.*

class SingleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySingleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val prefs: SharedPreferences = getSharedPreferences("store", Context.MODE_PRIVATE)


        val ptitle = findViewById(R.id.ptitle) as TextView
        val title = prefs.getString("title","")
        ptitle.text = title

        val pcost = findViewById(R.id.pcost) as TextView
        val cost = prefs.getString("cost","")
        pcost.text = cost

        val pdesc = findViewById(R.id.pdesc) as TextView
        val description = prefs.getString("description","")
        pdesc.text = description

        val pimage = findViewById(R.id.pimage) as ImageView
        val image_url = prefs.getString("image_url","")

        Glide.with(applicationContext)
            .load(image_url)
            .override(300,200)
            .into(pimage)

    }
}