package com.android.domain.repository

import com.android.base.Resource
import com.android.data.model.remote.NewSessionResponse
import kotlinx.coroutines.flow.Flow

interface NewSessionRepository {
    fun getNewSession(body: HashMap<String, Any>?): Flow<Resource<NewSessionResponse>>
}