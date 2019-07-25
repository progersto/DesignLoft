package com.designloft.ui.selectBackground

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import com.designloft.R
import com.designloft.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_camera.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.jetbrains.anko.support.v4.toast
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CameraFragment : BaseFragment() {
    protected var mCurrentPhotoPath = ""

    companion object {
        const val TAG = "CameraFragment"
        private const val SELECTED_ID = "id_selected"
        private const val REQUEST_CAMERA = 1001

        @JvmStatic
        fun newInstance(idSelected: Int) = CameraFragment().apply {
            arguments = Bundle().apply {
                putInt(SELECTED_ID, idSelected)
            }
        }
    }

    private val idSelected by lazy { arguments?.getInt(SELECTED_ID) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_btn.visibility = View.VISIBLE
        text_toolbar.visibility = View.GONE
        filter_btn.visibility = View.GONE
        search_btn.visibility = View.GONE

        if (idSelected == 0) {
            openDefaultCamera()
        }

        new_photo.setOnClickListener { openDefaultCamera() }
        complete.setOnClickListener {

        }
        back_btn.setOnClickListener { activity?.onBackPressed() }
    }

    private fun openDefaultCamera() {
        activity?.also { activity ->
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if (takePictureIntent.resolveActivity(activity.packageManager) != null) {
                createImageFile()?.also { photoFile ->
                    takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    takePictureIntent.putExtra(
                        MediaStore.EXTRA_OUTPUT,
                        FileProvider.getUriForFile(activity, "com.designloft", photoFile)
                    )
                    startActivityForResult(takePictureIntent, REQUEST_CAMERA)
                }
            } else {
                toast(getString(R.string.fragment_camera_error_camera_message))
            }
        }
    }

    private fun createImageFile(): File? {
        val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_${timeStamp}_"
        val image = File.createTempFile(imageFileName, ".jpg", storageDir)

        // path to Photo
        mCurrentPhotoPath = image.absolutePath

        return image
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            // Show the thumbnail on ImageView
            val imageUri = Uri.parse(mCurrentPhotoPath)
            val finalPath = imageUri.toString()
            camera_image.setImageURI(imageUri)
            complete.visibility = View.VISIBLE
        } else {
            toast(R.string.fragment_camera_not_created_photo)
        }
    }
}