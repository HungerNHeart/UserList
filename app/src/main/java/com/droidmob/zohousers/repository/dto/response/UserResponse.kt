package com.droidmob.zohousers.repository.dto.response

import com.droidmob.zohousers.repository.dto.common.UserData
import com.squareup.moshi.Json

data class UserResponse(
        val page: Int,
        @Json(name = "per_page")
        val itemsPerPage: Int,
        val total: Int,
        @Json(name = "total_pages")
        val totalPages: Int,
        @Json(name = "data")
        val users: MutableList<UserData>
)