package com.example.anime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anime.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.AnimeService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setTheme(R.style.Theme_Anime)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            val prog=findViewById<ProgressBar>(R.id.progressBar)
            prog.visibility=View.VISIBLE
            val animeService = AnimeService.create()
            val call = animeService.getTopAnimes()
            call.enqueue(object : Callback<TopAnime> {
                override fun onResponse(call: Call<TopAnime>, response: Response<TopAnime>) {
                    if (response.body() != null) {
                        prog.visibility=View.GONE
                        val top = response.body()!!.top
                        animeRecyclerView.adapter = AnimeAdapter(this@MainActivity, top)
                        animeRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 3)
                    }
                }
                override fun onFailure(call: Call<TopAnime>, t: Throwable) {
                    prog.visibility=View.GONE
                    val text = "Something went wrong"
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                }
            }
            )
            val button=findViewById<Button>(R.id.btnSearch)
            button.setOnClickListener{
                val searchedAnime = searchInputEditText.text.toString()
                val callSearchedAnime = animeService.getSearchedAnime(searchedAnime)
            prog.visibility=View.VISIBLE

                callSearchedAnime.enqueue(object : Callback<SearchedAnime> {

                    override fun onResponse(
                        call: Call<SearchedAnime>,
                        response: Response<SearchedAnime>
                    ) {
                        if (response.body() != null) {
                            prog.visibility=View.GONE
                            val searchedAnimes = response.body()!!.results

                            animeRecyclerView.adapter = AnimeAdapter(this@MainActivity,searchedAnimes)
                            animeRecyclerView.layoutManager = GridLayoutManager(this@MainActivity,3)
                        }
                    }

                    override fun onFailure(call: Call<SearchedAnime>, t: Throwable) {
                        prog.visibility=View.GONE
                        val text = "No Such Anime"
                        val duration = Toast.LENGTH_SHORT

                        val toast = Toast.makeText(applicationContext, text, duration)
                        toast.show()
                    }

                })
            }

        }
    }
    class AnimeAdapter(
        private val parentActivity: MainActivity,
        private val animes: List<Result>,

        ) : RecyclerView.Adapter<AnimeAdapter.CustomViewHolder>() {

        inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.anime_item, parent, false)
            return CustomViewHolder(view)
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            val anime = animes[position]

            val view = holder.itemView

            val name = view.findViewById<TextView>(R.id.name)
            val image = view.findViewById<ImageView>(R.id.image)

            name.text = anime.title
            Picasso.get().load(anime.imageUrl).into(image)

            view.setOnClickListener {
                bootomsheet(anime).apply {
                    show(parentActivity.supportFragmentManager,"AnimeDetailsBottomSheet")
                }
            }
        }

        override fun getItemCount(): Int {
            return animes.size
        }
    }
}
