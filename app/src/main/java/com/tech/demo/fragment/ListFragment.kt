package com.tech.demo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tech.demo.NewsViewModel
import com.tech.demo.R
import com.tech.demo.ViewModelFactory
import com.tech.demo.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.list_news.*
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: NewsViewModel

    lateinit var list: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = view.findViewById(R.id.newsList)
        list.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel::class.java)
        progressBar.visibility = View.VISIBLE
        viewModel.getNews().observe(requireActivity(), Observer {
            progressBar.visibility = View.GONE
            when (it.first) {
                "Success" -> {
                    val adapter = NewsAdapter(requireContext(), it.second!!)
                    list.adapter = adapter
                }
                "Error" -> Toast.makeText(
                    requireContext(),
                    "Oops something went wrong",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}