package com.designloft.ui.main.categories.products.product

import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.designloft.R
import com.designloft.base.BaseFragment
import com.designloft.models.Product
import com.designloft.ui.main.MainViewModel
import com.divyanshu.colorseekbar.ColorSeekBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_filter.*
import org.jetbrains.anko.backgroundColor
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.math.roundToInt

class FilterDialogFragment : BaseFragment() {

    private val categoryId by lazy {
        arguments?.getInt(CATEGORY_ID)
    }

    private val viewModel by sharedViewModel<MainViewModel>()
    private var filtredProductList = mutableListOf<Product>()


    companion object {
        const val TAG = "SendEmailDialogFragment"
        private const val CATEGORY_ID = "category_id"
        private const val INTERNAL_FORM_ID = "internal_form_id"

        fun newInstance(categoryId: Int) = FilterDialogFragment().apply {
            arguments = Bundle().apply {
                putInt(CATEGORY_ID, categoryId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.bottom_navigation.visibility = View.GONE
        viewModel.products.value?.also {
            filtredProductList.addAll(it)
        }

        initView()
        setDataInViews()
    }

    private fun setDataInViews() {
        viewModel.products.value?.also {
            if (it.size > 0) {
                val maxPrice = it.maxBy { product -> product.price }!!.price
                filter_price_to.text = maxPrice.toString()
                filter_price_current.text = filter_price_seekbar.progress.toString()
                filter_price_seekbar.max = maxPrice.roundToInt()

                val maxWidth = it.maxBy { product -> product.width }!!.width
                filter_width_to.text = maxWidth.toString()
                filter_width_current.text = filter_width_seekbar.progress.toString()
                filter_width_seekbar.max = maxWidth.roundToInt()

                val maxHeight = it.maxBy { product -> product.height }!!.height
                filter_height_to.text = maxHeight.toString()
                filter_height_current.text = filter_height_seekbar.progress.toString()
                filter_height_seekbar.max = maxHeight.roundToInt()

                val maxLength = it.maxBy { product -> product.length }!!.length
                filter_length_to.text = maxLength.toString()
                filter_length_current.text = filter_length_seekbar.progress.toString()
                filter_length_seekbar.max = maxLength.roundToInt()
            }
        }
    }

    private fun initView() {
        filter_reset.setOnClickListener {
            viewModel.getProductsByCategoryId(categoryId!!)
            activity?.onBackPressed()
        }
        filter_price_seekbar.setOnSeekBarChangeListener(listenerPrice)
        filter_width_seekbar.setOnSeekBarChangeListener(listenerWidth)
        filter_height_seekbar.setOnSeekBarChangeListener(listenerHeight)
        filter_length_seekbar.setOnSeekBarChangeListener(listenerLength)
        filter_from_low_btn.setOnClickListener {
            filter_from_high_btn.backgroundColor = ContextCompat.getColor(context!!, android.R.color.transparent)
            filter_from_low_btn.backgroundColor = ContextCompat.getColor(context!!, R.color.light_yellow)
            filtredProductList.sortBy { it.price }
        }
        filter_from_high_btn.setOnClickListener {
            filter_from_low_btn.backgroundColor = ContextCompat.getColor(context!!, android.R.color.transparent)
            filter_from_high_btn.backgroundColor = ContextCompat.getColor(context!!, R.color.light_yellow)
            filtredProductList.sortByDescending { it.price }
        }
        filter_color_seekbar.setOnColorChangeListener(listenerColor)
        filter_close.setOnClickListener { activity?.onBackPressed() }
        create_design.setOnClickListener {
            if (filtredProductList.size > 0) {
                if ( filter_price_current.text.toString().toInt() > 0 ) {
                    val fromPrice = filter_price_current.text.toString().toInt()
                    val filtered = filtredProductList.filter { product -> product.price <= fromPrice }.toMutableList()
                    filtredProductList = filtered
                }

                if ( filter_width_current.text.toString().toInt() > 0){
                    val fromWidth = filter_width_current.text.toString().toInt()
                    val filtered = filtredProductList.filter { product -> product.width <= fromWidth }.toMutableList()
                    filtredProductList = filtered
                }
                if ( filter_height_current.text.toString().toInt() > 0){
                    val fromHeight = filter_height_current.text.toString().toInt()
                    val filtered = filtredProductList.filter { product -> product.height <= fromHeight }.toMutableList()
                    filtredProductList = filtered
                }
                if ( filter_length_current.text.toString().toInt() > 0){
                    val fromLength = filter_length_current.text.toString().toInt()
                    val filtered = filtredProductList.filter { product -> product.length <= fromLength }.toMutableList()
                    filtredProductList = filtered
                }

                viewModel.filteredProducts.value = filtredProductList
            } else {
                viewModel.getProductsByCategoryId(categoryId!!)
            }
            activity?.onBackPressed()
        }
    }

    private val listenerColor = object : ColorSeekBar.OnColorChangeListener {
        override fun onColorChangeListener(i: Int) {
            filter_color_example.backgroundColor = i
        }
    }
    private val listenerPrice = object : SeekBar.OnSeekBarChangeListener {
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (progress != 0) {
                filter_price_current.visibility = View.VISIBLE
                filter_price_current.text = progress.toString()
            } else {
                filter_price_current.visibility = View.GONE
                filter_price_current.text = "0"
            }
        }
    }

    private val listenerWidth = object : SeekBar.OnSeekBarChangeListener {
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (progress != 0) {
                filter_width_current.visibility = View.VISIBLE
                filter_width_current.text = progress.toString()
            } else {
                filter_width_current.visibility = View.GONE
                filter_width_current.text = "0"
            }
        }
    }

    private val listenerHeight = object : SeekBar.OnSeekBarChangeListener {
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (progress != 0) {
                filter_height_current.visibility = View.VISIBLE
                filter_height_current.text = progress.toString()
            } else {
                filter_height_current.visibility = View.GONE
                filter_height_current.text = "0"
            }
        }
    }

    private val listenerLength = object : SeekBar.OnSeekBarChangeListener {
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (progress != 0) {
                filter_length_current.visibility = View.VISIBLE
                filter_length_current.text = progress.toString()
            } else {
                filter_length_current.visibility = View.GONE
                filter_length_current.text = "0"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity!!.bottom_navigation.visibility = View.VISIBLE
    }
}