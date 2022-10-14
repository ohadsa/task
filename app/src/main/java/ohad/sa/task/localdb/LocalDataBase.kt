package ohad.sa.task.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ohad.sa.task.models.Post
import ohad.sa.task.localdb.daos.PostsDao

@TypeConverters(ImageListConverter::class)
@Database(entities = [ Post::class], version = 1)
abstract class LocalDataBase: RoomDatabase() {
    abstract fun postDao(): PostsDao

}