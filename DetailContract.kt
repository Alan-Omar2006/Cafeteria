package com.example.starbucks.Contrato

import com.example.starbucks.Modelo.VariedadCafe

interface DetailContract {

    interface View {
        fun mostrarDetalles(cafe: VariedadCafe)
        fun mostrarTotal(total: String)
        fun mostrarError(mensaje: String)
        fun ocultarError()
    }

    interface Presenter {
        fun cargarCafe(id: Int)
        fun cantidadCambiada(cantidad: Int)
    }
}
