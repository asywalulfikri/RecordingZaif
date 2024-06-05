package sound.recorder.widget.ui.fragment

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import sound.recorder.widget.adapter.VideoListAdapter
import sound.recorder.widget.base.BaseFragmentWidget
import sound.recorder.widget.databinding.ActivityListVideoBinding
import sound.recorder.widget.listener.MyAdsListener
import sound.recorder.widget.model.Video
import sound.recorder.widget.model.VideoWrapper

class FragmentVideo : BaseFragmentWidget(), VideoListAdapter.OnItemClickListener {

    private var mAdapter: VideoListAdapter? = null
    private var mPage = 1
    private var mVideoList = ArrayList<Video>()
    private lateinit var binding: ActivityListVideoBinding
    private var firestore: FirebaseFirestore? = FirebaseFirestore.getInstance()

    companion object {
        fun newInstance(): FragmentVideo {
            return FragmentVideo()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ActivityListVideoBinding.inflate(inflater, container, false)
        setupRecyclerView()
        load(false)
        binding.ivClose.visibility = View.GONE
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    private fun setupRecyclerView() {
        val mainMenuLayoutManager = GridLayoutManager(activity, 3)
        binding.recyclerView.layoutManager = mainMenuLayoutManager
        binding.recyclerView.setHasFixedSize(true)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun load(loadMore: Boolean) {
        try {
            firestore?.collection("videos")
                ?.get()
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        if (isAdded) {
                            val wrapper = VideoWrapper()
                            wrapper.list = ArrayList()
                            var rowList = 1
                            for (doc in task.result!!) {
                                if (rowList <= mPage * 50 && rowList > (mPage - 1) * 50) {
                                    val video = Video()
                                    video.datepublish = doc.getString("datepublish")
                                    video.description = doc.getString("description")
                                    video.thumbnail = doc.getString("thumbnail")
                                    video.url = doc.getString("url")
                                    video.title = doc.getString("title")
                                    Log.d("title", video.url + "-")
                                    wrapper.list.add(video)
                                }
                                rowList++
                            }
                            binding.progressBar.visibility = View.GONE

                            try {
                                if (wrapper.list.isNotEmpty()) {
                                    result(wrapper, loadMore)
                                    mAdapter?.notifyDataSetChanged()
                                } else if (task.result!!.isEmpty) {
                                    setToastInfo(activity, "No Data")
                                    binding.progressBar.visibility = View.GONE
                                    binding.tvEmpty.visibility = View.VISIBLE
                                }
                            }catch (e : Exception){
                                setLog(e.message.toString())
                            }
                        }
                    } else {
                        setToast(activity, "Failed get data")
                    }
                }
        } catch (e: Exception) {
            setToast(activity, e.message.toString())
        }
    }

    private fun result(wrapper: VideoWrapper?, loadMore: Boolean) {
        if (wrapper != null) {
            try {
                if (wrapper.list.isEmpty()) {
                    setToastInfo(activity, "No Data")
                } else {
                    mVideoList = ArrayList()
                    updateList(wrapper)
                    for (i in wrapper.list.indices) {
                        mVideoList.add(wrapper.list[i])
                    }
                    if (loadMore) {
                        mPage += 1
                    }
                    showList()
                }
            }catch (e : Exception){
                setLog(e.message.toString())
            }
        } else {
            setToast(activity, "No Data")
        }
    }

    private fun showList() {
        binding.recyclerView.visibility = View.VISIBLE
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateList(wrapper: VideoWrapper) {
        try {
            showList()
            if (isAdded) {
                mAdapter = VideoListAdapter(activity, wrapper.list, this)
                mAdapter?.setData(activity, wrapper.list)
                binding.recyclerView.adapter = mAdapter
                mAdapter?.notifyDataSetChanged()
            }
        }catch (e : Exception){
            setLog(e.message.toString())
        }
    }

    fun onBackPressed(): Boolean {
        MyAdsListener.setAds(true)
        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        return false
    }

    override fun onItemClick(position: Int) {
        val video = mVideoList[position]
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video.url))
        try {
            startActivity(appIntent)
        } catch (e: ActivityNotFoundException) {
            setToastError(activity, e.message.toString())
        } catch (e: Exception) {
            setToastError(activity, e.message.toString())
        }
    }
}
