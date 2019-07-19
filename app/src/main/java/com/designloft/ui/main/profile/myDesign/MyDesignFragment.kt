package com.designloft.ui.main.profile.myDesign

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.designloft.R
import com.designloft.base.BaseFragment
import com.designloft.database.entities.MyDesignEntity
import com.designloft.database.entities.ProductEntity
import com.designloft.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_my_disign.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MyDesignFragment : BaseFragment() {

    private lateinit var myDesignAdapter: MyDesignAdapter
    private var productList = ArrayList<ProductEntity>()
    private lateinit var myDesignListener: MyDesignListener

    private val viewModel by sharedViewModel<MainViewModel>()


    companion object {
        const val TAG = "MyDesignFragment"
        private const val CATEGORY_NAME = "category_name"

        fun newInstance() = MyDesignFragment().apply {
            arguments = Bundle().apply {
                //                putString(CATEGORY_NAME, categoryName)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_disign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = RequestOptions()
            .override(200, 200)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .error(R.drawable.no_image)

        myDesignListener = object : MyDesignListener {

            override fun onItemClick(position: MyDesignEntity) {
                Log.d("MyDesignFragment", " onItemClick")
            }

            override fun onItemDelete(position: MyDesignEntity) {
                Log.d("MyDesignFragment", " onItemDelete")
            }
        }

        myDesignAdapter = MyDesignAdapter(options, myDesignListener)

        my_design_adapter.adapter = myDesignAdapter
    }
}
