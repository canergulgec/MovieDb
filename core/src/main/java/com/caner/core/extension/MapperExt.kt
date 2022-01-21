package com.caner.core.extension

import com.caner.core.base.Mapper
import com.caner.core.network.Resource

fun <A, B> A.toModel(mapper: Mapper<A, B>): Resource<B> {
    return Resource.Success(mapper.to(this))
}
