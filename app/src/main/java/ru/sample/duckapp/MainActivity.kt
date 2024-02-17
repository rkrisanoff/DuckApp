package ru.sample.duckapp

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.ImageView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sample.duckapp.activities.DuckDuckGo
import ru.sample.duckapp.activities.NetworkService
import ru.sample.duckapp.domain.Duck
import ru.sample.duckapp.infra.Api
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .permitNetwork()
                .build())

//        setContent {
//            DuckDuckGo()
//        }

        val my_view = findViewById<ImageView>(R.id.myImageView)

        val button = findViewById<Button>(R.id.button2)
        val true_this = this;
        button.setOnClickListener {
            loadImage()
            val intent = Intent(this, NetworkService::class.java)
            println("QWE")
            this.startService(intent)
            println("QWE")
        }
    }

    private fun loadImage() {
        val my_view = findViewById<ImageView>(R.id.myImageView)

        Api.ducksApi.getRandomDuck().enqueue(object : Callback<Duck> {
            override fun onResponse(call: Call<Duck>, response: Response<Duck>) {
                println("RESPONSE")
                if (response.isSuccessful) {
                    val imageUrl = response.body()
//                    if (!imageUrl.isNullOrBlank()) {
//                        currentImageUrl = imageUrl
                        // Используем Picasso для загрузки и отображения изображения
//                        Glide.with(this).load(imageUrl).into(my_view)
//                    }
                } else {
                    println(response.code())
                }
            }

            override fun onFailure(call: Call<Duck>, t: Throwable) {
                println("FAILURE")
                // Обработка ошибок при загрузке изображения
                t.printStackTrace()
            }
        })
    }







    // Code here executes on main thread after user presses button
//            Api.ducksApi.getRandomDuck().execute()
//            println(1);
//            Glide.with(true_this).load("https://random-d.uk/api/109.jpg") // image url
//                .placeholder(androidx.constraintlayout.widget.R.drawable.abc_btn_radio_material) // any placeholder to load at start
//                .error(R.drawable.ic_launcher_background)  // any image in case of error
//                .override(200, 200) // resizing
//                .centerCrop().into(my_view);
            // imageview object
//            my_view.setImageURI("https://random-d.uk/api/109.jpg");

//            thread {
//                try {
//                    val response = Api.ducksApi.getRandomDuck().execute()
//                    print(response.message())
////                if (response.isSuccessful) {
////                    println("RESPONSE")
////                    println(response.message());
////                } else {
////                    println("Silly requests");
////                }
//                } catch (e: Exception) {
//                    println(e.message)
//                }
//            }

//        }


//    }
}