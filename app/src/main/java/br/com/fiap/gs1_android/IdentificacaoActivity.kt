package br.com.fiap.gs1_android

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class IdentificacaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identificacao)

        val buttonVoltar = findViewById<Button>(R.id.buttonVoltar)
        
        buttonVoltar.setOnClickListener {
            finish()
        }
    }
}