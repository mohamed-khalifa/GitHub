package com.khalifa.github.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.khalifa.github.R
import com.khalifa.github.databinding.FragmentUserDetailsBinding
import com.khalifa.github.domain.entity.UserDomainEntities
import com.khalifa.github.presentation.entity.UserDetailsState
import com.khalifa.github.presentation.util.loadUrl
import com.khalifa.github.presentation.viewmodel.UserDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class UserDetailsFragment : Fragment() {
    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: UserDetailsFragmentArgs by navArgs()
    private val viewModel: UserDetailsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUserDetails(args.userName)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setupActionBar(binding.userDetailsToolBar)
        binding.userDetailsToolBar.title = args.userName
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userDetailsState.collect { userDetailsState ->
                    userDetailsState.userDetails?.let { userDetails ->
                        setUserDetailsContent(userDetails)
                    }
                    handleErrorView(userDetailsState)
                }
            }
        }
    }

    private fun setUserDetailsContent(userDetails: UserDomainEntities.UserDomainItem) {
        with(binding) {
            userDetailsToolbarImageView.loadUrl(userDetails.avatarUrl)
            userDetailsContent.followersCountTextView.text =
                userDetails.followers.toString()
            with(userDetailsContent) {
                followingCountTextView.text = userDetails.following.toString()
                reposCountTextView.text = userDetails.repos.toString()
                nameTextView.text = userDetails.name
                companyTextView.apply {
                    text = userDetails.company
                    isVisible = userDetails.company.isNotEmpty()
                }
                aboutUserTv.text = userDetails.bio.ifEmpty {
                    resources.getString(
                        R.string.about_me
                    )
                }
            }
        }
    }


    private fun handleErrorView(userState: UserDetailsState) {
        binding.userDetailsContent.userDetailsContentView.isVisible =
            userState.errorMessage.isEmpty()
        binding.errorView.errorView.isVisible = userState.errorMessage.isNotEmpty()
        if (userState.errorMessage.isNotEmpty()) {
            binding.errorView.errorTextView.text = userState.errorMessage
            binding.appBarLayout.setExpanded(false, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}