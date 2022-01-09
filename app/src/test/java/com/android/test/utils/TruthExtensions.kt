package com.android.test.utils

import com.google.common.truth.Truth.assertThat

infix fun Any?.`should be`(expected: Any?) {
    assertThat(this).isEqualTo(expected)
}

infix fun Any?.`should not be`(expected: Any?) {
    assertThat(this).isNotEqualTo(expected)
}
