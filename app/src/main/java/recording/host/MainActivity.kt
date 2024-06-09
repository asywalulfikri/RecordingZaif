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
import sound.recorder.widget.builder.InMobiBuilder
import sound.recorder.widget.builder.RecordingWidgetBuilder
import sound.recorder.widget.builder.StarAppBuilder
import sound.recorder.widget.listener.AdsListener
import sound.recorder.widget.listener.FragmentListener
import sound.recorder.widget.listener.MyAdsListener
import sound.recorder.widget.listener.MyFragmentListener
import sound.recorder.widget.model.MenuConfig
import sound.recorder.widget.model.Song
import sound.recorder.widget.ui.bottomSheet.BottomSheetVideo
import sound.recorder.widget.ui.fragment.FragmentSheetListSong
import sound.recorder.widget.ui.fragment.FragmentVideo
import sound.recorder.widget.ui.fragment.ListRecordFragment
import sound.recorder.widget.ui.fragment.VoiceRecordFragmentVertical
import sound.recorder.widget.util.Constant
import sound.recorder.widget.util.SnowFlakesLayout

class MainActivity : BaseActivityWidget(),FragmentListener,AdsListener {

    private lateinit var sp : SoundPool

    private var ss1 = 1
    private var ss2 = 2

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

    private lateinit var salju : SnowFlakesLayout
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupInterstitial()

        permissionNotification()

        salju = SnowFlakesLayout(this)
        salju.init()

        for (i in listTitle.indices) {
            val itemSong = Song()
            itemSong.title = listTitle[i]
            itemSong.pathRaw = pathRaw[i]
            song.add(itemSong)
        }
        RecordingSDK.addSong(this,song)

        MyFragmentListener.setMyListener(this)

        RecordingSDK.run()

        salju.startSnowing()
        binding.layoutBackground.addView(salju)

        FanAdsBuilder.builder(this)
            .setBannerId("")
            .setApplicationId("")
            .setInterstitialId("")
            .setEnable(false)
            .build()

        AdmobAdsBuilder.builder(this)
            .setAdmobId("")
            .setBannerId(Constant.AdsTesterId.admobBannerId)
            .setInterstitialId(Constant.AdsTesterId.admobInterstitialId)
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
            .showBanner(true)
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
           // showInterstitial()

            sp.play(ss1, 1f, 1f, 0, 0, 1f)
        }

        binding.btn2.setOnClickListener {
            sp.play(ss2, 1f, 1f, 0, 0, 1f)
        }


        binding.btnInterstitialAdmob.setOnClickListener {
            showInterstitial()
        }

        binding.btnInterstitialStarApp.setOnClickListener {

        }

        binding.btnVideo.setOnClickListener {
            try {
                // some code
                if (savedInstanceState == null) {
                    val fragment = FragmentVideo.newInstance()
                    MyAdsListener.setAds(false)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentFileViewer, fragment)
                        .commit()
                }
            } catch (e: Exception) {
                setToastError(e.message.toString())
            } catch (e : IllegalStateException){
                setToastError(e.message.toString())
            } catch (e : IllegalAccessException){
                setToastError(e.message.toString())
            }catch (e : NoSuchFieldException){
                setToastError(e.message.toString())
            }
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
        } else if (fragment is FragmentSheetListSong) {
            val consumed = fragment.onBackPressed()
            if (consumed) {
                return
            }
        }
        else if (fragment is FragmentVideo) {
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

    override fun onViewAds(boolean: Boolean) {

    }
}