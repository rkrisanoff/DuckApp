package ru.sample.duckapp.activities

import android.app.Activity
import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.semantics.SemanticsProperties.Text
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sample.duckapp.R
import ru.sample.duckapp.domain.Duck
import ru.sample.duckapp.infra.Api

@Composable

fun DuckDuckGo() {
        print("QWERTYQWERTY");

//
//        findLayout
//        Layout(
//                R.layout.activity_main
//         );

}

class NetworkService : IntentService("NetworkService") {
        @Deprecated("Deprecated in Java")
        override fun onHandleIntent(intent: Intent?) {
                // Perform network request
//                Thread.sleep(1000) // Simulate delay

                // Update UI with user data
                val broadcastIntent = Intent("ACTION_USER_DATA")
                broadcastIntent.putExtra("userData", "User data")
                sendBroadcast(broadcastIntent)
                intent
//            val my_view = findViewById<ImageView>(R.id.myImageView)
//            Glide.with(this).load("https://random-d.uk/api/109.jpg") // image url
//                .placeholder(androidx.constraintlayout.widget.R.drawable.abc_btn_radio_material) // any placeholder to load at start
//                .error(R.drawable.ic_launcher_background)  // any image in case of error
//                .override(200, 200) // resizing
//                .centerCrop().into(my_view);

println("asksjdh")
                loadImage();
        }

        private fun loadImage() {
//                val my_view = findViewById<ImageView>(R.id.myImageView)

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
}
