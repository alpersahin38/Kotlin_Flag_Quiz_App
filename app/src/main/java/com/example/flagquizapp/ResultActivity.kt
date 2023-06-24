package com.example.flagquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flagquizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val trueCount = intent.getIntExtra("trueCount",0)
        val FalseCount = 5-trueCount
        val percentSuccess = (trueCount*100)/5
        binding.textViewResult.text = "$trueCount True $FalseCount False"

        binding.textViewPercentResult.text = "%$percentSuccess SUCCESS"

        binding.buttonAgain.setOnClickListener{
            startActivity(Intent(this@ResultActivity,MainActivity::class.java))

        }
    }
}