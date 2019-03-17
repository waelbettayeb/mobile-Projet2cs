package com.waelkhelil.sayara_dz.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import android.util.Log
import com.waelkhelil.sayara_dz.R
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.LoginManager
import com.facebook.login.widget.LoginButton
import com.waelkhelil.sayara_dz.database.User
import java.util.*


// TODO : take a look at https://developers.google.com/identity/sign-in/android/backend-auth
class AppIntroActivity : AppCompatActivity(){
    private val TAG : String = "AppIntroActivity"
    val RC_SIGN_IN: Int = 1
    private lateinit var mGoogleSignInClient:GoogleSignInClient
    val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.waelkhelil.sayara_dz.R.layout.activity_app_intro)

        val button_skip = findViewById<Button>(R.id.skip_button)
        button_skip.setOnClickListener{
            skipActivity()
        }
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


        // Facebook
        val loginButton = findViewById(com.waelkhelil.sayara_dz.R.id.login_button) as LoginButton
        loginButton.setReadPermissions(Arrays.asList("public_profile"))

        // Callback registration
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d(TAG, "logged with facebook")
            }

            override fun onCancel() {
                Log.d(TAG, "login with facebook canceled")
            }

            override fun onError(exception: FacebookException) {
                Log.w(TAG, "signInResult:failed code=" + exception.toString())
                sendError()
            }
        })
    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)

        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
        if(isLoggedIn){
            val profile = Profile.getCurrentProfile()
            setUser(profile.firstName, profile.getProfilePictureUri(100,100))
            switchActivity()
        }
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
    }

    fun skipActivity() {

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext).edit()
        sharedPref.clear()
        sharedPref.putBoolean(getString(R.string.skip_key), true)
        sharedPref.commit()

        switchActivity()
    }

    fun switchActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {
        }
        startActivity(intent)
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            setUser(account?.getGivenName(), account?.getPhotoUrl())
            // Signed in successfully, show authenticated UI.
            switchActivity()
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            sendError()
        }

    }

    private fun sendError(){
        val context = applicationContext
        val text = "sign In Result:failed"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, text, duration)
        toast.show()
    }
    private fun setUser(name: String?, uri:Uri?):User{
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext).edit()
        sharedPref.clear()
        sharedPref.putString("user_name", name)
        sharedPref.putString("photo_url", uri.toString())
        sharedPref.putBoolean("is_connected", true)
        sharedPref.commit()
        return User(name?:"", uri.toString())
    }
}
