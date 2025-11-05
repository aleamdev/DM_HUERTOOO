package com.dewis.huertohogar.ui.screens.catalog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dewis.huertohogar.data.local.AppDatabase
import com.dewis.huertohogar.data.local.Product
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CatalogViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = AppDatabase.get(app).productDao()
    val products: StateFlow<List<Product>> =
        dao.observeAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun seedIfEmpty() = viewModelScope.launch {
        if (products.value.isNotEmpty()) return@launch

        val featured = listOf(

            Product(
                sku = "FR001", name = "Manzana Fuji",
                imageUrl = "https://img.freepik.com/fotos-premium/primer-plano-manzanas-bandeja-sobre-mesa_1048944-4636814.jpg",
                oldPriceText = "$1,200", priceText = "$900 CLP por kilo",
                isFeatured = true
            ),
            Product(
                sku = "VR002", name = "Espinacas Frescas",
                imageUrl = "https://img.freepik.com/fotos-premium/recipiente-madera-espinacas-servilleta-mesa-madera_185193-20003.jpg",
                oldPriceText = "$700", priceText = "$550 por bolsa de 500g",
                isFeatured = true
            ),
            Product(
                sku = "PL001", name = "Leche Entera",
                imageUrl = "https://img.freepik.com/foto-gratis/botellas-leche-fresca-galletas-americanas_23-2148239836.jpg",
                oldPriceText = "$1,200", priceText = "$980 CLP por litro",
                isFeatured = true
            )
        )

        val catalog = listOf(

            Product(
                sku = "FR001", name = "Manzana Fuji",
                imageUrl = "https://img.freepik.com/fotos-premium/primer-plano-manzanas-bandeja-sobre-mesa_1048944-4636814.jpg",
                priceText = "$1,200 CLP por kilo",
                stockText = "150 Kilos",
                description = "Manzanas Fuji crujientes y dulces, cultivadas en el Valle del Maule..."
            ),
            Product(
                sku = "FR002", name = "Naranjas Valencia",
                imageUrl = "https://img.freepik.com/foto-gratis/vista-horizontal-naranjas-frescas-enteras-cortadas-mitad-sobre-fondo-gris_140725-140758.jpg",
                priceText = "$1,000 CLP por kilo",
                stockText = "200 kilos",
                description = "Jugosas y ricas en vitamina C, ideales para zumos frescos..."
            ),
            Product(
                sku = "FR003", name = "Platanos Cavendish",
                imageUrl = "https://img.freepik.com/fotos-premium/bananas-hojas-colocadas-suelo-madera-viejo_1048944-13107132.jpg",
                priceText = "$800 CLP por kilo",
                stockText = "250 kilos",
                description = "Plátanos maduros y dulces, ricos en potasio..."
            ),
            Product(
                sku = "VR001", name = "Zanahorias Orgánicas",
                imageUrl = "https://img.freepik.com/fotos-premium/primer-plano-zanahorias-mesa-madera_1048944-28938675.jpg",
                priceText = "$900 CLP por kilo",
                stockText = "100 kilos",
                description = "Zanahorias crujientes sin pesticidas, fuente de vitamina A..."
            ),
            Product(
                sku = "VR002", name = "Espinacas Frescas",
                imageUrl = "https://img.freepik.com/fotos-premium/recipiente-madera-espinacas-servilleta-mesa-madera_185193-20003.jpg",
                priceText = "$700 por bolsa de 500g",
                stockText = "80 bolsas",
                description = "Espinacas frescas y nutritivas..."
            ),
            Product(
                sku = "VR003", name = "Pimientos Tricolores",
                imageUrl = "https://img.freepik.com/fotos-premium/vista-alto-angulo-frutas-mesa_1048944-14440269.jpg",
                priceText = "$1,500 CLP por kilo",
                stockText = "120 kilos",
                description = "Pimientos rojos, amarillos y verdes..."
            ),
            Product(
                sku = "PO001", name = "Miel Orgánica",
                imageUrl = "https://img.freepik.com/fotos-premium/panal-miel-natural-frasco-vidrio-sobre-mesa-madera-fondo-miel-productos-abejas-concepto-ingredientes-naturales-organicos-primer-plano_114941-7489.jpg",
                priceText = "$5,000 CLP por frasco de 500g",
                stockText = "50 frascos",
                description = "Miel pura y orgánica producida por apicultores locales..."
            ),
            Product(
                sku = "PO002", name = "Quinua Orgánica",
                imageUrl = "https://img.freepik.com/fotos-premium/quinua-cocida-cruda-cristaleria-sobre-fondo-negro_392895-147299.jpg",
                priceText = "$4,000 CLP por bolsa de 500g",
                stockText = "70 bolsas",
                description = "Quinua orgánica rica en proteínas..."
            ),
            Product(
                sku = "PO003", name = "Aceite de Coco Organico",
                imageUrl = "https://img.freepik.com/fotos-premium/primer-plano-coco-dividido-dos-botella-mesa_1048944-26685155.jpg",
                priceText = "$4,500 CLP por frasco de 500ml",
                stockText = "26 frascos",
                description = "Aceite de coco 100% natural, prensado en frío..."
            ),
            Product(
                sku = "PL001", name = "Leche Entera",
                imageUrl = "https://img.freepik.com/foto-gratis/botellas-leche-fresca-galletas-americanas_23-2148239836.jpg",
                priceText = "$1,200 CLP por litro",
                stockText = "100 litros",
                description = "Leche entera fresca de granjas locales..."
            ),
            Product(
                sku = "PL002", name = "Yogur Natural",
                imageUrl = "https://img.freepik.com/fotos-premium/yogur-griego-frasco-vidrio-cucharas-madera-sobre-fondo-madera_72608-107.jpg",
                priceText = "$900 CLP por envase de 500g",
                stockText = "65 envases",
                description = "Yogur natural cremoso sin aditivos..."
            ),
            Product(
                sku = "PL003", name = "Queso Fresco Artesanal",
                imageUrl = "https://img.freepik.com/fotos-premium/queso-camembert-cremoso-sobre-fondo-natural-madera-oscura-recien-desempaquetado-pieza-separada_1048944-7664253.jpg",
                priceText = "$3,000 CLP pieza de 500g",
                stockText = "60 piezas",
                description = "Queso fresco elaborado con leche local..."
            )
        )

        (featured + catalog).forEach { dao.upsert(it) }
    }

    fun clear() = viewModelScope.launch { dao.clear() }
}