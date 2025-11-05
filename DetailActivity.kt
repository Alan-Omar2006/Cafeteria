package com.example.starbucks.Vista

import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.starbucks.Contrato.DetailContract
import com.example.starbucks.Presentador.DetailPresenter
import com.example.starbucks.Modelo.VariedadCafe
import com.example.starbucks.R
import android.text.Editable
import android.text.TextWatcher
import android.view.View



class DetailActivity : AppCompatActivity(), DetailContract.View {

    private lateinit var presenter: DetailContract.Presenter
    private lateinit var txtNombre: TextView
    private lateinit var txtDescripcion: TextView
    private lateinit var txtPrecio: TextView
    private lateinit var txtStock: TextView
    private lateinit var edtCantidad: EditText
    private lateinit var txtTotal: TextView
    private lateinit var txtError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        txtNombre = findViewById(R.id.txtNombre)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        txtPrecio = findViewById(R.id.txtPrecio)
        txtStock = findViewById(R.id.txtStock)
        edtCantidad = findViewById(R.id.edtCantidad)
        txtTotal = findViewById(R.id.txtTotal)
        txtError = findViewById(R.id.txtError)

        presenter = DetailPresenter(this)

        val idCafe = intent.getIntExtra("ID_CAFE", -1)
        presenter.cargarCafe(idCafe)

        edtCantidad.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val cantidad = s.toString().toIntOrNull() ?: 0
                presenter.cantidadCambiada(cantidad)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun mostrarDetalles(cafe: VariedadCafe) {
        txtNombre.text = cafe.nombre
        txtDescripcion.text = cafe.descripcion
        txtPrecio.text = "Precio: $${cafe.precio}"
        txtStock.text = "Stock: ${cafe.stock}"
    }

    override fun mostrarTotal(total: String) {
        txtTotal.text = "Total a pagar: $total"
    }

    override fun mostrarError(mensaje: String) {
        txtError.visibility = View.VISIBLE
        txtError.text = mensaje
        txtError.setTextColor(Color.RED)
    }

    override fun ocultarError() {
        txtError.visibility = View.GONE
    }
}
