package sound.recorder.widget.builder

import android.content.Context
import android.content.SharedPreferences
import sound.recorder.widget.util.Constant
import sound.recorder.widget.util.DataSession

class RecordingWidgetBuilder private constructor(
    val appName : String?,
    val versionCode : Int?,
    val versionName: String?,
    val applicationId: String?,
    val showNote : Boolean,
    val backgroundWidgetColor : String?
) {


    fun addInfo(context: Context,versionCode : Int,versionName : String, appId : String,appName : String,jsonName : String,backgroundSplashScreen : String, isNote : Boolean,showSong : Boolean, llRecordBackground : String){
        DataSession(context).setInfoApp(versionCode,versionName,appId,appName,jsonName,backgroundSplashScreen,isNote,showSong,llRecordBackground)
    }

    // Builder class to construct MyObject
    class Builder(private val context: Context) {

        private var appName : String? = null
        private var versionCode : Int? = null
        private var versionName: String?= null
        private var applicationId: String? = null
        private var showNote = false
        private var backgroundWidgetColor : String? = null

        fun setAppName(appName: String?): Builder {
            this.appName = appName
            return this
        }

        fun setVersionCode(versionCode: Int?): Builder {
            this.versionCode = versionCode
            return this
        }

        fun setVersionName(versionName: String?): Builder {
            this.versionName = versionName
            return this
        }

        fun setApplicationId(applicationId: String?): Builder {
            this.applicationId = applicationId
            return this
        }

        fun showNote(showNote: Boolean): Builder {
            this.showNote = showNote
            return this
        }

        fun setBackgroundWidgetColor(backgroundWidgetColor: String?): Builder {
            this.backgroundWidgetColor= backgroundWidgetColor
            return this
        }

        // Build function to create an instance of MyObject
        fun build(): RecordingWidgetBuilder {
            val myObject = RecordingWidgetBuilder(appName, versionCode, versionName,applicationId,showNote,backgroundWidgetColor)

            // Save values to SharedPreferences
            saveToSharedPreferences(myObject)

            return myObject
        }

        private fun saveToSharedPreferences(recordingWidgetBuilder: RecordingWidgetBuilder) {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(
                Constant.KeyShared.shareKey,
                Context.MODE_PRIVATE
            )
            val editor = sharedPreferences.edit()

            editor.putString(Constant.KeyShared.appName, recordingWidgetBuilder.applicationId)
            editor.putString(Constant.KeyShared.versionCode, recordingWidgetBuilder.versionName)
            editor.putString(Constant.KeyShared.versionName, recordingWidgetBuilder.versionName)
            editor.putString(Constant.KeyShared.applicationId, recordingWidgetBuilder.applicationId)
            editor.putBoolean(Constant.KeyShared.showNote, recordingWidgetBuilder.showNote)
            editor.putString(Constant.KeyShared.backgroundWidgetColor, recordingWidgetBuilder.backgroundWidgetColor)

            editor.apply()
        }
    }

    companion object {
        // Function to get a Builder instance
        fun builder(context: Context): Builder {
            return Builder(context)
        }
    }
}
