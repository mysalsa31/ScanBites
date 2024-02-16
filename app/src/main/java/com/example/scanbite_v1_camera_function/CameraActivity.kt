package com.example.scanbite_v1_camera_function

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.Image
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class CameraActivity : AppCompatActivity() {

    private var our_request_code : Int = 123

   @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera)
       var btnScan: Button = findViewById(R.id.btnScan)
       var barCodeImage: ImageView = findViewById(R.id.imageViewBarCode)
       var drawable = barCodeImage.drawable as BitmapDrawable
       val bitmap = drawable.bitmap

       val image = InputImage.fromBitmap(bitmap,0)

       btnScan.setOnClickListener{
           val barcodeScanner = BarcodeScanning.getClient()
           barcodeScanner.process(image)
               .addOnSuccessListener {
                   barcodes ->
                   for (barcode in barcodes) {
                       val barcodeData = barcode.rawValue
                       Toast.makeText(this@CameraActivity, barcodeData, Toast.LENGTH_SHORT).show()
                   }
               }
               .addOnFailureListener{

                   Toast.makeText(this@CameraActivity, "Barcode Scan failed!", Toast.LENGTH_SHORT).show()
               }
       }

    }
    fun takePhoto(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (intent.resolveActivity(packageManager) != null){
            startActivityForResult(intent,our_request_code)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == our_request_code && resultCode == RESULT_OK){

            val imageView : ImageView = findViewById(R.id.imageViewBarCode)

            val bitmap =data?.extras?.get("data")as Bitmap

            imageView.setImageBitmap(bitmap)
        }
    }

}

