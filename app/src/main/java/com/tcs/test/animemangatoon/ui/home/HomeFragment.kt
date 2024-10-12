package com.tcs.test.animemangatoon.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tcs.test.animemangatoon.R
import com.tcs.test.animemangatoon.data.model.Article
import com.tcs.test.animemangatoon.databinding.FragmentHomeBinding
import com.tcs.test.animemangatoon.ui.home.adapter.HomeRecycleViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter:HomeRecycleViewAdapter
    private val viewModel:HomeViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.homeFragmentRv.layoutManager=LinearLayoutManager(requireContext())
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= HomeRecycleViewAdapter(
            object:OnArticleItemClickListener{
                override fun onAnswerClicked(article: Article) {
                    val bundle = Bundle().apply {
                        putInt("id", article.id)
                    }
                    findNavController().navigate(R.id.navigation_details,bundle)
                }
                override fun onImageButtonClicked(article:Article,position:Int) {
                    viewModel.setFavorite(article)
                    adapter.notifyItemChanged(position)
                }
            })
        lifecycleScope.launch {
            viewModel.getAllArticles().observe(viewLifecycleOwner){items->
                adapter.setArticles(items)
                binding.homeFragmentRv.adapter=adapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}