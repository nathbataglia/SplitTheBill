package br.edu.scl.ifsp.ads.splitthebill.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.scl.ifsp.ads.splitthebill.R
import br.edu.scl.ifsp.ads.splitthebill.model.Person

class PersonAdapter(
    private val context: Activity,
    private val personList: List<Person>
) : ArrayAdapter<Person>(context, R.layout.tile_person, personList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.tile_person, null, true)

        val nomeTv = rowView.findViewById<TextView>(R.id.nomeTv)
        val totalGastoTv = rowView.findViewById<TextView>(R.id.totalGastoTv)
        val debitTv = rowView.findViewById<TextView>(R.id.debitoTv)

        val person = personList[position]

        nomeTv.text = person.nome
        totalGastoTv.text = "Gastou: R$ ${"%.2f".format(person.totalGasto)}"

        if (person.debito < 0) {
            debitTv.text = "Deve: R$ ${"%.2f".format(person.debito * -1)}"
            debitTv.setTextColor(context.resources.getColor(android.R.color.holo_red_dark))
        } else {
            debitTv.text = "Receber: R$ ${"%.2f".format(person.debito)}"
            debitTv.setTextColor(context.resources.getColor(android.R.color.holo_green_dark))
        }

        return rowView
    }
}
