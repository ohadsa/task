package ohad.sa.task.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ohad.sa.task.localdb.ImageListConverter
import ohad.sa.task.localdb.LocalDataBase
import ohad.sa.task.localdb.LocalDataSource
import ohad.sa.task.localdb.LocalDataSourceImpl
import ohad.sa.task.localdb.daos.PostsDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DBName = "LocalDataBase"


    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): LocalDataBase =
        Room.databaseBuilder(appContext, LocalDataBase::class.java, DBName)
            .fallbackToDestructiveMigration()
            .addTypeConverter(ImageListConverter())
            .build()

    @Provides
    fun providePostsDao(spaceXDatabase: LocalDataBase): PostsDao =
        spaceXDatabase.postDao()

}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceProvider {

    @Binds
    @Singleton
    abstract fun bindDataSource(impl: LocalDataSourceImpl): LocalDataSource

}

