package com.example.superheroes.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroes.adapters.SuperHeroAdapter
import com.example.superheroes.R
import com.example.superheroes.interfaces.IAllHeroes
import com.example.superheroes.models.AllHeroesData
import com.example.superheroes.presenters.AllHeroesPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), IAllHeroes {

    val allHeroesPresenter: AllHeroesPresenter = AllHeroesPresenter(this)
    val superHeroes: AllHeroesData = AllHeroesData()
    var adapter: SuperHeroAdapter = SuperHeroAdapter(superHeroes)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        allHeroesPresenter.getAllHeroes()

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.getFilter().filter(p0)
                return true;
            }

        })
    }



    override suspend fun allHeroesSuccess(allHeroesResponse: AllHeroesData?) {
        Log.d("logueano", allHeroesResponse.toString())
        withContext(Dispatchers.Main){
            progress_circular.visibility = View.GONE
            lyt_main.visibility = View.VISIBLE
            rv_super_heroes.layoutManager = LinearLayoutManager(applicationContext)
            adapter = SuperHeroAdapter(allHeroesResponse!!)
            rv_super_heroes.adapter = adapter
        }

    }

    override fun allHeroesError(errorMessage: String) {
        Log.d("logueano", errorMessage)
        Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
    }
}