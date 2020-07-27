package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.util.SparseBooleanArray
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var itemlist = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_multiple_choice, itemlist)

        // add button
        add.setOnClickListener{
            itemlist.add(editText.text.toString())
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
            editText.text.clear()
        }

        // clear button
        clear.setOnClickListener{
            itemlist.clear()
            adapter.notifyDataSetChanged()
        }

        // adding toast message
        listView.setOnItemClickListener {
            _, _, i, _ ->
            android.widget.Toast.makeText(this,
                "You selected the item: "+itemlist.get(i), android.widget.Toast.LENGTH_SHORT).show()
        }

        // delete button
        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    adapter.remove(itemlist.get(item))
                    item--
                }
            }

            position.clear()
            adapter.notifyDataSetChanged()

            if (count == 1) {
                android.widget.Toast.makeText(
                    this,
                    "You deleted one item", android.widget.Toast.LENGTH_SHORT
                ).show()
            } else {
                android.widget.Toast.makeText(
                    this,
                    "You deleted " + count + " items", android.widget.Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}