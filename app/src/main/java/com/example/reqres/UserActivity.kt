package com.example.reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.reqres.adapters.ItemAdapter
import com.example.reqres.databinding.ActivityUserBinding
import com.example.reqres.network.APIService
import com.example.reqres.network.models.BaseClass
import com.example.reqres.network.models.UserBaseClass
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val img = binding.imageView
        val text1 = binding.text1
        val text2 = binding.text2
        val email = binding.email

//        This time asking for NPE if no id is passed in putExtra method
        val id = intent.extras!!.getString("id")
//

//        Build retrofit instance
//        Alternatively you can build retrofit instance in APIService class to reduce code repetitions
        val retrofit = Retrofit.Builder().baseUrl(APIService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(APIService::class.java)
//

//  Making the request

        service.getUser(id!!).also { it ->
            it.enqueue(object : Callback<UserBaseClass> {
                override fun onResponse(
                    call: Call<UserBaseClass>,
                    response: Response<UserBaseClass>
                ) {
                    response.body()?.data?.let {
                        Glide.with(this@UserActivity).load(it.avatar).into(img)
                        text1.text = it.firstName
                        text2.text = it.lastName
                        email.text = it.email
                    }
                }

                override fun onFailure(call: Call<UserBaseClass>, t: Throwable) {
                    Toast.makeText(this@UserActivity, t.message, Toast.LENGTH_SHORT).show()
                }


            })
        }

//

    }
}