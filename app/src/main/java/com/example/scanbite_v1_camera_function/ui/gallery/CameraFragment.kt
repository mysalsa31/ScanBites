package com.example.scanbite_v1_camera_function.ui.gallery

import android.graphics.Bitmap
import android.graphics.Camera
import android.graphics.drawable.BitmapDrawable
import android.hardware.camera2.CameraDevice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.scanbite_v1_camera_function.databinding.ActivityCameraBinding // used to FragmentgalleryBinding
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage


class CameraFragment : Fragment() {

    private var _binding: ActivityCameraBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!


    private lateinit var camera: Camera
    private lateinit var imageCapture: ImageCapture

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityCameraBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btnScan: Button = binding.btnScan
        val barCodeImage: ImageView = binding.imageViewBarCode
        val btnCamera: Button = binding.btnCamera

        btnCamera.setOnClickListener {
//            captureBarcode()
            startCamera()
        }
        btnScan.setOnClickListener {
            val drawable = barCodeImage.drawable as BitmapDrawable
            val bitmap: Bitmap = drawable.bitmap

            val image = InputImage.fromBitmap(bitmap, 0)

            val barcodeScanner = BarcodeScanning.getClient()
            barcodeScanner.process(image)
                .addOnSuccessListener { barcodes ->
                    for (barcode in barcodes) {
                        val barcodeData = barcode.rawValue
                        Toast.makeText(requireContext(), barcodeData, Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "Barcode Scan failed!", Toast.LENGTH_SHORT)
                        .show()
                }
        }


        return root
    }

    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                   // it.setSurfaceProvider(binding.textureView.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()

           /*     camera = cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture,
                )*/
            } catch (exc: Exception) {
                // Handle camera initialization error
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
