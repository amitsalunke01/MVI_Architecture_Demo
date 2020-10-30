package com.amitsalunke.mvi_architecture_demo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.amitsalunke.mvi_architecture_demo.R
import com.amitsalunke.mvi_architecture_demo.model.Blog
import com.amitsalunke.mvi_architecture_demo.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainAct"
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)//mvi
    }

    private fun subscribeObservers() {
        //when using mvvm
        //viewModel.getBlogs().observe(this, Observer { dataState ->

        //when mvi
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitle(dataState.data)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }

        })
    }


    private fun displayError(msg: String?) {
        if (msg != null) {
            text.text = msg
        } else {
            text.text = "unknown error"
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitle(blogs: List<Blog>) {
        val sb = StringBuilder()
        for (blog in blogs) {
            sb.append(blog.title + "\n")
        }
        text.text = sb.toString()
    }
}