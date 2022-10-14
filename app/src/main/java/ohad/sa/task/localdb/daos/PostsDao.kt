package ohad.sa.task.localdb.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ohad.sa.task.models.Post

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: Post)

    @Query("SELECT * FROM Post")
    suspend fun getAllPosts(): List<Post>

    @Query("DELETE FROM Post")
    suspend fun deleteAllPosts()

    @Query("DELETE FROM Post WHERE postId LIKE :id ")
    suspend fun deleteById(id:String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostList(list: List<Post>)

}