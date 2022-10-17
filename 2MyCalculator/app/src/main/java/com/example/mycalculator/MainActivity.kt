package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity()
{
    lateinit var tvInput:TextView
    var lastNum:Boolean=false
    var lastDot:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput=findViewById(R.id.tv_input)


    }

    fun onDigit(view: View)
    {
        tvInput.append((view as AppCompatButton).text)
        lastNum=true
        lastDot=false
    }

    fun onClear(view: View)
    {
        tvInput.setText("")
    }

    fun onDecimalPoint(view: View)
    {
        if (lastNum && !lastDot)
        {
            tvInput.append(".")
            lastNum=false
            lastDot=true
        }
    }

    fun onOperator(view: View){
        tvInput.text.let {

            if (lastNum && !isOperatorAdded(it.toString())){
                tvInput.append((view as AppCompatButton).text)
                lastNum=false
                lastDot=false
            }
        }
    }

    private fun isOperatorAdded(value : String) : Boolean
    {
        return if (value.startsWith("-"))
        {
            false
        }
        else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }

    fun onEqual(view: View)
    {
        if (lastNum)
        {
            var tvValue=tvInput.text.toString()
            var prefix=""

            try {

                if (tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }

                if (tvValue.contains("-")){
                    var splitValue=tvValue.split("-")
                    var one=splitValue[0] //99
                    var two=splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    tvInput.text=removeZero((one.toDouble() - two.toDouble()).toString())    // IMPORTENT
                }

                else if (tvValue.contains("+")){
                    var splitValue=tvValue.split("+")
                    var one=splitValue[0] //99
                    var two=splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    tvInput.text=removeZero((one.toDouble() + two.toDouble()).toString())    // IMPORTENT
                }

                else if (tvValue.contains("/")){
                    var splitValue=tvValue.split("/")
                    var one=splitValue[0] //99
                    var two=splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    tvInput.text=removeZero((one.toDouble() / two.toDouble()).toString())    // IMPORTENT
                }

                else if (tvValue.contains("*")){
                    var splitValue=tvValue.split("*")
                    var one=splitValue[0] //99
                    var two=splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one=prefix+one
                    }

                    tvInput.text=removeZero((one.toDouble() * two.toDouble()).toString())    // IMPORTENT
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun removeZero(result: String):String{
        var value=result

        if (result.contains(".0")){
            value=result.substring(0,result.length-2)
        }
        return value
    }
}