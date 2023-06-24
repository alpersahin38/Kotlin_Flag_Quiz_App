package com.example.flagquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
            TrueControl(binding.buttonA)
            ControlQuestionCount()
        }
        binding.buttonB.setOnClickListener {
            TrueControl(binding.buttonB)
            ControlQuestionCount()
        }
        binding.buttonC.setOnClickListener {
            TrueControl(binding.buttonC)
            ControlQuestionCount()
        }
        binding.buttonD.setOnClickListener {
            TrueControl(binding.buttonD)
            ControlQuestionCount()
        }
    }

    fun UploadQuestion() {
        binding.textViewQuestionNumber.text = "Question ${questionCount + 1}"
        trueQuestion = questions.get(questionCount)

        binding.imageViewFlag.setImageResource(
            resources.getIdentifier(
                trueQuestion.bayrak_resim,
                "drawable",
                packageName
            )
        )

        falseAnswer = Flagsdao().getRandomFalseThreeFlags(vt, trueQuestion.bayrak_id)

        allAnswers = HashSet()
        allAnswers.add(trueQuestion)
        allAnswers.add(falseAnswer.get(0))
        allAnswers.add(falseAnswer.get(1))
        allAnswers.add(falseAnswer.get(2))

        binding.buttonA.text = allAnswers.elementAt(0).bayrak_ad
        binding.buttonB.text = allAnswers.elementAt(1).bayrak_ad
        binding.buttonC.text = allAnswers.elementAt(2).bayrak_ad
        binding.buttonD.text = allAnswers.elementAt(3).bayrak_ad


    }

    fun ControlQuestionCount() {
        questionCount++
        if (questionCount != 5) {
            UploadQuestion()
        } else {
            val intent = Intent(this@QuizActivity, ResultActivity::class.java)
            intent.putExtra("trueCount",trueCount)

            startActivity(intent)
            finish()
        }
    }

    fun TrueControl(button: Button) {
        val buttonText = button.text.toString()
        val trueAnswer = trueQuestion.bayrak_ad

        if (buttonText == trueAnswer) {
            trueCount++
        } else {
            falseCount++
        }

        binding.textViewTrue.text = "True: $trueCount"
        binding.textViewFalse.text = "False: $falseCount"


    }
}