package com.felipecoronado.postsapp_zemoga.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.CommentsResponse
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.UsersResponse
import com.felipecoronado.postsapp_zemoga.postListShareViewModelTest.fakeResponse.FakeGetCommentsById
import com.felipecoronado.postsapp_zemoga.postListShareViewModelTest.fakeResponse.FakeGetUserById
import com.felipecoronado.postsapp_zemoga.postListShareViewModelTest.fakeResponse.FakeIGetPostById
import com.felipecoronado.postsapp_zemoga.postListShareViewModelTest.fakeResponse.FakeITogglePostAsFavorite
import com.felipecoronado.postsapp_zemoga.ui.viewstates.PostDescriptionViewState
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PostDescriptionViewModelTest {

    private var mockkPostsResponse = mockk<PostsResponse>()
    private var mockkUsersResponse = mockk<UsersResponse>()
    private var mockkCommentsResponse = mockk<List<CommentsResponse>>()

    private lateinit var viewModel: PostDescriptionViewModel
    private val fakeGetPostByID: FakeIGetPostById = spyk(FakeIGetPostById())
    private val fakeGetUserByID: FakeGetUserById = spyk(FakeGetUserById())
    private val fakeGetCommentsById: FakeGetCommentsById = spyk(FakeGetCommentsById())
    private val fakeTogglePostAsFavourite: FakeITogglePostAsFavorite = spyk(
        FakeITogglePostAsFavorite()
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModel = PostDescriptionViewModel(
            fakeGetPostByID,
            fakeGetUserByID,
            fakeGetCommentsById,
            fakeTogglePostAsFavourite
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun getPostSuccess() = runTest {
        //Given
        coEvery { fakeGetPostByID.invoke(any()) }.returns(Result.success(mockkPostsResponse))

        //When
        viewModel.getPost(0)
        val dataValue = viewModel.viewState.getOrAwaitValueTest()

        //Then
        assertThat(dataValue).isInstanceOf(PostDescriptionViewState.PostFetchSuccessful::class.java)
    }

    @Test
    fun getUserSuccess() = runTest {
        //Given
        coEvery { fakeGetUserByID.invoke(any()) }.returns(Result.success(mockkUsersResponse))

        //When
        viewModel.getUser(0)
        val dataValue = viewModel.viewState.getOrAwaitValueTest()
        //Then
        assertThat(dataValue).isInstanceOf(PostDescriptionViewState.UserFetchSuccessful::class.java)
    }

    @Test
    fun getCommentsSuccess() = runTest {
        //Given
        coEvery { fakeGetCommentsById.invoke(any()) }.returns(Result.success(mockkCommentsResponse))

        //When
        viewModel.getComments(0)
        val dataValue = viewModel.viewState.getOrAwaitValueTest()

        //Then
        assertThat(dataValue).isInstanceOf(PostDescriptionViewState.PostCommentsList::class.java)
    }
}
