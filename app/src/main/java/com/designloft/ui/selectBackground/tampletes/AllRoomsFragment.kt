package com.designloft.ui.selectBackground.tampletes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.designloft.R
import com.designloft.base.BaseFragment
import com.designloft.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_products.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AllRoomsFragment : BaseFragment() {

    private lateinit var imageAdapter: ImageAdapter
    private val viewModel by sharedViewModel<MainViewModel>()
    private val tab by lazy { arguments?.getInt(TAB_ID) }

    companion object {
        const val TAG = "AllRoomsFragment"
        private const val TAB_ID = "tab_id"

        fun newInstance(tab: Int) = AllRoomsFragment().apply {
            arguments = Bundle().apply {
                putInt(TAB_ID, tab)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        include_toolbar.visibility = View.GONE

        val options = RequestOptions()
            .override(200, 200)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .error(R.drawable.no_image)
        imageAdapter = ImageAdapter(options) {
            Log.d("ddddd", " dddd")
        }

        products_adapter.adapter = imageAdapter

        viewModel.roomsPhoto.observe(myLifecycleOwner, Observer { list ->
            list?.also {
                if (tab == 0) {
                    imageAdapter.setItems(list)
                } else {
                    val listRoms = list.filter { roomImage -> roomImage.tab == tab }.toMutableList()
                    imageAdapter.setItems(listRoms)
                }
            }
        })
    }
}
