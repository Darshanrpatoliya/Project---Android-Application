package com.example.myquiz

data class Question(
    var id:Int,
    var question:String,
    var image:Int,

    var op_one:String,
    var op_two:String,
    var op_three:String,
    var op_four:String,

    var right_ans:Int
)
