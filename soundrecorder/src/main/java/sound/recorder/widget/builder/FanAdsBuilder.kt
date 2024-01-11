package sound.recorder.widget.builder

import android.content.Context
import android.content.SharedPreferences
import sound.recorder.widget.util.Constant

class FanAdsBuilder private constructor(
    val applicationId : String?,
    val bannerId: String?,
    val interstitialId: String?
) {

    // Builder class to construct MyObject
    class Builder(private val context: Context) {
        private var applicationId: String? = null
        private var bannerId: String? = null
        private var interstitialId: String? = null

        fun setApplicationId(applicationId: String?): Builder {
            this.applicationId = applicationId
            return this
        }

        fun setBannerId(bannerId: String?): Builder {
            this.bannerId = bannerId
            return this
        }

        fun setInterstitialId(interstitialId: String?): Builder {
            this.interstitialId = interstitialId
            return this
        }

        // Build function to create an instance of MyObject
        fun build(): FanAdsBuilder {
            val myObject = FanAdsBuilder(applicationId, bannerId, interstitialId)

            // Save values to SharedPreferences
            saveToSharedPreferences(myObject)

            return myObject
        }

        private fun saveToSharedPreferences(fanAdsBuilder: FanAdsBuilder) {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(
                Constant.KeyShared.shareKey,
                Context.MODE_PRIVATE
            )
            val editor = sharedPreferences.edit()

            editor.putString(Constant.KeyShared.fanId, fanAdsBuilder.applicationId)
            editor.putString(Constant.KeyShared.fanBannerId, fanAdsBuilder.bannerId)
            editor.putString(Constant.KeyShared.fanInterstitialId, fanAdsBuilder.interstitialId)

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
