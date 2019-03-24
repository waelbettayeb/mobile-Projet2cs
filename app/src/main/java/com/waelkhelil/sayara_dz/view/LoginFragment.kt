package com.waelkhelil.sayara_dz.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import com.facebook.*
import com.facebook.login.widget.LoginButton
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.User
import com.waelkhelil.sayara_dz.view.LoginViewModel.AuthenticationState.*
import java.util.*


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val TAG : String = "LoginFragment"
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var viewModel: LoginViewModel
    val RC_SIGN_IN: Int = 1
    val callbackManager = CallbackManager.Factory.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        val button_skip = view.findViewById<Button>(R.id.skip_button)
        button_skip.setOnClickListener{
            skipActivity()
        }
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(activity as Activity, gso)

        val buttonSignIn = view.findViewById<com.google.android.gms.common.SignInButton>(R.id.sign_in_button)
        buttonSignIn.setOnClickListener{
            signIn()
        }


        // Facebook
        val loginButton = view.findViewById(com.waelkhelil.sayara_dz.R.id.login_button) as LoginButton
        loginButton.setReadPermissions(Arrays.asList("public_profile"))
        loginButton.setFragment(this);
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

//        requireActivity().addOnBackPressedCallback(viewLifecycleOwner, OnBackPressedCallback {
//            viewModel.refuseAuthentication()
//            navController.popBackStack(R.id.main_fragment, false)
//            true
//        })

        val navController = findNavController(view)
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                AUTHENTICATED -> navController.popBackStack()
                INVALID_AUTHENTICATION ->
                    Snackbar.make(view,
                        R.string.invalid_credentials,
                        Snackbar.LENGTH_SHORT
                    ).show()
            }
        })
    }

    fun skipActivity() {

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPref.clear()
        sharedPref.putBoolean(getString(R.string.skip_key), true)
        sharedPref.commit()

        switchActivity()
    }

    fun switchActivity() {

    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }



    private fun sendError(){
        val text = "sign In Result:failed"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, text, duration)
        toast.show()
    }
    private fun setUser(name: String?, uri: Uri?): User {
        Log.i(TAG, "user name = $name")
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPref.clear()
        sharedPref.putString("user_name", name)
        sharedPref.putString("photo_url", uri.toString())
        sharedPref.putBoolean("is_connected", true)
        sharedPref.commit()
        return User(name?:"", uri.toString())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }

        callbackManager?.onActivityResult(requestCode, resultCode, data)
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired

        if (isLoggedIn) {
            val profile = Profile.getCurrentProfile()
            setUser(profile.firstName, profile.getProfilePictureUri(100, 100))
            switchActivity()
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

//            setUser(account?.getGivenName(), account?.getPhotoUrl())
            // Signed in successfully, show authenticated UI.
//            switchActivity()
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
//            sendError()
        }

    }
}
