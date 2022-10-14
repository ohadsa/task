package ohad.sa.task.localdb

import ohad.sa.task.models.Post
import java.util.concurrent.Flow


interface LocalDataSource {

    suspend fun insertPost(post: Post)
    suspend fun getAllPosts(): List<Post>
    suspend fun deleteAllPosts()
    suspend fun deleteById(id:String)
    suspend fun insertPostList(list : List<Post>)

}