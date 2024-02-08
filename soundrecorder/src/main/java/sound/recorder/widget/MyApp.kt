package sound.recorder.widget

import android.annotation.SuppressLint
import android.app.Application
import com.facebook.ads.AudienceNetworkAds
import com.google.firebase.FirebaseApp


@SuppressLint("Registered")
open class MyApp : Application(){
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        AudienceNetworkAds.initialize(this)
    }
}