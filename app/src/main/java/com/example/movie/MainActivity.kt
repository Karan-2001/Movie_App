package com.example.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var linear =true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        changelayout()
    }

    private fun changelayout() {
        if(linear)
        {
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
        else
        {
            recyclerView.layoutManager = GridLayoutManager(this,2)
        }
        recyclerView.adapter=year_adapter()
    }
    private fun setIcon(menuItem: MenuItem?)
    {
        if(menuItem==null)
return
        menuItem.icon=
            if(linear) {
                ContextCompat.getDrawable(this,R.drawable.ic_grid)
                            }
            else{
                ContextCompat.getDrawable(this,R.drawable.ic_linear)
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
menuInflater.inflate(R.menu.layout_menu,menu)
val layoutButton=menu?.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
        var menuItem=menu!!.findItem(R.id.action_search)
        var searchView = menuItem.actionView as SearchView
        searchView.maxWidth=Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
               return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               year_adapter().filter.filter(newText)
                return true
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                linear = !linear
                changelayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}