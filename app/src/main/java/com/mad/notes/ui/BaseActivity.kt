package com.mad.notes.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mad.notes.R
import com.mad.notes.utils.isNullOrEmpty

/**
 * @author Mukesh K Patel <mukesh.kumaar8293@gmail.com>
 * @version $Revision 1.0 $, $Date 27/10/2020
 * @since 1.0.0
 */

abstract class BaseActivity : AppCompatActivity() {

    private var networkStateReceiver: NetworkStateReceiver? = null
    private var mSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        networkStateReceiver = NetworkStateReceiver()
    }

    public override fun onStart() {
        super.onStart()
        if (networkStateReceiver != null) {
            val intentFilter = IntentFilter()
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
            registerReceiver(networkStateReceiver, intentFilter)
        }
    }

    override fun onStop() {
        super.onStop()
        if (networkStateReceiver != null) {
            unregisterReceiver(networkStateReceiver)
        }
    }

    //To remove internet broadcast receiver manually
    fun removeNetworkReceiver() {
        networkStateReceiver = null
    }

    private fun noInternetConnection() {
        val parentLayout = findViewById<View>(android.R.id.content)
        mSnackBar = Snackbar.make(
            parentLayout,
            getText(R.string.error_no_internet_message),
            Snackbar.LENGTH_INDEFINITE
        )
        mSnackBar?.show()
    }

    //Internet Connection broadcast receiver
    inner class NetworkStateReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.extras != null) {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connectivityManager.activeNetworkInfo
                if (isNullOrEmpty(networkInfo) || networkInfo?.isConnected == false) {
                    //Internet No Connected
                    noInternetConnection()
                } else {
                    //Internet Connected
                    mSnackBar?.let {
                        if (it.isShownOrQueued)
                            it.dismiss()
                    }
                }
            }
        }
    }
}