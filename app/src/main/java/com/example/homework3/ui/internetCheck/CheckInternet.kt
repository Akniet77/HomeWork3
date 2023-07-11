package com.example.homework3.ui.internetCheck

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network


class CheckInternet(private val listener: InternetCheckListener) {

    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    fun register(context: Context) {
        connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                listener.onInternetAvailable()
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                listener.onInternetLost()
            }
        }
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    fun unregister() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    interface InternetCheckListener {
        fun onInternetAvailable()
        fun onInternetLost()
    }
}
