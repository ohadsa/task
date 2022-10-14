package ohad.sa.task.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build

class NetworkStatusChecker(
    private val connectivityManager: ConnectivityManager?
) {

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    //listener to connectivity changes.. -> can also use broadcast receiver for that
    fun addNetworkChangeListener(networkCallback: ConnectivityManager.NetworkCallback){
        connectivityManager?.requestNetwork(networkRequest, networkCallback)
    }


    //inline methods are copied to the user class
    inline fun performIfConnectedToInternet(action: () -> Unit) {
        if (hasInternetConnection()) {
            action()
        }
    }


    //taught about better way to check internet connection is just ping to google.com ...

    fun hasInternetConnection(): Boolean {
        val network = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager?.activeNetwork ?: return false
        }else null
        val capabilities = connectivityManager?.getNetworkCapabilities(network) ?: return false

        //if capabilities is WIFI/CELLULAR/VPN true
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }




}