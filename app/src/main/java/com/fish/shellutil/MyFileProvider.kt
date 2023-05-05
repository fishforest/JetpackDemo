package com.fish.shellutil

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.HandlerThread
import android.os.ParcelFileDescriptor
import android.os.storage.StorageManager
import android.system.ErrnoException
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.FileNotFoundException
import java.io.IOException
import java.net.URI

class MyFileProvider: ContentProvider() {

    private lateinit var callbackThread: HandlerThread
    private lateinit var callbackHandler: Handler

    override fun onCreate(): Boolean {
        callbackThread = HandlerThread("FileProvider.CallbackThread")
        callbackThread.start()
        callbackHandler = Handler(callbackThread.looper)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?,
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?,
    ): Int {
        TODO("Not yet implemented")
    }


    override fun openFile(uri: Uri, mode: String): ParcelFileDescriptor? {
        val path = uri.fileProviderPath
        val modeBits = ParcelFileDescriptor.parseMode(mode)
//        if (path.canOpenDirectly(modeBits)) {
//            return ParcelFileDescriptor.open(path.toFile(), modeBits)
//        }
//        val options = modeBits.toOpenOptions()
//        val channel = try {
//            // Strict mode thread policy is passed through binder, but some apps (notably music
//            // players) like to open file on their main thread.
//            StrictMode::class.withoutPenaltyDeathOnNetwork {
//                path.newByteChannel(options)
//            }
//        } catch (e: IOException) {
//            throw e.toFileNotFoundException()
//        }
        val storageManager = ContextCompat.getSystemService(context!!, StorageManager::class.java)!!
        return try {
            storageManager.openProxyFileDescriptorCompat(
                modeBits, ChannelCallback(), callbackHandler
            )
        } catch (e: IOException) {
            throw e.toFileNotFoundException()
        }
    }

    private fun IOException.toFileNotFoundException(): FileNotFoundException =
        if (this is FileNotFoundException) {
            this
        } else {
            FileNotFoundException(message).apply { initCause(this@toFileNotFoundException) }
        }

    private class ChannelCallback(
    ) : ProxyFileDescriptorCallbackCompat() {
        private var offset = 0L
        private var released = false
        override fun onRelease() {
            TODO("Not yet implemented")
        }
    }

}

private val Uri.fileProviderPath: String
    get() {
        // Strip the prepended slash. A slash is always prepended because our Uri path starts with
        // our URI scheme, which can never start with a slash; but our Uri has an authority so its
        // path must start with a slash.
        val uriPath = Uri.decode(path).substring(1)
        return uriPath
    }

fun StorageManager.openProxyFileDescriptorCompat(
    mode: Int,
    callback: ProxyFileDescriptorCallbackCompat,
    handler: Handler
): ParcelFileDescriptor =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        openProxyFileDescriptor(mode, callback.toProxyFileDescriptorCallback(), handler)
    } else {
        ParcelFileDescriptor.createReliablePipe()[0]
    }
//    else {
////        // TODO: Support other modes?
////        if (mode != ParcelFileDescriptor.MODE_READ_ONLY) {
////            throw UnsupportedOperationException("mode $mode")
////        }
////        val pfds = ParcelFileDescriptor.createReliablePipe()
////        PipeWriter(pfds[1], callback, handler).start()
////        pfds[0]
//
//    }