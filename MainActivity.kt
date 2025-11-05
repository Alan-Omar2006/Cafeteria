package com.example.starbucks.Vista

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.starbucks.Contrato.MainContract
import com.example.starbucks.Presentador.MainPresenter
import com.example.starbucks.R
import android.view.View


class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter
    private lateinit var spinner: Spinner
    private lateinit var btnDetalles: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinnerCafes)
        btnDetalles = findViewById(R.id.btnVerDetalles)

        presenter = MainPresenter(this)
        presenter.cargarCafes()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                presenter.cafeSeleccionado(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        btnDetalles.setOnClickListener {
            presenter.verDetalles()
        }
    }

    override fun mostrarCafes(nombres: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, nombres)
        spinner.adapter = adapter
    }

    override fun navegarADetalle(idCafe: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("ID_CAFE", idCafe)
        startActivity(intent)
    }
}
