package com.daiatech.app.ui.components.appbars

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import android.view.Window
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.util.UUID
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun saveViewAsBitmap(fileContainer: String, view: View, window: Window): Uri? =
    withContext(Dispatchers.IO) {
        val dirFile = File(fileContainer)
        if (!dirFile.exists()) dirFile.mkdirs()
        val uuid: String = UUID.randomUUID().toString().replace("-", "").substring(8)
        val fileUri: Uri? = try {
            val screenshotFile = File(fileContainer, "screenshot_$uuid.png")
            val fos = FileOutputStream(screenshotFile)
            captureView(view, window).compress(Bitmap.CompressFormat.PNG, 90, fos)
            fos.close()
            (Uri.fromFile(screenshotFile))
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        fileUri
    }

suspend fun captureView(view: View, window: Window): Bitmap =
    suspendCancellableCoroutine { continuation ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Above Android O, use PixelCopy
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val location = IntArray(2)
            view.getLocationInWindow(location)
            PixelCopy.request(
                window,
                Rect(location[0], location[1], location[0] + view.width, location[1] + view.height),
                bitmap,
                { copyResult ->
                    if (copyResult == PixelCopy.SUCCESS) {
                        continuation.resume(bitmap)
                    } else {
                        continuation.resumeWithException(RuntimeException("PixelCopy failed with result: $copyResult"))
                    }
                },
                Handler(Looper.getMainLooper())
            )
        } else {
            val tBitmap = Bitmap.createBitmap(
                view.width,
                view.height,
                Bitmap.Config.RGB_565
            )
            val canvas = Canvas(tBitmap)
            view.draw(canvas)
            canvas.setBitmap(null)
            continuation.resume(tBitmap)
        }

        continuation.invokeOnCancellation {
            // Handle cancellation if needed
        }
    }
