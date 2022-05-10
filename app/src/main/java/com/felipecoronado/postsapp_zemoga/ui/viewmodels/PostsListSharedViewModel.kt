package com.felipecoronado.postsapp_zemoga.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetAllFavoritesPostsList
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetAllPostsList
import com.felipecoronado.postsapp_zemoga.ui.utils.asLiveData
import com.felipecoronado.postsapp_zemoga.ui.viewstates.AllPostsListViewState
import com.felipecoronado.postsapp_zemoga.ui.viewstates.FavoritePostsListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsListSharedViewModel @Inject constructor(
    private val getAllPostsList: IGetAllPostsList,
    private val getAllFavoritesList: IGetAllFavoritesPostsList
    ) : ViewModel() {

    private val _allViewState = MutableLiveData<AllPostsListViewState>()
    val allViewState = _allViewState.asLiveData()

    private val _favoriteViewState = MutableLiveData<FavoritePostsListViewState>()
    val favoriteViewState = _favoriteViewState.asLiveData()

    fun getAllPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getAllPostsList.invoke()
            when {
                result.isSuccess -> setAllListViewState(AllPostsListViewState.AllPostsList(result.getOrThrow()))
                else -> setAllListViewState(AllPostsListViewState.AllPostsNotFound)

            }
        }
    }

    fun getAllFavoritesPosts(){
        viewModelScope.launch() {
            val result = getAllFavoritesList.invoke()
            when {
                result.isSuccess -> setFavoriteListViewState(FavoritePostsListViewState.FavoriteListAll(result.getOrThrow()))
                else -> setFavoriteListViewState(FavoritePostsListViewState.EmptyListAll)

            }
        }
    }


    private fun setAllListViewState(newViewState:AllPostsListViewState){
        _allViewState.postValue(newViewState)
    }

    private fun setFavoriteListViewState(newViewState:FavoritePostsListViewState){
        _favoriteViewState.postValue(newViewState)
    }
}
