package com.example.myquiz

object Constants {

    const val USER_NAME:String="user_name"
    const val TOTAL_QUESTIONS:String="total_questions"
    const val CORRECT_ANSWERS: String ="correct_answers"

    fun getQuestions():ArrayList<Question>{
        var questionList=ArrayList<Question>()

        //1
        var que1=Question(
            1,
            "Which country's flag is this ?",
            R.drawable.ic_flag_of_argentina,
            "Argentina", "Austrialia","India","Armenia",
            1
        )
        questionList.add(que1)

        //2
        var que2=Question(
            2,
            "Which country's flag is this ?",
            R.drawable.ic_flag_of_australia,
            "Newzelend", "Austrialia","India","Armenia",
            2
        )
        questionList.add(que2)

        //3
        var que3=Question(
            3,
            "Which country's flag is this ?",
            R.drawable.ic_flag_of_belgium,
            "Brazil", "Denmark","Germany","Belgium",
            4
        )
        questionList.add(que3)

        //4
        var que4=Question(
            4,
            "Which country's flag is this ?",
            R.drawable.ic_flag_of_brazil,
            "Denmark", "Kuwait","Brazil","India",
            3
        )
        questionList.add(que4)

        //5
        var que5=Question(
            5,
            "Which country's flag is this ?",
            R.drawable.ic_flag_of_denmark,
            "Denmark", "Pakistan","Egypt","Ethiopia",
            1
        )
        questionList.add(que5)

        //6
        var que6=Question(
            6,
            "Which country's flag is this ?",
            R.drawable.ic_flag_of_fiji,
            "Egypt", "Germani","ShriLanka","Fiji",
            4
        )
        questionList.add(que6)

        //7
        var que7=Question(
            7,
            "Which country's flag is this ?",
            R.drawable.ic_flag_of_germany,
            "Egypt", "Germany","ShriLanka","Fiji",
            2
        )
        questionList.add(que7)

        //8
        var que8=Question(
            8,
            "Which country's flag is this ?",
            R.drawable.ic_flag_of_india,
            "India", "Iran","Bangladesh","Egypt",
            1
        )
        questionList.add(que8)

        //9
        var que9=Question(
            9,
            "Which country's flag is this ?",
            R.drawable.ic_flag_of_kuwait,
            "England", "Newzeleland","kuwait","Irak",
            3
        )
        questionList.add(que9)

        //10
        var que10=Question(
            10,
            "Which country's flag is this ?",
            R.drawable.ic_flag_of_new_zealand,
            "England", "Irak","Afghanistan","Newzeleland",
            4
        )
        questionList.add(que10)

        //----
        return questionList
    }
}