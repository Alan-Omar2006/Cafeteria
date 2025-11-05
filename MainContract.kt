package com.example.starbucks.Contrato

interface MainContract {

    interface View {
        fun mostrarCafes(nombres: List<String>)
        fun navegarADetalle(idCafe: Int)
    }

    interface Presenter {
        fun cargarCafes()
        fun cafeSeleccionado(posicion: Int)
        fun verDetalles()
    }
}
