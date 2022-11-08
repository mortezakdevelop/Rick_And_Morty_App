package com.example.rickandmortymvvmapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rickandmortymvvmapplication.databinding.ActivityMainBinding
import com.example.rickandmortymvvmapplication.viewmodel.SharedViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel:SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.root

        viewModel.refreshCharacter(9)
        viewModel.characterByIdLiveData.observe(this) { response ->
            if (response == null) {
                Toast.makeText(this, "unsuccessful network call", Toast.LENGTH_LONG).show()
                return@observe
            }

            binding.tvName.text = response.name
            binding.tvLifeStatus.text = response.status
            binding.tvOrigin.text = response.origin.name
            binding.tvSpecies.text = response.species

            if (response.gender.equals("male", true)) {
                binding.ivGender.setImageResource(R.drawable.male_image)
            } else {
                binding.ivGender.setImageResource(R.drawable.ic_baseline_female_24)
            }

            Glide.with(this@MainActivity).load(response.image).into(binding.ivHeaderImageView);
        }
    }
}