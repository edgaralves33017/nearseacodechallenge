package com.codechallenge.nearshoretest.ui.chardetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.codechallenge.nearshoretest.MainActivity
import com.codechallenge.nearshoretest.R
import com.codechallenge.nearshoretest.databinding.FragmentChardetailBinding
import com.codechallenge.nearshoretest.model.models.characters.MarvelChar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class CharDetailFragment: Fragment() {
    private var _binding: FragmentChardetailBinding? = null

    private val binding get() = _binding!!

    private lateinit var charData : MarvelChar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        postponeEnterTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as MainActivity).changeBottomNavigationVisibility(true)
        _binding = FragmentChardetailBinding.inflate(inflater, container, false)

        charData = arguments?.getParcelable("charData", MarvelChar::class.java)!!

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.charpic.apply {
            val imageUri = "${charData.thumbnail.path}.${charData.thumbnail.extension}"
            transitionName = charData.thumbnail.path
            Picasso.get().load(imageUri).placeholder(R.drawable.logo_marvel).fit().centerCrop().into(this, object: Callback{
                override fun onSuccess() {
                    startPostponedEnterTransition()
                }

                override fun onError(e: Exception?) {
                    startPostponedEnterTransition()
                }

            })
        }
        binding.close.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}