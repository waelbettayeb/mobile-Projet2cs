package com.waelkhelil.sayara_dz.view.login_ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.HttpMethod
import com.facebook.login.LoginManager


class LoginViewModel : ViewModel() {
    enum class AuthenticationState {
        AUTHENTICATED,          // Initial state, the user needs to authenticate
        UNAUTHENTICATED,        // The user has authenticated successfully
        INVALID_AUTHENTICATION  // Authentication failed
    }

    val authenticationState = MutableLiveData<AuthenticationState>()
    var username = ""
    var userPhotoUrl = ""


    init {

        initAuthenticationState()

    }

    fun refuseAuthentication() {
        authenticationState.value =
            AuthenticationState.UNAUTHENTICATED
    }

    fun initAuthenticationState()
    {
        val token: AccessToken?
        token = AccessToken.getCurrentAccessToken()

        if (token == null) {
            authenticationState.value =
                AuthenticationState.UNAUTHENTICATED
            username = "Default"
        }
        else {
            authenticationState.value =
                AuthenticationState.AUTHENTICATED
        }
    }

    fun authenticate(username: String, userPhotoUrl: String) {
            this.username = username
            this.userPhotoUrl = userPhotoUrl
            authenticationState.value =
                AuthenticationState.AUTHENTICATED


    }

    fun getUser():String{

        return this.username}
    fun logOut()
    {


            if (AccessToken.getCurrentAccessToken() == null) {

                return; // already logged out
            }

            GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null,
                HttpMethod.DELETE,  GraphRequest
            .Callback() {
                fun onCompleted( graphResponse:GraphResponse) {

                    LoginManager.getInstance().logOut()


                }
        }).executeAsync();


    }


    }




