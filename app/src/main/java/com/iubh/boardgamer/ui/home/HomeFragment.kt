package com.iubh.boardgamer.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.iubh.boardgamer.core.Resource
import com.iubh.boardgamer.data.Post
import com.iubh.boardgamer.data.remote.HomeScreenDataSource
import com.iubh.boardgamer.databinding.FragmentHomeBinding
import com.iubh.boardgamer.domain.home.HomeScreenRepoImpl
import com.iubh.boardgamer.presentation.HomeScreenViewModel
import com.iubh.boardgamer.presentation.HomeScreenViewModelFactory
import com.iubh.boardgamer.ui.adapter.PostsAdapter
import com.iubh.boardgamer.utils.gone
import com.iubh.boardgamer.utils.show
import com.iubh.boardgamer.utils.showToast
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeScreenViewModel: HomeScreenViewModel by viewModels {
        HomeScreenViewModelFactory(
            HomeScreenRepoImpl(
                HomeScreenDataSource()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var posts: MutableList<Post>
        val adapter = PostsAdapter {
            Toast.makeText(requireContext(), it.profileName, Toast.LENGTH_SHORT).show()
        }
        binding.rvPost.adapter = adapter

        homeScreenViewModel.getPosts().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.show()
                }
                is Resource.Success -> {
                    binding.progressBar.gone()
                    posts = it.data as MutableList
                    if (posts.isEmpty()) {
                        binding.emptyContainer.show()
                        return@observe
                    } else {
                        binding.emptyContainer.gone()
                    }
                    adapter.submitList(posts)
                }
                is Resource.Failure -> {
                    binding.progressBar.gone()
                    Toast.makeText(requireContext(), "Fail to fetch data", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }

    fun scrollToTop() {
        binding.rvPost.smoothScrollToPosition(0)
    }

}