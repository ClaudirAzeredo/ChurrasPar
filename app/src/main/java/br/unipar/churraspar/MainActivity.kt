package br.unipar.churraspar

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextHomens: EditText
    private lateinit var editTextMulheres: EditText
    private lateinit var editTextCriancas: EditText
    private lateinit var textViewCarne: TextView
    private lateinit var textViewAperitivos: TextView
    private lateinit var textViewCerveja: TextView
    private lateinit var textViewAgua: TextView
    private lateinit var textViewRefrigerante: TextView
    private lateinit var textViewParticipantes: TextView
    private lateinit var buttonCalcular: Button
    private lateinit var buttonLimpar: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando as Views
        editTextHomens = findViewById(R.id.editTextHomens)
        editTextMulheres = findViewById(R.id.editTextMulheres)
        editTextCriancas = findViewById(R.id.editTextCriancas)
        textViewCarne = findViewById(R.id.textViewCarne)
        textViewAperitivos = findViewById(R.id.textViewAperitivos)
        textViewCerveja = findViewById(R.id.textViewCerveja)
        textViewAgua = findViewById(R.id.textViewAgua)
        textViewRefrigerante = findViewById(R.id.textViewRefrigerante)
        textViewParticipantes = findViewById(R.id.textViewParticipantes)
        buttonCalcular = findViewById(R.id.buttonCalcular)
        buttonLimpar = findViewById(R.id.buttonLimpar)

        // Configurando ação do botão "Calcular"
        buttonCalcular.setOnClickListener {
            if (validarEntradas()) {
                calcularChurrasco()
            }
        }

        // Configurando ação do botão "Limpar"
        buttonLimpar.setOnClickListener {
            limparCampos()
        }
    }

    private fun validarEntradas(): Boolean {
        if (editTextHomens.text.isEmpty() || editTextMulheres.text.isEmpty() || editTextCriancas.text.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun calcularChurrasco() {
        val numHomens = editTextHomens.text.toString().toIntOrNull() ?: 0
        val numMulheres = editTextMulheres.text.toString().toIntOrNull() ?: 0
        val numCriancas = editTextCriancas.text.toString().toIntOrNull() ?: 0

        // Calcular e atualizar as quantidades
        val totalCarne = calcularCarne(numHomens, numMulheres, numCriancas)
        val totalAperitivos = calcularAperitivos(numHomens, numMulheres, numCriancas)
        val totalBebidaAlcoolica = calcularBebidaAlcoolica(numHomens, numMulheres)
        val totalAgua = calcularAgua(numMulheres, numCriancas)
        val totalRefrigerante = calcularRefrigerante(numMulheres, numCriancas)

        atualizarTextViews(totalCarne, totalAperitivos, totalBebidaAlcoolica, totalAgua, totalRefrigerante, numHomens + numMulheres + numCriancas)
    }

    private fun calcularCarne(numHomens: Int, numMulheres: Int, numCriancas: Int): Int {
        val carneHomens = numHomens * 400
        val carneMulheres = numMulheres * 300
        val carneCriancas = numCriancas * 200
        return ((carneHomens + carneMulheres + carneCriancas) * 1.1).toInt() // 10% extra
    }

    private fun calcularAperitivos(numHomens: Int, numMulheres: Int, numCriancas: Int): Int {
        val aperitivosHomens = numHomens * 150
        val aperitivosMulheres = numMulheres * 100
        val aperitivosCriancas = numCriancas * 50
        return ((aperitivosHomens + aperitivosMulheres + aperitivosCriancas) * 1.1).toInt() // 10% extra
    }

    private fun calcularBebidaAlcoolica(numHomens: Int, numMulheres: Int): Double {
        val bebidaHomens = numHomens * 4.0
        val bebidaMulheres = numMulheres * 2.0
        return (bebidaHomens + bebidaMulheres) * 1.1 // 10% extra
    }

    private fun calcularAgua(numMulheres: Int, numCriancas: Int): Double {
        val aguaMulheres = numMulheres * 2.0
        val aguaCriancas = numCriancas * 2.0
        return (aguaMulheres + aguaCriancas) * 1.1 // 10% extra
    }

    private fun calcularRefrigerante(numMulheres: Int, numCriancas: Int): Double {
        val refriMulheres = numMulheres * 1.5
        val refriCriancas = numCriancas * 1.5
        return (refriMulheres + refriCriancas) * 1.1 // 10% extra
    }

    private fun atualizarTextViews(totalCarne: Int, totalAperitivos: Int, totalBebidaAlcoolica: Double, totalAgua: Double, totalRefrigerante: Double, totalParticipantes: Int) {
        textViewCarne.text = "${totalCarne}g"
        textViewAperitivos.text = "${totalAperitivos}g"
        textViewCerveja.text = "${totalBebidaAlcoolica.toInt()}L"
        textViewAgua.text = "${totalAgua.toInt()}L"
        textViewRefrigerante.text = "${totalRefrigerante.toInt()}L"
        textViewParticipantes.text = "Realizamos o cálculo para $totalParticipantes participantes"
    }

    private fun limparCampos() {
        // Limpando os EditTexts
        editTextHomens.text.clear()
        editTextMulheres.text.clear()
        editTextCriancas.text.clear()

        // Resetando os TextViews
        textViewCarne.text = "0g"
        textViewAperitivos.text = "0g"
        textViewCerveja.text = "0L"
        textViewAgua.text = "0L"
        textViewRefrigerante.text = "0L"
        textViewParticipantes.text = "Realizamos o cálculo para 0 participantes"
    }
}
