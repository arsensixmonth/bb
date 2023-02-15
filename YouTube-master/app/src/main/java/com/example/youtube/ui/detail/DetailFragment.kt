package com.example.youtube.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.youtube.base.BaseFragment
import com.example.youtube.databinding.FragmentDetailBinding
import com.example.youtube.model.Item
import com.example.youtube.network.Status
import com.example.youtube.playlist.Adapter.PlaylistAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {


    private val adapter: PlaylistAdapter by lazy {
        PlaylistAdapter(this::onClick)
    }

    override val viewModel: DetailViewModel by viewModel()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)

    }


    @SuppressLint("SetTextI18n")
    override fun initView() {
        val item = arguments?.getSerializable("item") as Item
        viewModel.getPlaylistItem(item.id.toString()).observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.loading.value = false
                    adapter.addData(it.data?.items)
                }
                Status.LOADING -> {
                    viewModel.loading.value = true
                }
                Status.ERROR -> {
                    viewModel.loading.value = false
                }

            }
            viewModel.loading.observe(viewLifecycleOwner) {
                binding.progresBar.isVisible = it
            }
            binding.recycler.adapter = adapter
            binding.description.text = item.snippet?.title

            val count = item.contentDetails?.itemCount
            binding.amountOfVideos.text = "$count video Series"


        }
    }

    private fun onClick(item: Item) {

    }


}