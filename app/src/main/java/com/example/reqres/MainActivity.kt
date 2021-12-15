package com.example.reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.reqres.adapters.ItemAdapter
import com.example.reqres.databinding.ActivityMainBinding
import com.example.reqres.network.APIService
import com.example.reqres.network.models.BaseClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recycler = binding.recyclerView
//        Build Retrofit service
        val retrofit = Retrofit.Builder().baseUrl(APIService.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build()
        val service = retrofit.create(APIService::class.java)

//end retrofit


//        Call Retrofit methods now in asynchronous mode
//        Alternatively you can make functions in APIService suspendable and launch those functions in coroutinescope
//
        service.getAllUsers().also { it ->
            it.enqueue(object : Callback<BaseClass> {
                override fun onResponse(call: Call<BaseClass>, response: Response<BaseClass>) {
                    response.body()?.data.also {
//                        We know have the data just set the adapter for recyclerview
                        recycler.adapter = ItemAdapter(this@MainActivity, it!!)
                    }
                }

                override fun onFailure(call: Call<BaseClass>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                }

            })
        }


//


    }

}