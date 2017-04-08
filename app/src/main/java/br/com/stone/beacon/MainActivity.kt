package br.com.stone.beacon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.nearby.Nearby
import com.google.android.gms.nearby.messages.Message

class MainActivity : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    lateinit var googleApiClient: GoogleApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        googleApiClient = GoogleApiClient.Builder(this)
                .addApi(Nearby.MESSAGES_API)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .build()
        googleApiClient.connect()
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Toast.makeText(this, "FALHOU - ${connectionResult.errorMessage}", Toast.LENGTH_SHORT).show()
    }

    override fun onConnectionSuspended(code: Int) {
        Toast.makeText(this, "onConnectionSuspended - $code", Toast.LENGTH_SHORT).show()
    }

    override fun onConnected(connectionHint: Bundle?) {
        val message = Message("Opa!!!".toByteArray())
        Nearby.Messages.publish(googleApiClient, message)
    }
}
