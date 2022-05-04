package com.felipecoronado.postsapp_zemoga.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.felipecoronado.postsapp_zemoga.R
import com.felipecoronado.postsapp_zemoga.databinding.FragmentAllPostsBinding
import com.felipecoronado.postsapp_zemoga.ui.fragments.adapters.AllPostsAdapter

class AllPostsFragment : Fragment() {

    private lateinit var binding: FragmentAllPostsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.fragment_all_posts,
                container,
                false
            )

        binding.lifecycleOwner = this

        inflateRecycler()

        return binding.root
    }

    private fun inflateRecycler(){
        val adapter = AllPostsAdapter()
        binding.allPostsRecyclerView.adapter = adapter
    }

}
