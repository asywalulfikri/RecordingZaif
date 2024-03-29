package recording.host

import android.annotation.SuppressLint
import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import recording.host.databinding.ActivityMainBinding
import sound.recorder.widget.RecordingSDK
import sound.recorder.widget.base.BaseActivityWidget
import sound.recorder.widget.builder.AdmobAdsBuilder
import sound.recorder.widget.builder.FanAdsBuilder
import sound.recorder.widget.builder.InMobiBuilder
import sound.recorder.widget.builder.RecordingWidgetBuilder
import sound.recorder.widget.builder.StarAppBuilder
import sound.recorder.widget.listener.FragmentListener
import sound.recorder.widget.listener.MyAdsListener
import sound.recorder.widget.listener.MyFragmentListener
import sound.recorder.widget.model.MenuConfig
import sound.recorder.widget.model.Song
import sound.recorder.widget.ui.fragment.FragmentSheetListSong
import sound.recorder.widget.ui.fragment.ListRecordFragment
import sound.recorder.widget.ui.fragment.VoiceRecordFragmentVertical

class SplashActivity : BaseActivityWidget() {

    private lateinit var sp : SoundPool


    private lateinit var binding : ActivityMainBinding

    private val listTitle = arrayOf(
        "Gundul Gundul Pacul",
        "Ampar Ampar Pisang"
    )

    private var song = ArrayList<Song>()

    private val pathRaw = arrayOf(
        "android.resource://"+BuildConfig.APPLICATION_ID+"/raw/gundul_gundul_pacul",
        "android.resource://"+BuildConfig.APPLICATION_ID+"/raw/ampar_ampar_pisang"
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        for (i in listTitle.indices) {
            val itemSong = Song()
            itemSong.title = listTitle[i]
            itemSong.pathRaw = pathRaw[i]
            song.add(itemSong)
        }
        RecordingSDK.addSong(this,song)
        RecordingSDK.run()

        FanAdsBuilder.builder(this)
            .setBannerId("")
            .setApplicationId("")
            .setInterstitialId("")
            .setEnable(false)
            .build()

        AdmobAdsBuilder.builder(this)
            .setAdmobId("")
            .setBannerId("")
            .setInterstitialId("")
            .setRewardId("")
            .setRewardInterstitialId("")
            .setNativeId("")
            .build()


        InMobiBuilder.builder(this)
            .setBannerId(1705420822194)
            .setApplicationId("1706737529085")
            .setInterstitialId(1708302528403)
            .setEnable(true)
            .build()

        StarAppBuilder.builder(this)
            .setApplicationId("205917032")
            .showBanner(false)
            .showInterstitial(false)
            .setEnable(true)
            .build()

        RecordingWidgetBuilder.builder(this)
            .setAppName("")
            .setApplicationId("")
            .setVersionCode(1)
            .setVersionName("")
            .setApplicationId("")
            .showNote(true)



        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 500)


    }

}