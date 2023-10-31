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

    private lateinit var valorGastoEt2: EditText
    private lateinit var descricaoEt2: EditText

    private lateinit var valorGastoEt3: EditText
    private lateinit var descricaoEt3: EditText


    private var originalPerson: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        nomeEt = findViewById(R.id.nomeEt)
        valorGastoEt = findViewById(R.id.valorGastoEt)
        descricaoEt = findViewById(R.id.descricaoEt)

        valorGastoEt2 = findViewById(R.id.valorGastoEt2)
        descricaoEt2 = findViewById(R.id.descricaoEt2)

        valorGastoEt3 = findViewById(R.id.valorGastoEt3)
        descricaoEt3 = findViewById(R.id.descricaoEt3)

        originalPerson = intent.getParcelableExtra<Person>(EXTRA_PERSON)
        if (originalPerson != null) {
            nomeEt.setText(originalPerson!!.nome)
            valorGastoEt.setText(originalPerson!!.valorGasto.toString())
            valorGastoEt2.setText(originalPerson!!.valorGasto2.toString())
            valorGastoEt3.setText(originalPerson!!.valorGasto3.toString())
            descricaoEt.setText(originalPerson!!.descricao)
        }
    }

    fun save(view: View) {
        val nome = nomeEt.text.toString()
        val valorGasto = valorGastoEt.text.toString().toDouble()
        val valorGasto2 = valorGastoEt2.text.toString().toDouble()
        val valorGasto3 = valorGastoEt3.text.toString().toDouble()

        val descricao = descricaoEt.text.toString()
        val descricao2 = descricaoEt2.text.toString()
        val descricao3 = descricaoEt3.text.toString()

        val personId = originalPerson?.id ?: System.currentTimeMillis().toInt()

        val totalGasto = valorGasto + valorGasto2 + valorGasto3

        val person = Person(
            id = personId,
            nome = nome,
            valorGasto = valorGasto,
            valorGasto2 = valorGasto2,
            valorGasto3 = valorGasto3,
            totalGasto = totalGasto,
            debito = 0.0,
            descricao = descricao,
            itensComprados = listOf(descricao, descricao2, descricao3)
        )

        val intent = Intent().apply {
            putExtra(EXTRA_PERSON, person)
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}
