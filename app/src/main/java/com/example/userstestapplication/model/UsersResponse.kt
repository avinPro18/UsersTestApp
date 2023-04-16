package com.example.userstestapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class UsersResponse(
    val `data`: ArrayList<UsersData>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)

@Parcelize
data class UsersData(
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
): Parcelable

data class Support(
    val text: String,
    val url: String
)
