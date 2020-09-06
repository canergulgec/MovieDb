package com.android.base

interface Mapper<T, E> {

    fun from(e: E): T

    fun to(t: T): E

}