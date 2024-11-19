package com.nurullahsevinckan.artbookpro.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nurullahsevinckan.artbookpro.R
import com.nurullahsevinckan.artbookpro.databinding.FragmentArtDetailsBinding
import com.nurullahsevinckan.artbookpro.databinding.FragmentArtsBinding

class ArtDetailsFragment: Fragment(R.layout.fragment_art_details) {

    private var fragementBinding : FragmentArtDetailsBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding =   FragmentArtDetailsBinding.bind(view)
        fragementBinding = binding

        binding.imageView.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
        }

        val callback = object  : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
               findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)


    }
}