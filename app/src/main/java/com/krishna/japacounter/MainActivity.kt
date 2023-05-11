package com.krishna.japacounter

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.krishna.japacounter.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var sharedP: SharedPreferences
    lateinit var myEdit : SharedPreferences.Editor

    private var count = 0
    var todayCount = 0
    var totalCount = 0

    private var cal = Calendar.getInstance()
    private var currentDate = cal.get(Calendar.DAY_OF_YEAR)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        sharedP = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        myEdit = sharedP.edit()

//        saveData()
        loadData()


      /*  var getTodayCount = sharedP.getString(TODAY_JAPA,"0")
        todayCount = getTodayCount!!.toInt()
        binding.tvTodayJapaCount.text = todayCount.toString()*/

        binding.ivFeet.setOnClickListener(this)
        binding.ivSetting.setOnClickListener(this)

    }

    override fun onPause() {
        super.onPause()
//        saveData()
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.ivFeet -> {
                count += 1

                binding.tvcurrentJapaCount.text = count.toString()
                myEdit.putString(CURRENT_JAPA, count.toString())
                myEdit.commit();

                //comment this
              /*  todayCount += 1
                binding.tvTodayJapaCount.text = todayCount.toString()
                myEdit.putString(TODAY_JAPA, todayCount.toString())
                myEdit.commit();*/

                //uncomment
                if (binding.tvcurrentJapaCount.text.toString().toInt() % 108 == 0) {

                    count = 0

                    todayCount += 1
                    binding.tvTodayJapaCount.text = todayCount.toString()
                    myEdit.putString(TODAY_JAPA, todayCount.toString())

                    totalCount += 1
                    binding.tvTotalJapaCount.text = totalCount.toString()
                    myEdit.putString(TOTAL_JAPA, totalCount.toString())

                    myEdit.commit();
                }
            }

            R.id.ivSetting -> {
                startActivity(Intent(this@MainActivity, SettingActivity::class.java))
            }

        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()

        loadData()
        if(IS_RESET){
            count = 0
            binding.tvcurrentJapaCount.text = count.toString()
        }
    }

    private fun saveData() {
        myEdit.putInt(DATE_KEY, currentDate)

        myEdit.putString(TODAY_JAPA, todayCount.toString())
        myEdit.commit()

        Log.d("MainActivity", "Today is $currentDate")
//        myEdit.apply()
    }

    private fun loadData() {

        val savedDate = sharedP.getInt(DATE_KEY, 0)
        Log.d("MainActivity_date", "Saved date is $savedDate")
        Log.d("MainActivity_date", "current date is $currentDate")

        var getTotalCount = sharedP.getString(TOTAL_JAPA,"0")
        totalCount = getTotalCount!!.toInt()
        binding.tvTotalJapaCount.text = totalCount.toString()

        if (currentDate != savedDate) {
         /*   previousSteps = totalSteps
            tv_totalSteps.text = 0.toString()*/


            binding.tvTodayJapaCount.text = 0.toString()
            todayCount = 0
            saveData()

        } else{
            var getTodayCount = sharedP.getString(TODAY_JAPA,"0")
            todayCount = getTodayCount!!.toInt()
            binding.tvTodayJapaCount.text = todayCount.toString()

            Log.d("GET_DATE", "loadData: cz today's date:::")
        }

        /*Log.d("MainActivity", "$savedNumber")
        goalSteps = goalNumber
        tv_totalStepsMax.text = goalSteps.toString()
        previousSteps = savedNumber*/

    }

    companion object{
        const val CURRENT_JAPA = "currentJapa"
        const val TODAY_JAPA = "todayJapa"
        const val TOTAL_JAPA = "todayJapa"
        const val DATE_KEY = "DATE_KEY"
        var IS_RESET = false
    }
}