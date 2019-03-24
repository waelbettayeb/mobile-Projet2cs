package com.waelkhelil.sayara_dz.view

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

    init {
        // In this example, the user is always unauthenticated when MainActivity is launched
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
        username = ""
    }

    fun refuseAuthentication() {
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
    }

    fun authenticate(username: String, password: String) {
        if (true) {
            this.username = username
            authenticationState.value = AuthenticationState.AUTHENTICATED
        } else {
            authenticationState.value =
                AuthenticationState.INVALID_AUTHENTICATION
        }
    }


}
