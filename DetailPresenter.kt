package com.example.starbucks.Presentador

import com.example.starbucks.Contrato.DetailContract
import com.example.starbucks.Modelo.CafeRepository
import com.example.starbucks.Modelo.VariedadCafe

class DetailPresenter(
    private val view: DetailContract.View
) : DetailContract.Presenter {

    private var cafe: VariedadCafe? = null

    override fun cargarCafe(id: Int) {
        cafe = CafeRepository.obtenerCafePorId(id)
        cafe?.let {
            view.mostrarDetalles(it)
        }
    }

    override fun cantidadCambiada(cantidad: Int) {
        cafe?.let {
            if (cantidad > it.stock) {
                view.mostrarError("Stock insuficiente. Máximo: ${it.stock}")
                view.mostrarTotal("$0.00")
            } else {
                view.ocultarError()
                val total = cantidad * it.precio
                view.mostrarTotal("$%.2f".format(total))
            }
        }
    }
}
