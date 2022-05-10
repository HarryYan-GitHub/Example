package com.codingwithmitch.daggerhiltplayground.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.codingwithmitch.daggerhiltplayground.R
import com.codingwithmitch.daggerhiltplayground.databinding.FragmentMainBinding
import com.codingwithmitch.daggerhiltplayground.model.Blog
import com.codingwithmitch.daggerhiltplayground.ui.UserEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import java.lang.StringBuilder

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private var binding: FragmentMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentMainBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        viewModel.onEvent(UserEvent.DownloadFromFirebase)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectLifeCycleFlow(viewModel.dataState) { blogsEvent ->
            binding?.let {
                if (blogsEvent.blogs.isNotEmpty()) {
                    Glide.with(this)
                        .load(blogsEvent.blogs[0].image)
                        .into(it.imageView)
                }
            }

            appendBlogTitles(blogsEvent.blogs)
            displayError(blogsEvent.error)
            if (blogsEvent.blogs.isEmpty() && blogsEvent.isLoading) {
                displayProgressBar(true)
            } else {
                displayProgressBar(false)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mi_api -> viewModel.onEvent(UserEvent.DownloadFromApi)
            R.id.mi_firebase -> viewModel.onEvent(UserEvent.DownloadFromFirebase)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayError(message: String?) {
        if (message != null) {
            Toast.makeText(context, "$message", Toast.LENGTH_LONG).show()
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding?.progressBar?.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitles(blogs: List<Blog>) {
        val stringBuilder = StringBuilder()
        blogs.map { stringBuilder.append(it.title + " ${it.city}" + "\n") }
        binding?.text?.text = stringBuilder
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}

fun <T> Fragment.collectLifeCycleFlow(flow: Flow<T>, collect: FlowCollector<T>) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(collect)
        }
    }
}