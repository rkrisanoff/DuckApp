package ru.sample.duckapp

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sample.duckapp.domain.Duck
import ru.sample.duckapp.infra.Api


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val giveRandomDuckButton = findViewById<Button>(R.id.giveRandomDuckButton)
        giveRandomDuckButton.setOnClickListener {
            loadRandomDuck()
        }

        val giveHttpDuckField = findViewById<EditText>(R.id.editTextNumber);


        giveHttpDuckField.setOnKeyListener { _, integer_, key_event ->
            val txt = giveHttpDuckField.text
            giveHttpDuckField.error = null;
            if (txt.length > 3) {
                giveHttpDuckField.setText("");
                giveHttpDuckField.error = "Слишком большое число";


            }
            if (txt.length == 1 && (txt[0] !in '1'..'5')) {
                giveHttpDuckField.setText("");
                giveHttpDuckField.error = "неверный первый символ";
            }
            false
        }

        giveHttpDuckField.setOnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loadHttpDuck(giveHttpDuckField.text.toString().toInt())
            }
            false
        }

    }

    private fun loadRandomDuck() {
        val duckView = findViewById<ImageView>(R.id.duckImageView)

        Api.ducksApi.getRandomDuck().enqueue(object : Callback<Duck> {
            override fun onResponse(call: Call<Duck>, response: Response<Duck>) {
                if (response.isSuccessful) {
                    val duck: Duck? = response.body();
                    if (duck != null) {
                        Glide.with(duckView).load(duck.url)
                            .placeholder(android.R.drawable.ic_menu_help)
                            .error(android.R.drawable.ic_dialog_alert)
                            .centerCrop().into(duckView);
                    }
                } else {
                    println(response.code())
                }
            }

            override fun onFailure(call: Call<Duck>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun loadHttpDuck(number: Int) {
        val duckView = findViewById<ImageView>(R.id.duckImageView)
        Glide.with(duckView).load("https://random-d.uk/api/v2/http/$number.jpg")
            .placeholder(android.R.drawable.ic_menu_help)
            .error(android.R.drawable.ic_dialog_alert)
            .centerCrop().into(duckView);
    }

}