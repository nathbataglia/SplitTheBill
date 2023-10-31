package br.edu.scl.ifsp.ads.splitthebill.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import br.edu.scl.ifsp.ads.splitthebill.R
import br.edu.scl.ifsp.ads.splitthebill.model.Constant.EXTRA_PERSON
import br.edu.scl.ifsp.ads.splitthebill.model.Person

class PersonActivity : AppCompatActivity() {

    private lateinit var nomeEt: EditText
    private lateinit var valorGastoEt: EditText
    private lateinit var descricaoEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        nomeEt = findViewById(R.id.nomeEt)
        valorGastoEt = findViewById(R.id.valorGastoEt)
        descricaoEt = findViewById(R.id.descricaoEt)

        val person = intent.getParcelableExtra<Person>(EXTRA_PERSON)
        if (person != null) {
            nomeEt.setText(person.nome)
            valorGastoEt.setText(person.valorGasto.toString())
            descricaoEt.setText(person.descricao)
        }
    }

    fun save(view: View) {
        val nome = nomeEt.text.toString()
        val valorGasto = valorGastoEt.text.toString().toDouble()
        val descricao = descricaoEt.text.toString()

        val person = Person(
            id = System.currentTimeMillis().toInt(),
            nome = nome,
            valorGasto = valorGasto,
            debito = 0.0, // This will be calculated in MainActivity
            descricao = descricao
        )

        val intent = Intent().apply {
            putExtra(EXTRA_PERSON, person)
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}