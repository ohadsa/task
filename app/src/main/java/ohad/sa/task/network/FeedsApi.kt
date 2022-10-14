package ohad.sa.task.network


import ohad.sa.task.models.Feed
import retrofit2.Call
import retrofit2.http.*


interface FeedsApi {

    @GET("feed.json")
    suspend fun getFeed(): Feed

    @FormUrlEncoded
    @POST("like")
    fun postLikeState(
        @Field("postId") postId: String,
        @Field("status") status: Int
    ): Call<Any>

    companion object {
        const val baseUrl = "https://dev.tedooo.com/"
    }
}


/***************************
endpoints:
https://dev.tedooo.com/feed.json
http://dev.tedooo.com/like?postId={post_id}&status={0|1}(0=dislike, 11=like)
 **************************/
