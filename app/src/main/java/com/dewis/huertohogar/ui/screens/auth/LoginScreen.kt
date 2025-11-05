package com.dewis.huertohogar.ui.screens.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    onLoggedIn: () -> Unit,
    goToRegister: () -> Unit,
    vm: AuthViewModel = viewModel()
) {
    val s by vm.ui.collectAsState()
    var showRegister by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(Modifier.padding(16.dp)) {
            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(if (showRegister) "Crear cuenta" else "Ingreso", style = MaterialTheme.typography.titleLarge)

                OutlinedTextField(
                    value = s.email, onValueChange = vm::onEmail,
                    label = { Text("Email") },
                    leadingIcon = { Icon(Icons.Default.Email, null) },
                    isError = s.emailError != null,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )
                AnimatedVisibility(visible = s.emailError != null, enter = fadeIn(), exit = fadeOut()) {
                    Text(s.emailError ?: "", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                }

                OutlinedTextField(
                    value = s.password, onValueChange = vm::onPass,
                    label = { Text("Contraseña") },
                    leadingIcon = { Icon(Icons.Default.Lock, null) },
                    isError = s.passwordError != null,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                AnimatedVisibility(visible = s.passwordError != null, enter = fadeIn(), exit = fadeOut()) {
                    Text(s.passwordError ?: "", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                }
                Spacer(Modifier.height(8.dp))
                Divider()
                OutlinedButton(
                    onClick = { vm.guest(onLoggedIn) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Entrar como invitado")
                }

                if (showRegister) {
                    Button(onClick = { vm.register { showRegister = false } }, modifier = Modifier.fillMaxWidth()) {
                        Text("Registrarme")
                    }
                    TextButton(onClick = { showRegister = false }, modifier = Modifier.align(Alignment.End)) { Text("Ya tengo cuenta") }
                } else {
                    Button(onClick = { vm.login(onLoggedIn) }, modifier = Modifier.fillMaxWidth()) {
                        Text("Ingresar")
                    }
                    TextButton(onClick = { showRegister = true }, modifier = Modifier.align(Alignment.End)) { Text("Crear cuenta") }
                }
            }
        }
    }
}
