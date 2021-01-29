package com.example.superheroes.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.R
import com.example.superheroes.models.AllHeroesData
import com.example.superheroes.models.AllHeroesDataItem
import com.example.superheroes.views.SuperHeroDetail
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.super_hero_item.view.*

class SuperHeroAdapter(val superHeroes: AllHeroesData): RecyclerView.Adapter<SuperHeroAdapter.SuperHeroHolder>() {

    class SuperHeroHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun render(superHero: AllHeroesDataItem) {
            Picasso.get().load(superHero.images.lg).transform(CropCircleTransformation()).into(view.img_item_photo)
            view.txt_item_name.text = superHero.name
            view.setOnClickListener {
                val context = view.context
                val intent = Intent( context, SuperHeroDetail::class.java)
                val superHeroId = superHero.id.toString()
                intent.putExtra("id", superHeroId)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroHolder(layoutInflater.inflate(R.layout.super_hero_item, parent, false))
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: SuperHeroHolder, position: Int) {
        holder.render(superHeroes[position])
    }

}