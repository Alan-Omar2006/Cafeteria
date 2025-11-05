package com.example.starbucks.Presentador
 import com.example.starbucks.Contrato.MainContract
 import com.example.starbucks.Modelo.CafeRepository
 import com.example.starbucks.Modelo.VariedadCafe

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    private var cafes: List<VariedadCafe> = emptyList()
    private var cafeSeleccionado: VariedadCafe? = null

    override fun cargarCafes() {
        cafes = CafeRepository.obtenerVariedadesDeCafe()
        val nombres = cafes.map { it.nombre }
        view.mostrarCafes(nombres)
    }

    override fun cafeSeleccionado(posicion: Int) {
        cafeSeleccionado = cafes[posicion]
    }

    override fun verDetalles() {
        cafeSeleccionado?.let {
            view.navegarADetalle(it.id)
        }
    }
}
