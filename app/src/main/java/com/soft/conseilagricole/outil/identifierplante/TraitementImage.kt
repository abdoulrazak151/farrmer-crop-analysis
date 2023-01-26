package com.soft.conseilagricole

import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions


    class TraitementImage {
        fun runObjectDetector(bitmap: Bitmap):String{
            val image=InputImage.fromBitmap(bitmap, 0)
            var a:String=""
            val options=ObjectDetectorOptions.Builder()
                .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
                .enableMultipleObjects()
                .enableClassification()
                .build()
            val objectDetector = ObjectDetection.getClient(options)
            objectDetector.process(image)
                .addOnSuccessListener{
//                return@addOnSuccessListener
                    a= objectDetector.toString()
                }
                .addOnFailureListener{
                    return@addOnFailureListener
                    a="erreur"
                }
            return  a

        }

    }