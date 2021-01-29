package com.example.superheroes.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.R
import com.example.superheroes.models.AllHeroesData
import com.example.superheroes.models.AllHeroesDataItem
import com.example.superheroes.views.SuperHeroDetail
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.super_hero_item.view.*


class SuperHeroAdapter(val superHeroes: AllHeroesData, var superHeroesFiltered: AllHeroesData = superHeroes): RecyclerView.Adapter<SuperHeroAdapter.SuperHeroHolder>(), Filterable {

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

    override fun getItemCount(): Int = superHeroesFiltered.size

    override fun onBindViewHolder(holder: SuperHeroHolder, position: Int) {
        holder.render(superHeroesFiltered[position])
    }

    override fun getFilter(): Filter {
        val filter = object: Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val searchString: String = p0.toString()
                if (searchString.isEmpty()) {
                    superHeroesFiltered = superHeroes
                } else {
                    val tempFilteredList: AllHeroesData = AllHeroesData()
                    for (superHero: AllHeroesDataItem in superHeroes) {
                        if (superHero.name.toLowerCase().contains(searchString)) {
                            tempFilteredList.add(superHero)
                        }
                    }
                    Log.d("Logueamos", tempFilteredList.toString())

                    superHeroesFiltered = tempFilteredList

                    Log.d("Logueamos", superHeroesFiltered.toString())
                }

                val filterResults: FilterResults =  FilterResults()
                filterResults.values = superHeroesFiltered;
                return filterResults;
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                superHeroesFiltered = p1?.values as AllHeroesData
                notifyDataSetChanged()
            }

        }
        return filter
    }

}