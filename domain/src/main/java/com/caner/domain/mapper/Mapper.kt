package com.caner.domain.mapper

interface Mapper<T, E> {

    fun to(t: T): E
}
