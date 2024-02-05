package sound.recorder.widget.model

import androidx.annotation.Keep
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@Keep
@JsonIgnoreProperties(ignoreUnknown = true)
class MenuConfig : Serializable {
    var appName : String? =null
    var versionName : String? =null
    var versionCode : Int? =  null
    var forceUpdate : Boolean? = null
    var maintenance : Boolean? =null
    var showDialog : Boolean? = null
    var fanAds : Boolean? =null
    var starAppAds : Boolean? =null
    var inMobiAds : Boolean? =null

}