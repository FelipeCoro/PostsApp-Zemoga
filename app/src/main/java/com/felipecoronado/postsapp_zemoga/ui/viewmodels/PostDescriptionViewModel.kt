package com.felipecoronado.postsapp_zemoga.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetCommentsById
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetPostById
import com.felipecoronado.postsapp_zemoga.domain.usecases.interfaces.IGetUserById
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
    private val getCommentsById: IGetCommentsById
) : ViewModel() {

    private val _viewState = MutableLiveData<PostDescriptionViewState>()
    val viewState = _viewState.asLiveData()

    fun getPost(postId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getPostById.invoke(postId)
            if (result.isSuccess) {
                when (val notNullResult = result.getOrNull()) {
                    null -> setViewState(PostDescriptionViewState.NullPost)
                    else -> setViewState(PostDescriptionViewState.PostFetchSuccessful(notNullResult))
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


    private fun setViewState(newViewState: PostDescriptionViewState) {
        _viewState.postValue(newViewState)
    }

}
