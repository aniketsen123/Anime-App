package com.example.anime

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.example.anime.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import com.example.anime.databinding.DetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import service.anime_searching
import java.text.SimpleDateFormat

class bootomsheet(private val anime: Result):BottomSheetDialogFragment() {
    lateinit var binding: DetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val anime = LayoutInflater.from(requireContext())
        binding = DetailsBinding.inflate(anime)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            Picasso.get().load(anime.imageUrl).into(image)
            name.text =anime.title
            rating.text == anime.score.toString()
            pgRating.text = anime.rated
            episodes.text = "${anime.episodes} episodes"
            synopsis.text = anime.synopsis
            dates.text = "Started-${formatDate(anime.startDate)}"
            textView.text = if (anime.endDate == null) {
                "Ongoing "
            } else {
                "Ended - ${formatDate(anime.endDate)}"
            }
            knowMoreText.setOnClickListener {
                openCustomTab(activity as AppCompatActivity, Uri.parse(anime.url))
            }
           /* val animeService = anime_searching.create()
            var nameit=anime.title.toString()
            nameit=nameit.trim()
            var str:String=""
            var str2:String=""
            nameit=nameit+' '
            var f=0;
            val c:Char=nameit[1]
            for(i in nameit.indices){
                if((nameit[i].isLetter())||(nameit[i] in '0'..'9'))
                { str=str+nameit[i]
                    f=0}
                else if((nameit[i]==';'))
                    continue
                else
                {   if(f==0)
                {str2 += str.lowercase()
                    str2 += '-'
                    str=""
                    f++
                }
                }
            }

            str=str2.substring(0,str2.length-1)
            var url="https://ww3.gogoanime2.org/anime/"
            */

           /* button.setOnClickListener {



               // val search = response.body()!!.searchingresults

                /*val text = url+str
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(context,text , duration)
                toast.show()
                intent.putExtra("URL",text)
                context?.startActivity(intent)*/
                   val animeService=anime_searching.create()
                val name = animeService.searching(anime.title).also {

                    it.enqueue(object:Callback<Searching>
                    {
                        override fun onResponse(call: Call<Searching>, response: Response<Searching>) {
                            if (response.body() != null)
                            {
                                val text = "ok"
                                val duration = Toast.LENGTH_SHORT

                                val toast = Toast.makeText(context, text, duration)
                                toast.show()
                                val intent= Intent(context,web_view::class.java)
                                val search = response.body()!!.searchingresults

                                MainActivity.AnimeAdapter(this, search)



                            }
                        }

                        override fun onFailure(call: Call<Searching>, t: Throwable) {
                            val text = "Something went wrong"
                            val duration = Toast.LENGTH_SHORT

                            val toast = Toast.makeText(context, text, duration)
                            toast.show()
                        }

                    })
                }


            }*/
           /* button2.setOnClickListener{
                val intent= Intent(context,web_view::class.java)
                // val search = response.body()!!.searchingresults

               /* val text = url+str+ "-dub"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(context,text , duration)
                toast.show()
                intent.putExtra("URL",text)
                context?.startActivity(intent)*/

            }*/
        }
    }

    private fun openCustomTab(activity: AppCompatActivity, url: Uri) {
        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)
        builder.build().launchUrl(activity, url)
    }

    private fun formatDate(date: String): String {
        return if (date.contains("-")) {
            val newDate = date.substring(0, date.lastIndexOf("-"))
            val _date = SimpleDateFormat("yyyy-MM").parse(newDate)
            SimpleDateFormat("MMM yyyy").format(_date)
        } else {
            date
        }
    }
}


