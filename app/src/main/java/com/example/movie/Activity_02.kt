package com.example.movie

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import com.squareup.picasso.Picasso


class Activity_02: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val SEARCH_PREFIX = "https://www.google.com/search?q="
        setContentView(R.layout.movie_full_view)

        val moviename=intent?.extras?.getString("name").toString()
        val movieimg=intent?.extras?.getString("img").toString()
        val text_screen=findViewById<TextView>(R.id.text_screen)
        val img_screen=findViewById<ImageView>(R.id.full_screen)
        text_screen.text=moviename
        Picasso.get().load(movieimg).into(img_screen)
        val more_details=findViewById<Button>(R.id.more_option)
        more_details.setOnClickListener {
            val queryUrl: Uri = Uri.parse("${SEARCH_PREFIX}${moviename}")
            val intent= Intent(Intent.ACTION_VIEW,queryUrl)
            this.startActivity(intent)
        }

        }
    private fun onShare()
    {val moviename=intent?.extras?.getString("name").toString()
    val year=intent?.extras?.getString("year").toString()
        val shareintent =ShareCompat.IntentBuilder.from(this)
            .setText("Name:"+moviename+"Year:"+year+"\n#Movie APP -By Karan")
            .setType("text/plain")
            .intent
        try {
            startActivity(shareintent)
        } catch (ex: ActivityNotFoundException) {
        Toast.makeText(this,"Sharing Not Possible",Toast.LENGTH_LONG).show()
    }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.layout_share,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> onShare()
        }
        return super.onOptionsItemSelected(item)
    }
    }
