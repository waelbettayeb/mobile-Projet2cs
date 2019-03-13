package com.waelkhelil.sayara_dz.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.waelkhelil.sayara_dz.R
import android.widget.Toast




class AppIntroActivity : AppCompatActivity() {
    val RC_SIGN_IN: Int = 1
    private lateinit var mGoogleSignInClient:GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.waelkhelil.sayara_dz.R.layout.activity_app_intro)

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
           mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val button_sign_in = findViewById<com.google.android.gms.common.SignInButton>(R.id.sign_in_button)
        button_sign_in.setOnClickListener{
                signIn()
            }

        val button_skip = findViewById<Button>(R.id.skip_button)
        button_skip.setOnClickListener{
            skipActivity()
        }
    }
    fun skipActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {
        }
        startActivity(intent)
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            skipActivity()
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("AppIntroActivity", "signInResult:failed code=" + e.statusCode)
            val context = applicationContext
            val text = "sign In Result:failed"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }

    }
}
