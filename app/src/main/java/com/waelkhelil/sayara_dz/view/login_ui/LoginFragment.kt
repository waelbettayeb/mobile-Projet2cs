package com.waelkhelil.sayara_dz.view.login_ui

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.model.User
import com.waelkhelil.sayara_dz.view.login_ui.LoginViewModel.AuthenticationState.AUTHENTICATED
import com.waelkhelil.sayara_dz.view.login_ui.LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION
import org.json.JSONObject
import java.net.MalformedURLException
import java.net.URL
import java.util.*


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val TAG : String = "LoginFragment"
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var viewModel: LoginViewModel
    val RC_SIGN_IN: Int = 101
    val callbackManager = CallbackManager.Factory.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

              //////////////


               val accessToken:String  = loginResult.getAccessToken().getToken();

                // save accessToken to SharedPreference

                                                Log.i("Login","${accessToken}")
               var  request : GraphRequest= GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        GraphRequest.GraphJSONObjectCallback() {

                                jsonObject: JSONObject, graphResponse: GraphResponse ->


                            //Getting FB User Data

                             var facebookData :   Bundle = getFacebookData(jsonObject)!!
                            Log.i("userinfooo","${facebookData.getString("last_name")}")

                            viewModel.authenticate(facebookData.getString("last_name").plus(" ").plus(facebookData.getString("first_name")),"")
                            })

                   var parameters :Bundle = Bundle()
                parameters.putString("fields", "id,first_name,last_name,email,gender");
                request.setParameters(parameters);
                request.executeAsync();
                        }


                ///////////


            override fun onCancel() {
                Log.d(TAG, "login with facebook canceled")
            }

            override fun onError(exception: FacebookException) {
                Log.w(TAG, "signInResult:failed code=" + exception.toString())
                sendError()
            }
        })


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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun skipActivity() {

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPref.clear()
        sharedPref.putBoolean(getString(R.string.skip_key), true)
        sharedPref.commit()


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
    private fun setUser(name: String, uri: Uri): User {

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPref.clear()
        sharedPref.putString("user_name", name)
        sharedPref.putString("photo_url", uri.toString())
        sharedPref.putBoolean("is_connected", true)
        sharedPref.commit()

        viewModel.authenticate(name, uri.toString())

        return User(name, uri.toString())
    }


    override
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
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
        }

    }


     fun getFacebookData( Jobject: JSONObject) :Bundle?{
        var bundle: Bundle =  Bundle();

        try {
           var id : String = Jobject!!.getString("id");
             var profile_pic: URL
            try {
                profile_pic =  URL("https://graph.facebook.com/" + id + "/picture?type=large");
                Log.i("profile_pic", "${profile_pic}")
                bundle.putString("profile_pic", profile_pic.toString());
            } catch ( e: MalformedURLException) {
                e.printStackTrace()
                return null
            }

            bundle.putString("idFacebook", id);
            if (Jobject.has("first_name"))
                bundle.putString("first_name", Jobject.getString("first_name"));
            if (Jobject.has("last_name"))
                bundle.putString("last_name", Jobject.getString("last_name"));
            if (Jobject.has("email"))
                bundle.putString("email", Jobject.getString("email"));
            if (Jobject.has("gender"))
                bundle.putString("gender", Jobject.getString("gender"));
            Log.i("userinfo","${Jobject.getString("last_name")}")

            saveUserInfo(Jobject,profile_pic.toString())

        } catch ( e:Exception) {
            Log.d(TAG, "BUNDLE Exception : "+e.toString());
        }

        return bundle;
    }

    fun saveUserInfo(Jobject: JSONObject,photo_url:String)
    {
       //var   prefs :SharedPreferences  = PreferenceManager.getDefaultSharedPreferences(this.context)
        var editor: SharedPreferences.Editor=this.activity!!.getSharedPreferences("userInfo",MODE_PRIVATE).edit()
        //var editor: SharedPreferences.Editor  = prefs.edit()
        editor.clear()
        editor.putString("fb_first_name", Jobject.getString("first_name"));
        editor.putString("fb_last_name", Jobject.getString("last_name"));
        editor.putString("fb_email", Jobject.getString("emain"));
        editor.putString("fb_gender", Jobject.getString("gender"));
        editor.putString("fb_profileURL",photo_url );
        editor.commit() // This line is IMPORTANT !!!
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            setUser(account?.getGivenName()!!, account?.getPhotoUrl()!!)

            Log.i("info","logged as ${account?.getGivenName()}")
            // Signed in successfully, show authenticated UI.
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
//            sendError()
        }

    }
}
