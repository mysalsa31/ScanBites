package com.example.scanbite_v1_camera_function.ui.camera


import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ScanMode

import com.example.scanbite_v1_camera_function.R
import com.example.scanbite_v1_camera_function.ui.productDetail.ProductDetailFragment

private const val CAMERA_REQUEST_CODE = 101

class CameraFragment : Fragment() {



    private lateinit var tv_textView: TextView
    private lateinit var codeScanner: CodeScanner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
        val activity = requireActivity()
        val btnProductDetail: Button = view. findViewById(R.id.btnProductDetail)

        
        setupPermissions()
        codeScanner = CodeScanner(activity, scannerView)
        codeScanner.apply {
            // this will enable the codeScanner to continuously Scan any barcode/qrcode
            scanMode = ScanMode.CONTINUOUS
            // this sets the scan mode to auto focus
            autoFocusMode = AutoFocusMode.CONTINUOUS
            formats = CodeScanner.ALL_FORMATS
        }
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                Toast.makeText(activity, it.text, Toast.LENGTH_LONG).show()
                // tv_textView.text = it.text
            }
        }
        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
        //event listener to navigate to product detail page
        btnProductDetail.setOnClickListener {
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            val fragmentProductDetail = ProductDetailFragment ()
            fragmentTransaction.replace(R.id.frame_layout, fragmentProductDetail)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
    private fun setupPermissions(){
        val permission = ContextCompat.checkSelfPermission(requireContext(),
            android.Manifest.permission.CAMERA)
        if(permission != PackageManager.PERMISSION_GRANTED)
        {
            makeRequest()
        }
    }

    private fun makeRequest(){
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_REQUEST_CODE ->{
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(requireContext(), "You need the camera permission to be able to use this app!", Toast.LENGTH_SHORT).show()
                else{
                    //successful Request
                    Toast.makeText(requireContext(), "Camera permission has been accepted!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
