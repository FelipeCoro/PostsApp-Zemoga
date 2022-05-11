package com.felipecoronado.postsapp_zemoga.domain.usecases

import com.felipecoronado.postsapp_zemoga.data.repositories.IPostRepository.IPostsRepository
import com.felipecoronado.postsapp_zemoga.data.webservice.dtos.PostsResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.MockKSettings.relaxed
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class GetAllPostsListTest {

    @RelaxedMockK
    private var postRepository: IPostsRepository = spyk<IPostsRepository>()
    @RelaxedMockK
    lateinit var getAllPostsList: GetAllPostsList

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getAllPostsList = GetAllPostsList(postRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `When The Api call is Successful Return A List`() = runTest {
        //Given
        val myResponse = PostsResponse(1,1,"loren","ipsum")
        val myList= listOf(myResponse)


        coEvery { postRepository.getPosts() } returns Result.success(myList)

        //When
        val answerAsList = getAllPostsList()
        val answer= answerAsList.getOrNull()

        //Then
        assertThat(answerAsList).isInstanceOf(Result:: class.java)
        assertNotNull(answer)
        assertThat(answer).contains(myResponse)
        assertTrue("List should contain only 1 element",1 == answer?.size)
        assertThat(answer).isEqualTo(myList)
    }
}


