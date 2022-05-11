package com.felipecoronado.postsapp_zemoga.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetAllFavoritesPostsList
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetAllPostsList
import com.felipecoronado.postsapp_zemoga.ui.utils.asLiveData
import com.felipecoronado.postsapp_zemoga.ui.viewstates.PostsListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsListSharedViewModel @Inject constructor(
    private val getAllPostsList: IGetAllPostsList,
    private val getAllFavoritesList: IGetAllFavoritesPostsList
    ) : ViewModel() {

    private val _viewState = MutableLiveData<PostsListViewState>()
    val viewState = _viewState.asLiveData()

    fun getAllPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getAllPostsList.invoke()
            when {
                result.isSuccess -> setPostListViewState(PostsListViewState.AllPostsList(result.getOrThrow()))
                else -> setPostListViewState(PostsListViewState.PostsNotFound)

            }
        }
    }

    fun getAllFavoritesPosts(){
        viewModelScope.launch() {
            val result = getAllFavoritesList.invoke()
            when {
                result.isSuccess -> setPostListViewState(PostsListViewState.FavoritePostsList(result.getOrThrow()))
                else -> setPostListViewState(PostsListViewState.PostsNotFound)

            }
        }
    }

    fun wipeRecycler(){
        setPostListViewState(PostsListViewState.AllPostsList(mutableListOf()))
    }


    private fun setPostListViewState(newViewState:PostsListViewState){
        _viewState.postValue(newViewState)
    }

}
