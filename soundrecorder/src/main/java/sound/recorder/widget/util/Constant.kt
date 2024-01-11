package sound.recorder.widget.util

open class Constant {


    interface KeyShared{
        companion object {
            const val appName = "appName"
            const val versionCode = "versionCode"
            const val versionName = "versionName"
            const val applicationId = "applicationId"
            const val showNote = "showNote"
            const val showSong = "showSong"
            const val backgroundWidgetColor = "llRecordBackground"



            const val shareKey = "recordingWidget"
            const val backgroundColor = "backgroundColor"
            const val volume = "volume"
            const val animation = "animation"
            const val colorWidget = "colorWidget"
            const val colorRunningText = "colorRunningText"

            const val admobBannerId = "bannerId"
            const val admobId = "admobId"
            const val admobInterstitialId = "interstitialIdName"
            const val admobRewardInterstitialId = "rewardInterstitialId"
            const val admobRewardId = "rewardId"
            const val admobNativeId = "nativeId"

            const val fanBannerId = "fanBannerId"
            const val fanId = "fanId"
            const val fanInterstitialId = "fanInterstitialId"



        }
    }


    interface typeFragment{
        companion object{
            const val listRecordFragment       = "listRecordFragment"
            const val listMusicFragment        = "listMusicFragment"
            const val listNoteFragment         = "listNoteFragment"
            const val settingFragment          = "settingFragment"
            const val videoFragment            = "videoFragment"
            const val listNoteFirebaseFragment = "listNoteFirebaseFragment"
        }
    }

}