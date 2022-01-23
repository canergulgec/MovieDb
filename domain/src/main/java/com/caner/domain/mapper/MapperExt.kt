package com.caner.domain.mapper

import com.caner.core.network.Resource

fun <A, B> A.mapTo(mapper: Mapper<A, B>): Resource<B> {
    return Resource.Success(mapper.to(this))
}
