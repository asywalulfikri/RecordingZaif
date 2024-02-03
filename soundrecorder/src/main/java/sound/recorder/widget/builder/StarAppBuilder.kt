package sound.recorder.widget.builder

import android.content.Context
import android.content.SharedPreferences
import sound.recorder.widget.util.Constant

class StarAppBuilder private constructor(
    val applicationId : String?,
    val enable : Boolean) {

    // Builder class to construct MyObject
    class Builder(private val context: Context) {
        private var applicationId: String? = null
        private var enable : Boolean = false

        fun setApplicationId(applicationId: String?): Builder {
            this.applicationId = applicationId
            return this
        }
        fun setEnable(enable: Boolean): Builder {
            this.enable = enable
            return this
        }

        // Build function to create an instance of MyObject
        fun build(): StarAppBuilder {
            val myObject = StarAppBuilder(applicationId,enable)

            // Save values to SharedPreferences
            saveToSharedPreferences(myObject)

            return myObject
        }

        private fun saveToSharedPreferences(starAppBuilder: StarAppBuilder) {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(
                Constant.KeyShared.shareKey,
                Context.MODE_PRIVATE
            )
            val editor = sharedPreferences.edit()

            editor.putString(Constant.KeyShared.starAppId, starAppBuilder.applicationId)
            editor.putBoolean(Constant.KeyShared.starAppEnable,starAppBuilder.enable)

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
