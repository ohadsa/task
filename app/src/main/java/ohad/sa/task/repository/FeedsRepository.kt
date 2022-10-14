package ohad.sa.task.repository

import kotlinx.coroutines.flow.Flow
import ohad.sa.task.models.Post

interface FeedsRepository {
    fun getFeed(): Flow<List<Post>>
    suspend fun editPost(post: Post)
}