package com.almiladurukavak.calendartask

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProviders
import org.threeten.bp.LocalTime

class NewTimeActivity : AppCompatActivity() {

    lateinit var mealtimeText: AppCompatEditText
    lateinit var meditatingText: AppCompatEditText
    lateinit var saveButton:AppCompatButton
    lateinit var dateText:AppCompatTextView
    lateinit var time: LocalTime
    private lateinit var eventModel:EventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_time)

        eventModel=ViewModelProviders.of(this).get(EventViewModel::class.java)

        val back=findViewById<AppCompatImageView>(R.id.back)
        back.setOnClickListener(listener)


            time=LocalTime.now()


        initWidgets()


            dateText.text= CalendarUtils.selectedDate.toString()



        mealtimeText.addTextChangedListener(object:TextWatcher{


                override fun afterTextChanged(p0: Editable?) {
                    if (meditatingText.text.toString().isNotEmpty()&&mealtimeText.text.toString().isNotEmpty()){


                        if (mealtimeText.text.toString().toInt()<=24){

                            meditatingText.setText((24-mealtimeText.text.toString().toInt()).toString())

                            saveButton.alpha=1F
                            saveButton.isEnabled=true

                        }else{
                            mealtimeText.setText("24")

                        }


                    }else
                    {
                        saveButton.alpha=0.5F
                        saveButton.isEnabled=false
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }


            })





    }

    private fun initWidgets() {
        dateText=findViewById(R.id.dateText)
        mealtimeText=findViewById(R.id.mealtimeText)
        meditatingText=findViewById(R.id.meditatingText)
        saveButton=findViewById(R.id.saveButton)

     }


    val listener= View.OnClickListener { view ->

        when(view.id){

            R.id.back->{

              finish()

            }

        }



    }

    fun saveEventAction(view: View) {

        if ((mealtimeText.text.toString().toInt()+meditatingText.text.toString().toInt())==24){

            val mealTime= "Mealtime: "+mealtimeText.text.toString()
            val meditating= "Meditating: "+ meditatingText.text.toString()

            eventModel.insert(Event(null,CalendarUtils.selectedDate.toString(), mealTime,meditating))

            finish()

        }else{

            Toast.makeText(this,"The sum of the hours cannot exceed 24 hours",Toast.LENGTH_SHORT).show()
        }



    }


}