package com.android.test

import com.android.base.Resource
import com.android.data.model.remote.NewSessionResponse
import com.android.domain.repository.NewSessionRepository
import com.android.domain.usecase.NewSessionUseCase
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Test

@ExperimentalCoroutinesApi
class NewSessionUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()

    private val newSessionRepository = mock<NewSessionRepository>()
    private val newSessionUseCase = NewSessionUseCase(newSessionRepository, testDispatcher)

    @Test
    fun `new session flow emits successfully`() = runBlocking {
        val userDetails = NewSessionResponse(true, "asd")
        newSessionUseCase.stub {
            onBlocking { execute(addBody()) } doReturn flow {
                emit(Resource.Loading)
                emit(Resource.Success(userDetails))
            }
        }
        val flow = newSessionUseCase.execute(addBody())
        flow.collectIndexed { index, value ->
            if (index == 0) assert(value == Resource.Loading)
            if (index == 1) assert(value is Resource.Success)
        }
    }

    private fun addBody(): HashMap<String, Any>? {
        return object : LinkedHashMap<String, Any>() {
            init {
                put("request_token", "as")
            }
        }
    }
}