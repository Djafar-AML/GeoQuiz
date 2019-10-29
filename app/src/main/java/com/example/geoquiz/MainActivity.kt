package com.example.geoquiz

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.geoquiz.databinding.ActivityMainBinding
import com.example.geoquiz.model.Question

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        DataBindingUtil.setContentView(this , R.layout.activity_main) as ActivityMainBinding
    }
    
    private val questionBanks = listOf(
                    Question(R.string.question_australia , true) ,
                    Question(R.string.question_oceans , true) ,
                    Question(R.string.question_mideast , false) ,
                    Question(R.string.question_africa , false) ,
                    Question(R.string.question_americas , true) ,
                    Question(R.string.question_asia , true))
    
    private var currentIndex = 0
    
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding.trueButton.setOnClickListener {
            checkAnswer(true)
        }
        
        binding.falseButton.setOnClickListener {
            checkAnswer(false)
        }
        
        updateQuestion()
        
        binding.nextButton.setOnClickListener {
            
            currentIndex = (currentIndex + 1) % questionBanks.size
            updateQuestion()
            
        }
    }
    
    private fun updateQuestion() {
        
        val questionTextResId = questionBanks[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
        
    }
    
    private fun checkAnswer(userAnswer : Boolean) {
        
        val correctAnswer = questionBanks[currentIndex].answer
        
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        }
        else {
            R.string.incorrect_toast
        }
        
        Toast.makeText(this , messageResId , Toast.LENGTH_SHORT).show()
    }
}


