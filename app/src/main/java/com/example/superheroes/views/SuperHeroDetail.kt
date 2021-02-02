package com.example.superheroes.views

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.superheroes.databinding.ActivitySuperHeroDetailBinding
import com.example.superheroes.interfaces.ISuperHeroDetail
import com.example.superheroes.models.SuperHeroDetail
import com.example.superheroes.presenters.SuperHeroPresenter
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SuperHeroDetail : AppCompatActivity(), ISuperHeroDetail {

    private lateinit var binding: ActivitySuperHeroDetailBinding
    private val superHeroDetailPresenter: SuperHeroPresenter = SuperHeroPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val heroId = intent.getStringExtra("id").toString()
        superHeroDetailPresenter.getSuperHeroDetail(heroId)

    }

    override suspend fun superHeroDetailSuccess(superheroDetailResponse: SuperHeroDetail?) {
        withContext(Dispatchers.Main) {
            if (superheroDetailResponse != null) {
                binding.progressCircular.visibility = View.GONE
                binding.lytMain.visibility = View.VISIBLE
                binding.txtSuperHeroName.text = superheroDetailResponse.name
                binding.txtSuperHeroFullName.text = superheroDetailResponse.biography.fullName
                binding.txtSuperHeroPlaceOfBirth.text = superheroDetailResponse.biography.placeOfBirth
                binding.txtFirstAppearance.text = superheroDetailResponse.biography.firstAppearance
                val heroWeight = superheroDetailResponse.appearance.weight[1]
                val heroHeight = superheroDetailResponse.appearance.height[1]
                val heroAlene: String = "$heroWeight - $heroHeight"
                binding.txtWeightHeight.text = heroAlene
                Picasso.get().load(superheroDetailResponse.image.url).transform(CropCircleTransformation()).into(binding.imgSuperHeroePhoto)
                binding.txtPublisher.text = superheroDetailResponse.biography.publisher
                binding.txtOcucupation.text = superheroDetailResponse.work.occupation
                binding.txtAffiliation.text = superheroDetailResponse.connections.groupAffiliation


                var superHeroStats: ArrayList<RadarEntry> = ArrayList()
                superHeroStats.add(RadarEntry(superheroDetailResponse.powerstats.intelligence.toFloat()))
                superHeroStats.add(RadarEntry(superheroDetailResponse.powerstats.strength.toFloat()))
                superHeroStats.add(RadarEntry(superheroDetailResponse.powerstats.speed.toFloat()))
                superHeroStats.add(RadarEntry(superheroDetailResponse.powerstats.durability.toFloat()))
                superHeroStats.add(RadarEntry(superheroDetailResponse.powerstats.power.toFloat()))
                superHeroStats.add(RadarEntry(superheroDetailResponse.powerstats.combat.toFloat()))

                var radarSetSuperHeroStats = RadarDataSet(superHeroStats, superheroDetailResponse.name )
                radarSetSuperHeroStats.color = Color.GREEN
                radarSetSuperHeroStats.lineWidth = 4f
                radarSetSuperHeroStats.valueTextColor = Color.RED
                radarSetSuperHeroStats.valueTextSize = 8f
                radarSetSuperHeroStats.fillColor = Color.GREEN

                var radarData: RadarData = RadarData()
                radarData.addDataSet(radarSetSuperHeroStats)

                var labels: Array<String> = arrayOf("Inteligencia", "Fuerza", "Velocidad", "Durabilidad", "Poder", "Combate")
                var xAxis: XAxis = binding.radarChart.xAxis
                xAxis.valueFormatter = IndexAxisValueFormatter(labels)

                binding.radarChart.data = radarData

                binding.radarChart.invalidate()
            }
        }

    }

    override fun superHeroDetailError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}