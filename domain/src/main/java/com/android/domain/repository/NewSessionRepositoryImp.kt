package com.android.domain.repository

import com.android.base.Resource
import com.android.base.ext.filterResponse
import com.android.domain.api.NewSessionApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewSessionRepositoryImp @Inject constructor(
    private val apiService: NewSessionApi
) : NewSessionRepository {

    override fun getNewSession(body: HashMap<String, Any>?) = flow {
        emit(Resource.Loading)
        val data = apiService.getNewSession(body)
        emit(data.filterResponse())
    }
}