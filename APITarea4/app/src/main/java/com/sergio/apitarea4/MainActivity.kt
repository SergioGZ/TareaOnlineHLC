package com.sergio.apitarea4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var digimonAdapter: DigimonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://digi-api.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val digimonApi = retrofit.create(DigimonApi::class.java)

        digimonApi.getDigimons().enqueue(object : Callback<DigimonResponse> {
            override fun onResponse(call: Call<DigimonResponse>, response: Response<DigimonResponse>) {
                if (response.isSuccessful) {
                    val digimonResponse = response.body()
                    digimonResponse?.let { showDigimons(it.content) }
                } else {
                    Log.e("MainActivity", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<DigimonResponse>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}", t)
            }
        })
    }

    private fun showDigimons(digimons: List<Digimon>) {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        digimonAdapter = DigimonAdapter(this, digimons.subList(0, minOf(digimons.size, 6)))
        recyclerView.adapter = digimonAdapter
    }
}