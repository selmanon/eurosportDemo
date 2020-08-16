package com.tech.demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: com.tech.demo.NewsViewModel

    lateinit var list:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list = findViewById(R.id.newsList)
        list.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(com.tech.demo.NewsViewModel::class.java)
        viewModel.getNews().observe(this, Observer {
            val adapter = NewsAdapter(it)
            list.adapter = adapter
        })
    }
}