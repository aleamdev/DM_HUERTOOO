package com.dewis.huertohogar.ui.screens.auth

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dewis.huertohogar.data.datastore.SessionDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false
)

class AuthViewModel(app: Application) : AndroidViewModel(app) {
    private val session = SessionDataStore(app)
    private val _ui = MutableStateFlow(LoginUiState())
    val ui: StateFlow<LoginUiState> = _ui

    fun onEmail(v: String) {
        _ui.value = _ui.value.copy(email = v.trim(), emailError = null)
    }
    fun onPass(v: String) {
        _ui.value = _ui.value.copy(password = v.trim(), passwordError = null)
    }

    private fun validate(): Boolean {
        val s = _ui.value
        val emailOk = Patterns.EMAIL_ADDRESS.matcher(s.email).matches()
        val passOk  = s.password.length >= 6
        _ui.value = s.copy(
            emailError = if (!emailOk) "Email inválido" else null,
            passwordError = if (!passOk) "Mínimo 6 caracteres" else null
        )
        return emailOk && passOk
    }

    fun login(onSuccess: () -> Unit) = viewModelScope.launch {
        if (!validate()) return@launch
        _ui.value = _ui.value.copy(isLoading = true)
        session.setEmail(_ui.value.email)
        _ui.value = _ui.value.copy(isLoading = false)
        onSuccess()
    }



    fun guest(onSuccess: () -> Unit) = viewModelScope.launch {
        session.setEmail("invitado@huertohogar.cl")
        onSuccess()
    }

    fun register(onDone: () -> Unit) = viewModelScope.launch {

        if (!validate()) return@launch
        // sin funcion aun
        onDone()
    }

}
