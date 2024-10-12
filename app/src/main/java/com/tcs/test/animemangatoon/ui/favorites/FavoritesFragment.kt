package com.tcs.test.animemangatoon.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tcs.test.animemangatoon.databinding.FragmentDashboardBinding
import com.tcs.test.animemangatoon.ui.favorites.adapter.FavoritesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val viewModel:FavoritesViewModel by viewModels()
    private lateinit var adapter: FavoritesAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.favRv.layoutManager=LinearLayoutManager(requireContext())
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= FavoritesAdapter(){ article, position->
            article.isFav=false
            viewModel.setFavorite(article)
            adapter.notifyItemRemoved(position)
        }
        setUpRv()
    }
    private fun setUpRv(){
        viewModel.getAllFavoritesArticles().observe(viewLifecycleOwner){items->
            if(items.size==0){
                binding.favRv.visibility=View.GONE
                binding.tv.visibility=View.VISIBLE
            }else{
                binding.root.visibility=View.VISIBLE
                binding.tv.visibility=View.GONE
                adapter.setArticles(items)
                binding.favRv.adapter=adapter
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}