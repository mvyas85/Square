package com.challenge.square.util

import com.challenge.square.BuildConfig

object Constants {
    const val HEADER_API_KEY: String = BuildConfig.API_KEY
    const val BASE_URL: String = "https://s3.amazonaws.com/sq-mobile-interview/"
    const val DATABASE_NAME = "employee_db"

    const val EMP_DATA = "EMP_DATA"
    const val EMP_EMPTY_DATA = "EMP_EMPTY_DATA"
    const val EMP_MALFORMED_DATA = "EMP_MALFORMED_DATA"
}