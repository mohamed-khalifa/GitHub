package com.khalifa.github.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.khalifa.github.R
import com.khalifa.github.databinding.FragmentUsersBinding
import com.khalifa.github.domain.entity.UserDomainEntities
import com.khalifa.github.presentation.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : Fragment() {
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UsersViewModel by viewModels()
    private val usersAdapter by lazy { UsersAdapter(::navigateToUserDetails) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inflateSearchView(binding.searchView)
        binding.usersRecyclerView.adapter = usersAdapter
        observeSearchText()
        handleLoadState()
    }

    private fun observeSearchText() {
        lifecycleScope.launch {
            viewModel.searchResultState.collect { userState ->
                userState.usersList?.let { users ->
                    usersAdapter.submitData(viewLifecycleOwner.lifecycle, users)
                }
            }
        }
    }

    private fun handleLoadState() {
        usersAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            ) {
                binding.loadingView.isVisible = true
            } else {
                binding.loadingView.isVisible = false
                val errorState = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                handleError(errorState != null)
            }
            binding.initialView.root.isVisible = false
        }
    }

    private fun handleError(errorState: Boolean) {
        binding.errorView.errorView.isVisible = errorState
        binding.usersRecyclerView.isVisible = !errorState
    }

    private fun inflateSearchView(searchView: androidx.appcompat.widget.SearchView) {
        searchView.apply {
            queryHint = resources.getString(R.string.search_hint)
            clearFocus()
            setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    searchView.clearFocus()
                    if (query.isNotEmpty()) {
                        viewModel.userSearch(
                            user = query
                        )
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean = false
            })
        }
    }

    private fun navigateToUserDetails(user: UserDomainEntities.UserDomainItem) {
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}