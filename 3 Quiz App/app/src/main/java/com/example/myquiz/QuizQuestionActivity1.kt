package com.example.myquiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class QuizQuestionActivity1 : AppCompatActivity(), View.OnClickListener
{
    var mCurrentPosition:Int=1
    var mQuestionList:ArrayList<Question>? = null
    var mSelectedOptionPosition:Int=0

    var mUser_name:String?=null
    var mRightAns_Selected:Int=0

    lateinit var tvquestion:TextView
    lateinit var ivflag:ImageView
    lateinit var progressBar: ProgressBar
    lateinit var tvprogress:TextView

    lateinit var op1:TextView
    lateinit var op2:TextView
    lateinit var op3:TextView
    lateinit var op4:TextView
    lateinit var btsubmit:AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question1)

        mUser_name=intent.getStringExtra(Constants.USER_NAME)

        tvquestion=findViewById(R.id.tv_question)
        ivflag=findViewById(R.id.iv_flag)
        progressBar=findViewById(R.id.progress_bar)
        tvprogress=findViewById(R.id.tv_progress)

        op1=findViewById(R.id.op_one_tv)
        op2=findViewById(R.id.op_two_tv)
        op3=findViewById(R.id.op_three_tv)
        op4=findViewById(R.id.op_four_tv)
        btsubmit=findViewById(R.id.btn_submit)

        op1.setOnClickListener(this)
        op2.setOnClickListener(this)
        op3.setOnClickListener(this)
        op4.setOnClickListener(this)
        btsubmit.setOnClickListener(this)

        mQuestionList = Constants.getQuestions()

        setQuestion()
        //defaultOptionView()

    }

    private fun setQuestion() {

        defaultOptionView()

        var question: Question = mQuestionList!![mCurrentPosition - 1]
        progressBar.progress = mCurrentPosition

        tvprogress.text = "$mCurrentPosition/${progressBar.max}"

        tvquestion.text = question.question
        ivflag.setImageResource(question.image)
        op1.text = question.op_one
        op2.text = question.op_two
        op3.text = question.op_three
        op4.text = question.op_four

        if (mCurrentPosition == mQuestionList!!.size){
            btsubmit.text="FINISH"
        }else{
            btsubmit.text="SUBMIT"
        }
    }

    private fun defaultOptionView(){
        var options=ArrayList<TextView>()
        op1.let {
            options.add(0,it)
        }
        op2.let {
            options.add(0,it)
        }
        op3.let {
            options.add(0,it)
        }
        op4.let {
            options.add(0,it)
        }

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background= ContextCompat.getDrawable(
                this,
                R.drawable.option_border
            )
        }
    }

    private fun selectedOptionView(tv:TextView, selectedOptionNum:Int){
        defaultOptionView()

        mSelectedOptionPosition=selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(
            this,
            R.drawable.selected_op_border
        )
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.op_one_tv ->{
                op1.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.op_two_tv ->{
                op2.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.op_three_tv ->{
                op3.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.op_four_tv ->{
                op4.let {
                    selectedOptionView(it, 4)
                }
            }
            R.id.btn_submit ->{
                if (mSelectedOptionPosition==0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition<=mQuestionList!!.size ->{
                            setQuestion()
                        }else->{
                            var intent=Intent(this,FinishActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUser_name)
                            intent.putExtra(Constants.CORRECT_ANSWERS,mRightAns_Selected)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList?.size)
                            finish()
                            startActivity(intent)
                        }
                    }
                }else{
                    val question= mQuestionList?.get(mCurrentPosition-1)
                    if (question!!.right_ans != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition,R.drawable.wrong_option_border)
                    }else{
                        mRightAns_Selected++
                    }
                    answerView(question.right_ans,R.drawable.correct_option_border)

                    if (mCurrentPosition==mQuestionList!!.size){
                        btsubmit.text="FINISH"
                    }else{
                        btsubmit.text="GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition=0
                }
            }
        }
    }

    private fun answerView(answer:Int, drawableView:Int){
        when(answer){
            1 -> {
                op1.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                op2.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                op3.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                op4.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}