package com.krishna.japacounter

import android.content.DialogInterface
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.krishna.japacounter.MainActivity.Companion.IS_RESET
import com.krishna.japacounter.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivitySettingBinding
    lateinit var actionBar: ActionBar

    lateinit var sharedP: SharedPreferences
    lateinit var myEdit: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SettingTheme)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)

        actionBar = supportActionBar!!
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#CCCCFF")))
        actionBar.title = "Settings"

        sharedP = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        myEdit = sharedP.edit()

        binding.reset.setOnClickListener(this)

    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.reset -> {
                MaterialAlertDialogBuilder(this,
                    R.style.RoundShapeAppearance
                    ).setTitle("Reset Japa Counter")
                    .setMessage("All the count has been reset to 0.")
                    .setPositiveButton("Reset", DialogInterface.OnClickListener { dialog, which ->
                        myEdit.putString(MainActivity.TODAY_JAPA, "0")
                        myEdit.putString(MainActivity.TOTAL_JAPA, "0")

                        myEdit.commit()

                        IS_RESET = true

//                        showToast("Data is successfully Reset")
                        dialog.dismiss()
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    }).show()

            }
        }
    }

}