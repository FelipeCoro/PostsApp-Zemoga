package com.felipecoronado.postsapp_zemoga.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetCommentsById
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetPostById
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetUserById
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.ITogglePostAsFavorite
import com.felipecoronado.postsapp_zemoga.ui.utils.asLiveData
import com.felipecoronado.postsapp_zemoga.ui.viewstates.PostDescriptionViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDescriptionViewModel @Inject constructor(
    private val getPostById: IGetPostById,
    private val getUserById: IGetUserById,
    private val getCommentsById: IGetCommentsById,
    private val togglePostAsFavorite: ITogglePostAsFavorite,
) : ViewModel() {

    private val _viewState = MutableLiveData<PostDescriptionViewState>()
    val viewState = _viewState.asLiveData()
    private lateinit var thisPost: PostsResponse

    fun getPost(postId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getPostById.invoke(postId)
            if (result.isSuccess) {
                when (val notNullResult = result.getOrNull()) {
                    null -> setViewState(PostDescriptionViewState.NullPost)
                    else -> saveAndSent(notNullResult)
                }
            } else setViewState(PostDescriptionViewState.PostsNotFound)

        }
    }

    fun getUser(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getUserById.invoke(userId)
            if (result.isSuccess) {
                when (val notNullResult = result.getOrNull()) {
                    null -> setViewState(PostDescriptionViewState.NullPost)
                    else -> setViewState(PostDescriptionViewState.UserFetchSuccessful(notNullResult))
                }
            } else setViewState(PostDescriptionViewState.UserNotFound)

        }
    }

    fun getComments(postId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getCommentsById.invoke(postId)
            when {
                result.isSuccess -> setViewState(PostDescriptionViewState.PostCommentsList(result.getOrThrow()))
                else -> setViewState(PostDescriptionViewState.CommentsNotFound)
            }
        }
    }

    fun togglePostFromFavorites() {
        viewModelScope.launch {
            val result = togglePostAsFavorite.invoke(thisPost)
            when (val notNullResult = result.getOrNull()
            ) {
                null -> setViewState(PostDescriptionViewState.PostsNotFound)
                else -> setViewState(PostDescriptionViewState.FavoritePostsList(notNullResult))
            }
        }

    }

    private fun saveAndSent(result: PostsResponse) {
        setViewState(PostDescriptionViewState.PostFetchSuccessful(result))
        thisPost = result
    }

    private fun setViewState(newViewState: PostDescriptionViewState) {
        _viewState.postValue(newViewState)
    }
}


