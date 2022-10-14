package ohad.sa.task.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppManager : Application(){

    companion object{
        fun getContext() = this
    }
}