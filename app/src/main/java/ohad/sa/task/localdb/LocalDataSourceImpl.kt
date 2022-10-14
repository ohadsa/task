package ohad.sa.task.localdb

import ohad.sa.task.models.Post
import ohad.sa.task.localdb.daos.PostsDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val postsDao: PostsDao,
) : LocalDataSource {

    override suspend fun insertPost(post: Post) = postsDao.insertPost(post)
    override suspend fun getAllPosts(): List<Post> = postsDao.getAllPosts()
    override suspend fun deleteAllPosts() = postsDao.deleteAllPosts()
    override suspend fun deleteById(id: String) = postsDao.deleteById(id)
    override suspend fun insertPostList(list : List<Post>)  = postsDao.insertPostList(list)
}