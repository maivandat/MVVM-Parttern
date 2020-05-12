package com.sun.mvvm_pattern.data.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @SerializedName("avatar_url")
    @ColumnInfo
    val avatarUrl: String,
    @SerializedName("html_url")
    @ColumnInfo
    val htmlUrl: String,
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @SerializedName("login")
    @ColumnInfo
    val login: String,
    @SerializedName("type")
    @ColumnInfo
    val type: String
) {
    @SerializedName("url")
    @Ignore
    val url: String = ""
    @SerializedName("node_id")
    @Ignore
    val nodeId: String = ""
    @SerializedName("organizations_url")
    @Ignore
    val organizationsUrl: String = ""
    @SerializedName("received_events_url")
    @Ignore
    val receivedEventsUrl: String = ""
    @SerializedName("repos_url")
    @Ignore
    val reposUrl: String = ""
    @SerializedName("site_admin")
    @Ignore
    val siteAdmin: Boolean = false
    @SerializedName("starred_url")
    @Ignore
    val starredUrl: String = ""
    @SerializedName("subscriptions_url")
    @Ignore
    val subscriptionsUrl: String = ""
    @SerializedName("events_url")
    @Ignore
    val eventsUrl: String = ""
    @SerializedName("followers_url")
    @Ignore
    val followersUrl: String = ""
    @SerializedName("following_url")
    @Ignore
    val followingUrl: String = ""
    @SerializedName("gists_url")
    @Ignore
    val gistsUrl: String = ""
    @SerializedName("gravatar_id")
    @Ignore
    val gravatarId: String =""
}
