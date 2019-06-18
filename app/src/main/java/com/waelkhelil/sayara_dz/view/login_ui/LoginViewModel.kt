package com.waelkhelil.sayara_dz.view.login_ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

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
        // In this example, the user is always unauthenticated when MainActivity is launched

        authenticationState.value =
            AuthenticationState.UNAUTHENTICATED
        //TODO remove this when you add the auth module
        authenticationState.value =
            AuthenticationState.AUTHENTICATED
        username = ""
    }

    fun refuseAuthentication() {
        authenticationState.value =
            AuthenticationState.UNAUTHENTICATED
    }

    fun authenticate(username: String, userPhotoUrl: String) {
            this.username = username
            this.userPhotoUrl = userPhotoUrl
            authenticationState.value =
                AuthenticationState.AUTHENTICATED

    }


}
