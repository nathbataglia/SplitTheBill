package br.edu.scl.ifsp.ads.splitthebill.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import br.edu.scl.ifsp.ads.splitthebill.R
import br.edu.scl.ifsp.ads.splitthebill.adapter.PersonAdapter
import br.edu.scl.ifsp.ads.splitthebill.model.Constant.EXTRA_PERSON
import br.edu.scl.ifsp.ads.splitthebill.model.Constant.VIEW_PERSON
import br.edu.scl.ifsp.ads.splitthebill.model.Person

class MainActivity : AppCompatActivity() {
    private lateinit var personList: MutableList<Person>
    private lateinit var personLv: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        personList = mutableListOf()
        personLv = findViewById(R.id.personLv)
        val addBt = findViewById<Button>(R.id.addBt)

        val adapter = PersonAdapter(this, personList)
        personLv.adapter = adapter

        registerForContextMenu(personLv)

        addBt.setOnClickListener {
            val intent = Intent(this, PersonActivity::class.java)
            startActivityForResult(intent, 1)
        }

        personLv.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, PersonActivity::class.java).apply {
                putExtra(VIEW_PERSON, personList[position])
            }
            startActivityForResult(intent, 2)
        }

        personLv.setOnItemClickListener{ parent, view, position, id ->
            val contact = personList[position]
            val viewContactIntent = Intent(this, PersonActivity::class.java)
            viewContactIntent.putExtra(EXTRA_PERSON, contact)
            viewContactIntent.putExtra(VIEW_PERSON, true)
            startActivity(viewContactIntent)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu_main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when (item.itemId) {
            R.id.editarPersonMi -> {
                val intent = Intent(this, PersonActivity::class.java).apply {
                    putExtra(EXTRA_PERSON, personList[info.position])
                }
                startActivityForResult(intent, 2)
            }
            R.id.removerPersonMi -> {
                personList.removeAt(info.position)
                (personLv.adapter as PersonAdapter).notifyDataSetChanged()
                calculateDebits()
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && data != null) {
            val person = data.getParcelableExtra<Person>(EXTRA_PERSON) ?: return

            if (requestCode == 1) {
                // Adiciona
                personList.add(person)
            } else if (requestCode == 2) {
                // Edita
                val index = personList.indexOfFirst { it.id == person.id }
                if (index != -1) {
                    personList[index] = person
                }
            }

            calculateDebits()
            (personLv.adapter as PersonAdapter).notifyDataSetChanged()
        }
    }

    private fun calculateDebits() {
        val totalSpent = personList.sumByDouble { it.valorGasto }
        val equalShare = totalSpent / personList.size

        for (person in personList) {
            person.debito = person.valorGasto - equalShare
        }
    }
}