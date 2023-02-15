package com.example.youtube.playlist

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.youtube.R
import com.example.youtube.base.BaseFragment
import com.example.youtube.databinding.FragmentPlayListBinding
import com.example.youtube.model.Item
import com.example.youtube.network.Status
import com.example.youtube.playlist.Adapter.PlaylistAdapter
import com.example.youtube.utils.isOnline
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlayListFragment : BaseFragment<FragmentPlayListBinding, PlayListViewModel>() {

    private val adapter: PlaylistAdapter by lazy {
        PlaylistAdapter(this::onClick)
    }

    override val viewModel: PlayListViewModel by viewModel()

    override fun inflateViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentPlayListBinding {
        return FragmentPlayListBinding.inflate(inflater, container, false)
    }

    override fun initView() {

    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun initListener() {
        super.initListener()
        binding.noInternetInclude.tryAgain.setOnClickListener {
            checkInternet()
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        //local data

        viewModel.getPlaylistDB.observe(viewLifecycleOwner) {
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
        }

        viewModel.getPlaylistDB.observe(viewLifecycleOwner) {
            Log.e("ololo", "setPlaylist: $it")
        }

        //remote  data
        viewModel.getPlaylist.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.loading.value = false
                    adapter.addData(it.data?.items)
                    it.data?.let { it1 -> viewModel.setPlaylistDB(it1) }
                }
                Status.LOADING -> {
                    viewModel.loading.value = true
                }
                Status.ERROR -> {
                    viewModel.loading.value = false
                }

            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }
        binding.recyclerPlayList.adapter = adapter
    }

    private fun onClick(item: Item) {
        findNavController().navigate(R.id.detailFragment, bundleOf("item" to item))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun checkInternet() {
        val onLine = isOnline(requireContext())
        binding.recyclerPlayList.isVisible = onLine
        binding.noInternetContainer.isVisible = !onLine

    }


}



