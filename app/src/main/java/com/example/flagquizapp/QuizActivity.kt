package com.example.flagquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flagquizapp.databinding.ActivityMainBinding
import com.example.flagquizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var questions: ArrayList<Flags>
    private lateinit var falseAnswer: ArrayList<Flags>
    private lateinit var trueQuestion: Flags
    private lateinit var allAnswers: HashSet<Flags>
    private lateinit var vt: HelperDatabase

    private var questionCount = 0
    private var trueCount = 0
    private var falseCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vt = HelperDatabase(this)
        questions = Flagsdao().getRandomFiveFlags(vt)
        UploadQuestion()
        binding.buttonA.setOnClickListener {
            ControlQuestionCount()
        }
    }

    fun UploadQuestion(){
        binding.textViewQuestionNumber.text = "Question ${questionCount+1}"
        trueQuestion = questions.get(questionCount)

        binding.imageViewFlag.setImageResource(resources.getIdentifier(trueQuestion.bayrak_resim,"drawable", packageName))
    }

    fun ControlQuestionCount(){
        questionCount++
        if (questionCount != 5){
            UploadQuestion()
        }
        else{
            startActivity(Intent(this@QuizActivity, ResultActivity::class.java))
            finish()
        }
    }
}