package com.tcs.test.animemangatoon.ui.details

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.tcs.test.animemangatoon.R
import com.tcs.test.animemangatoon.data.model.Article
import com.tcs.test.animemangatoon.databinding.FragmentDashboardBinding
import com.tcs.test.animemangatoon.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding:FragmentDetailsBinding?=null
    private val viewModel: DetailsViewModel by viewModels()
    private val binding get() = _binding!!
    private lateinit var article:Article

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(layoutInflater,container,false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt("id") ?: throw IllegalArgumentException("File ID is required")
        lifecycleScope.launch {
            article=viewModel.getArticleById(id)
            updateUi(article)
        }
    }
    private fun updateUi(article: Article){
        binding.name.text=article.name
        binding.description.text=article.description
        Glide.with(binding.image.context)
            .load(AppCompatResources.getDrawable(binding.image.context,drawableMap[article.imgSmall]?: R.drawable.solo_leveling_small))
            .into(binding.image)
        if(article.isFav){
            binding.imageButtonDetails.isSelected=true
        }
        binding.imageButtonDetails.setOnClickListener{
            article.isFav=!article.isFav
            viewModel.setFavorite(article)
            binding.imageButtonDetails.isSelected=article.isFav
        }
        binding.rating.text=article.rating.toString()
    }
    private val drawableMap= mapOf(
        "solo_leveling_small.jpg" to R.drawable.solo_leveling_small,
        "tower_small.jpg" to R.drawable.tower_small,
        "estate_small.jpg" to R.drawable.estate_small,
        "returner_small.jpg" to R.drawable.returners_small,
        "god_small.jpg" to R.drawable.god_small)
}