package com.designloft.ui.selectBackground

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.designloft.R
import com.designloft.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_galery.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.jetbrains.anko.support.v4.toast

class GalleryFragment : BaseFragment() {

    companion object {
        const val TAG = "GalleryFragment"
        private const val SELECTED_ID = "id_selected"
        private const val REQUEST_GALERY = 1002
        private const val RC_GET_AVATAR_IMAGE = 1002

        @JvmStatic
        fun newInstance(idSelected: Int) = GalleryFragment().apply {
            arguments = Bundle().apply {
                putInt(SELECTED_ID, idSelected)
            }
        }
    }

    private val idSelected by lazy { arguments?.getInt(SELECTED_ID) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_galery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_btn.visibility = View.VISIBLE
        text_toolbar.visibility = View.GONE
        filter_btn.visibility = View.GONE
        search_btn.visibility = View.GONE

        if (idSelected == 1) {
            openDefaultCamera()
        }

        select_photo.setOnClickListener { openDefaultCamera() }
        complete.setOnClickListener {

        }
        back_btn.setOnClickListener { activity?.onBackPressed() }
    }

    private fun openDefaultCamera() {
        startActivityForResult(
            Intent.createChooser(Intent(Intent.ACTION_GET_CONTENT).apply { type = "image/*" }, "Выберите изображение"),
            RC_GET_AVATAR_IMAGE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_GET_AVATAR_IMAGE && resultCode == Activity.RESULT_OK) {
            data?.also {
                val iStream = activity?.contentResolver?.openInputStream(it.data!!)
                val avatarImage = BitmapFactory.decodeStream(iStream)
                iStream?.close()
                gallery_image.setImageBitmap(avatarImage)
                complete.visibility = View.VISIBLE
            }
        } else {
            toast(R.string.fragment_camera_not_choose_photo)
        }
    }
}