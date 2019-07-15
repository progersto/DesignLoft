package com.designloft.ui.catalog

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.widget.Toast
import com.designloft.R
import com.designloft.models.ModelItem
import com.google.ar.core.HitResult
import com.google.ar.core.Plane
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_ar.*
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val MIN_OPENGL_VERSION = 3.0

    private var arFragment: ArFragment? = null
    private var andyRenderable: ModelRenderable? = null

    private var DUCK_ASSET =
        "https://raw.githubusercontent.com/progersto/ARCore_link/master/app/sampledata/models/hamburger/model.gltf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listImage: ArrayList<ModelItem> = java.util.ArrayList()
        listImage.add(
            ModelItem(
                "гамбургер",
                "https://raw.githubusercontent.com/progersto/ARCore_link/master/app/sampledata/models/hamburger/model.gltf"
            )
        )
        listImage.add(
            ModelItem(
                "забор",
                "https://raw.githubusercontent.com/progersto/ARCore_link/master/app/sampledata/models/archive2/model.gltf"
            )
        )
        listImage.add(
            ModelItem(
                "поганка",
                "https://raw.githubusercontent.com/progersto/ARCore_link/master/app/sampledata/models/archive_/model.gltf"
            )
        )
        listImage.add(
            ModelItem(
                "Самолет",
                "https://raw.githubusercontent.com/progersto/ARCore_link/master/app/sampledata/models/plane/model.gltf"
            )
        )


        if (!checkIsSupportedDeviceOrFinish(this)) {
            return
        }

        setContentView(R.layout.activity_ar)


        arFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment) as ArFragment?


        recycler.adapter = PositionsAdapter(listImage) { event ->
            Log.d(TAG, event.link)

            DUCK_ASSET = event.link

            ModelRenderable.builder()
                .setSource(
                    this, RenderableSource.builder().setSource(
                        this,
                        Uri.parse(DUCK_ASSET),
                        RenderableSource.SourceType.GLTF2
                    )
                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build()
                )
                .setRegistryId(DUCK_ASSET)
                .build()
                .thenAccept { renderable -> andyRenderable = renderable }
                .exceptionally { throwable ->
                    longToast("Unable to load andy renderable")
                    null
                }

            arFragment!!.setOnTapArPlaneListener { hitResult: HitResult, plane: Plane, motionEvent: MotionEvent ->
                if (andyRenderable == null) {
                    return@setOnTapArPlaneListener
                }

                // Create the Anchor.
                val anchor = hitResult.createAnchor()
                val anchorNode = AnchorNode(anchor)
                anchorNode.setParent(arFragment!!.arSceneView.scene)

                // Create the transformable andy and add it to the anchor.
                val andy = TransformableNode(arFragment!!.transformationSystem)
                andy.setParent(anchorNode)
                andy.renderable = andyRenderable
                andy.select()
            }
        }


    }

    private fun checkIsSupportedDeviceOrFinish(activity: Activity): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later")
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show()
            activity.finish()
            return false
        }
        val openGlVersionString = (activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
            .deviceConfigurationInfo
            .glEsVersion
        if (java.lang.Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later")
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                .show()
            activity.finish()
            return false
        }
        return true
    }


}
