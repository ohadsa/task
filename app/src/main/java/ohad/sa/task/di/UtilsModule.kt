package ohad.sa.task.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ohad.sa.task.network.NetworkStatusChecker
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }

    @Provides
    @Singleton
    fun provideNetworkStatusChecker(@ApplicationContext context: Context): NetworkStatusChecker =
        NetworkStatusChecker(
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        )


    @Provides
    @Singleton
    fun providePicasso(): Picasso = Picasso.get()

}

@Module
@InstallIn(SingletonComponent ::class)
object ViewModule {
    @Provides
    fun provideGridLayoutManager(@ApplicationContext context: Context): GridLayoutManager =
        GridLayoutManager(context, 1)
}