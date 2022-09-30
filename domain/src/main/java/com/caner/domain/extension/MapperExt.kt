package com.caner.domain.extension

import com.caner.domain.state.Resource
import com.caner.domain.mapper.Mapper

fun <A, B> A.mapTo(mapper: Mapper<A, B>): Resource<B> {
    return Resource.Success(mapper.to(this))
}
