package com.android.domain.repository

import com.android.base.ext.filterResponse
import com.android.data.model.remote.NewSessionResponse
import com.android.domain.api.NewSessionApi
import io.reactivex.Single
import javax.inject.Inject

class NewSessionRepositoryImp @Inject constructor(
    private val apiService: NewSessionApi
) : NewSessionRepository {

    override fun getNewSession(body: HashMap<String, Any>?): Single<NewSessionResponse> {
        return apiService.getNewSession(body).filterResponse()
    }
}