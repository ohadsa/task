package ohad.sa.task.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ohad.sa.task.models.Post
import ohad.sa.task.localdb.LocalDataSource
import ohad.sa.task.network.FeedsApi
import ohad.sa.task.network.NetworkStatusChecker
import okhttp3.Response
import javax.inject.Inject

class FeedsRepositoryImpl @Inject constructor(
    private val feedsApi: FeedsApi,
    private val localDataSource: LocalDataSource,
    private val networkStatusChecker: NetworkStatusChecker
) : FeedsRepository {

    override fun getFeed(): Flow<List<Post>> {
        return if (networkStatusChecker.hasInternetConnection()) {
            flow {
                val list = feedsApi.getFeed().items
                localDataSource.insertPostList(list)
                emit(list)
            }
        } else {
            flow {
                emit(localDataSource.getAllPosts())
            }
        }
    }

    override suspend fun editPost(post: Post) {
        feedsApi.postLikeState(post.postId , if (post.didLike) 1 else 0)
        localDataSource.insertPost(post)
    }
}