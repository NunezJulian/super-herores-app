package com.example.superheroes.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.Adapters.SuperHeroAdapter
import com.example.superheroes.R
import com.example.superheroes.interfaces.IAllHeroes
import com.example.superheroes.models.AllHeroesData
import com.example.superheroes.presenters.AllHeroesPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), IAllHeroes {

    val allHeroesPresenter: AllHeroesPresenter = AllHeroesPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        allHeroesPresenter.getAllHeroes()
    }



    override suspend fun allHeroesSuccess(allHeroesResponse: AllHeroesData?) {
        Log.d("logueano", allHeroesResponse.toString())
        withContext(Dispatchers.Main){
            rv_super_heroes.layoutManager = LinearLayoutManager(applicationContext)
            val adapter = SuperHeroAdapter(allHeroesResponse!!)
            rv_super_heroes.adapter = adapter
        }

    }

    override fun allHeroesError(errorMessage: String) {
        Log.d("logueano", errorMessage)
        Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
    }
}