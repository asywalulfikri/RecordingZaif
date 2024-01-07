package recording.host

import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import recording.host.databinding.ActivityMainBinding
import sound.recorder.widget.RecordingSDK
import sound.recorder.widget.base.BaseActivityWidget
import sound.recorder.widget.ui.fragment.VoiceRecordFragmentVertical

class MainActivity : BaseActivityWidget() {

    private lateinit var sp : SoundPool

    private var ss1 = 1
    private var ss2 = 2

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RecordingSDK.run()

        sp = SoundPool(
            5,
            AudioManager.STREAM_MUSIC, 5
        )


        ss1 = sp.load(this,R.raw.dum,1)
        ss2 = sp.load(this,R.raw.dek,1)

        // btn1 = findViewById(R.id.btn1)
        // btn2 = findViewById(R.id.btn2)

        binding.btn1.setOnClickListener {
            sp.play(ss1, 1f, 1f, 0, 0, 1f)
        }

        binding.btn2.setOnClickListener {
            sp.play(ss2, 1f, 1f, 0, 0, 1f)
        }

        setupFragment(binding.recordingView.id,VoiceRecordFragmentVertical())


    }
}