package y19th.example.dagger_roomexample

import android.app.Application
import y19th.example.dagger_roomexample.dagger.AppComponent
import y19th.example.dagger_roomexample.dagger.DaggerAppComponent

class MainApp : Application() {

    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent get() = requireNotNull(_appComponent)

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}
