package ohad.sa.task.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ohad.sa.task.repository.FeedsRepository
import ohad.sa.task.repository.FeedsRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun bindRepository(impl: FeedsRepositoryImpl) : FeedsRepository

}
