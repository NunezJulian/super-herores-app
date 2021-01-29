package com.example.superheroes.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.superheroes.databinding.ActivitySuperHeroDetailBinding
import com.example.superheroes.interfaces.ISuperHeroDetail
import com.example.superheroes.models.SuperHeroDetail
import com.example.superheroes.presenters.SuperHeroPresenter
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



//        var superHeroStats: ArrayList<RadarEntry> = ArrayList()
//        superHeroStats.add(RadarEntry(94.0f))
//        superHeroStats.add(RadarEntry(100.0f))
//        superHeroStats.add(RadarEntry(100.0f))
//        superHeroStats.add(RadarEntry(100.0f))
//        superHeroStats.add(RadarEntry(100.0f))
//        superHeroStats.add(RadarEntry(85.0f))
//
//        var radarSetSuperHeroStats = RadarDataSet(superHeroStats, "Super Hero name" )
//        radarSetSuperHeroStats.color = Color.GREEN
//        radarSetSuperHeroStats.lineWidth = 4f
//        radarSetSuperHeroStats.valueTextColor = Color.RED
//        radarSetSuperHeroStats.valueTextSize = 8f
//        radarSetSuperHeroStats.fillColor = Color.GREEN
//        radarSetSuperHeroStats.entryCount
//
//        var radarData: RadarData = RadarData()
//        radarData.addDataSet(radarSetSuperHeroStats)
//
//        var labels: Array<String> = arrayOf("Inteligencia", "Fuerza", "Velocidad", "Durabilidad", "Poder", "Combate")
//        var xAxis: XAxis = binding.radarChart.xAxis
//        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
//
//        binding.radarChart.data = radarData
//
//        binding.radarChart.invalidate()
    }

    override suspend fun superHeroDetailSuccess(superheroDetailResponse: SuperHeroDetail?) {
        Log.d("Logueando", superheroDetailResponse.toString())
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
            }
        }

    }

    override fun superHeroDetailError(errorMessage: String) {
        Log.d("Logueando", errorMessage)
        Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
    }
}