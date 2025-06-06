package br.com.fiap.gs1_android

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var editTextLocal: EditText
    private lateinit var editTextTipoEvento: EditText
    private lateinit var spinnerGrauImpacto: Spinner
    private lateinit var editTextData: EditText
    private lateinit var editTextPessoasAfetadas: EditText
    private lateinit var buttonIncluir: Button
    private lateinit var buttonIdentificacao: Button
    private lateinit var recyclerView: RecyclerView
    
    private val listaEventos = mutableListOf<Evento>()
    private lateinit var adapter: EventoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextLocal = findViewById(R.id.editTextLocal)
        editTextTipoEvento = findViewById(R.id.editTextTipoEvento)
        spinnerGrauImpacto = findViewById(R.id.spinnerGrauImpacto)
        editTextData = findViewById(R.id.editTextData)
        editTextPessoasAfetadas = findViewById(R.id.editTextPessoasAfetadas)
        buttonIncluir = findViewById(R.id.buttonIncluir)
        buttonIdentificacao = findViewById(R.id.buttonIdentificacao)
        recyclerView = findViewById(R.id.recyclerView)

        val grausImpacto = arrayOf("Leve", "Moderado", "Severo")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, grausImpacto)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGrauImpacto.adapter = spinnerAdapter

        adapter = EventoAdapter(listaEventos) { position ->
            removerEvento(position)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        buttonIncluir.setOnClickListener {
            adicionarEvento()
        }

        buttonIdentificacao.setOnClickListener {
            val intent = Intent(this, IdentificacaoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun adicionarEvento() {
        val local = editTextLocal.text.toString().trim()
        val tipoEvento = editTextTipoEvento.text.toString().trim()
        val grauImpacto = spinnerGrauImpacto.selectedItem.toString()
        val data = editTextData.text.toString().trim()
        val pessoasAfetadasStr = editTextPessoasAfetadas.text.toString().trim()

        if (local.isEmpty() || tipoEvento.isEmpty() || data.isEmpty() || pessoasAfetadasStr.isEmpty()) {
            Toast.makeText(this, getString(R.string.erro_campos_vazios), Toast.LENGTH_SHORT).show()
            return
        }

        val pessoasAfetadas = try {
            pessoasAfetadasStr.toInt()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, getString(R.string.erro_numero_invalido), Toast.LENGTH_SHORT).show()
            return
        }

        if (pessoasAfetadas <= 0) {
            Toast.makeText(this, getString(R.string.erro_pessoas_afetadas), Toast.LENGTH_SHORT).show()
            return
        }

        val evento = Evento(local, tipoEvento, grauImpacto, data, pessoasAfetadas)
        listaEventos.add(evento)
        
        adapter.notifyItemInserted(listaEventos.size - 1)

        limparCampos()
    }

    private fun removerEvento(position: Int) {
        if (position >= 0 && position < listaEventos.size) {
            listaEventos.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
    }

    private fun limparCampos() {
        editTextLocal.text.clear()
        editTextTipoEvento.text.clear()
        spinnerGrauImpacto.setSelection(0)
        editTextData.text.clear()
        editTextPessoasAfetadas.text.clear()
        editTextLocal.requestFocus()
    }
}