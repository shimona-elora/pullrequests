package com.contus.pullrequests.data.model

import com.google.gson.annotations.SerializedName

data class PullRequestsResponse(
    @SerializedName("items") val products: Product?
)

data class Product(
    @SerializedName("total") val count: Int?,
    @SerializedName("product_list") val productItems: List<PullRequest>?
)

data class PullRequest(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("quantity") val quantity: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("original_price") val price: String?
)