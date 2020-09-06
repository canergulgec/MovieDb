package com.android.domain.repository

import com.android.data.model.remote.NewSessionResponse
import io.reactivex.Single

interface NewSessionRepository {
    fun getNewSession(body: HashMap<String, Any>?): Single<NewSessionResponse>
}