package com.dewis.huertohogar.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(productId: String) {
    Scaffold(topBar = { TopAppBar(title = { Text("Detalle #$productId") }) }) { pad ->
        Column(Modifier.padding(pad).padding(16.dp)) {
            Text("Aquí podrías mostrar fotos, compatibilidad, etc.")
        }
    }
}