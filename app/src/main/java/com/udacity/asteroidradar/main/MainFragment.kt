package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter = AsteroidAdapter( AsteroidClickListener {
//            asteroidId -> Toast.makeText(context, "${asteroidId}", Toast.LENGTH_LONG).show()
            asteroidId -> viewModel.onAsteroidClicked(asteroidId)
        })
        binding.asteroidRecycler.adapter = adapter

        viewModel.asteroidList.observe(viewLifecycleOwner, Observer { updatedAsteroidList ->
            adapter.submitList(updatedAsteroidList)
        })

        viewModel.navigateToAsteroidDetails.observe(viewLifecycleOwner, Observer { asteroid ->
            asteroid?.let {
                this.findNavController().navigate(
                        MainFragmentDirections.actionShowDetail(asteroid)
                )
                viewModel.onAsteroidDetailsNavigated()
            }
        })

        viewModel.imageOfTheDay.observe(viewLifecycleOwner, Observer { imageOfTheDay ->
            if (imageOfTheDay.url.isNotEmpty() && imageOfTheDay.mediaType.equals("image")) {
                Picasso.with(context).load(imageOfTheDay.url).into(binding.activityMainImageOfTheDay)
                binding.imageOfTheDayTitleText.text = imageOfTheDay.title
                binding.activityMainImageOfTheDay.contentDescription = imageOfTheDay.title
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
