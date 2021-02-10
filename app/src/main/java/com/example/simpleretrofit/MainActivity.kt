package com.example.simpleretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.IntegerRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.simpleretrofit.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val options: HashMap<String, String> = HashMap()
        options["_sort"] = "id"
        options["_order"] = "desc"

        button.setOnClickListener{
            val myNumber = number_editText.text.toString()
            viewModel.getCustomPosts(Integer.parseInt(myNumber), "id", "desc")
            viewModel.myCustomPosts.observe(this, Observer { response ->
                if(response.isSuccessful){
                    textView.text = response.body().toString()
                    response.body()?.forEach {
                        Log.d("Response", it.userId.toString())
                        Log.d("Response", it.id.toString())
                        Log.d("Response", it.title)
                        Log.d("Response", it.body)
                        Log.d("Response", "-------------------")
                    }
                }else{
                    textView.text = response.code().toString()
                }
            })
        }
    }
}
