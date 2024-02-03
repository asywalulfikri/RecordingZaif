package recording.host

import android.annotation.SuppressLint
import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import androidx.fragment.app.Fragment
import recording.host.databinding.ActivityMainBinding
import sound.recorder.widget.RecordingSDK
import sound.recorder.widget.base.BaseActivityWidget
import sound.recorder.widget.builder.AdmobAdsBuilder
import sound.recorder.widget.builder.FanAdsBuilder
import sound.recorder.widget.builder.RecordingWidgetBuilder
import sound.recorder.widget.builder.StarAppBuilder
import sound.recorder.widget.listener.FragmentListener
import sound.recorder.widget.listener.MyFragmentListener
import sound.recorder.widget.ui.fragment.ListRecordFragment
import sound.recorder.widget.ui.fragment.VoiceRecordFragmentVertical

class MainActivity : BaseActivityWidget(),FragmentListener {

    private lateinit var sp : SoundPool

    private var ss1 = 1
    private var ss2 = 2

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MyFragmentListener.setMyListener(this)

        RecordingSDK.run()

        val fanAdsBuilder = FanAdsBuilder.builder(this)
            .setBannerId("")
            .setApplicationId("")
            .setInterstitialId("")
            .setEnable(false)
            .build()

        val admobAdsBuilder = AdmobAdsBuilder.builder(this)
            .setAdmobId("")
            .setBannerId("")
            .setInterstitialId("")
            .setRewardId("")
            .setRewardInterstitialId("")
            .setNativeId("")
            .build()

        StarAppBuilder.builder(this)
            .setApplicationId("205917032")
            .setEnable(true)
            .build()

        val recordingWidgetBuilder = RecordingWidgetBuilder.builder(this)
            .setAppName("")
            .setApplicationId("")
            .setVersionCode(1)
            .setVersionName("")
            .setApplicationId("")
            .showNote(true)



        sp = SoundPool(
            5,
            AudioManager.STREAM_MUSIC, 5
        )


        setupBannerStarApp(binding.bannerView)

        ss1 = sp.load(this,R.raw.dum,1)
        ss2 = sp.load(this,R.raw.dek,1)

        // btn1 = findViewById(R.id.btn1)
        // btn2 = findViewById(R.id.btn2)

        binding.btn1.setOnClickListener {
            showInterstitialStarApp()
           // sp.play(ss1, 1f, 1f, 0, 0, 1f)
        }

        binding.btn2.setOnClickListener {
            sp.play(ss2, 1f, 1f, 0, 0, 1f)
        }

        setupFragment(binding.recordingView.id,VoiceRecordFragmentVertical())


    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentFileViewer)

        if (fragment is ListRecordFragment) {
            val consumed = fragment.onBackPressed()
            if (consumed) {
                return
            }
        }else{
            finish()
        }
    }

    override fun openFragment(fragment: Fragment?) {
        setupFragment(binding.fragmentFileViewer.id,fragment)
    }
}