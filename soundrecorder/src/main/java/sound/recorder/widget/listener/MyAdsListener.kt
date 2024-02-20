package sound.recorder.widget.listener


object MyAdsListener {
    private var myListener: AdsListener? = null

    fun setMyListener(listener: AdsListener) {
        myListener = listener
    }
    fun setAds(show : Boolean) {
        myListener?.onViewAds(show)
    }

}