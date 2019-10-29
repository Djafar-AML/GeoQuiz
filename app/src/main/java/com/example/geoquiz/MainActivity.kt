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
            checkAnswer(userAnswer = true)
        }
        
        binding.falseButton.setOnClickListener {
            checkAnswer(userAnswer = false)
        }
        
        updateQuestion(startCall = true)
        
        binding.nextImgButton.setOnClickListener {
            
            updateQuestion()
            
        }
        
        binding.backImgButton.setOnClickListener {
            
            updateQuestion(true)
            
        }
        
        binding.questionTextView.setOnClickListener {
            
            updateQuestion()
        }
    }
    
    private fun updateQuestion(back : Boolean = false , startCall : Boolean = false) {
        
        currentIndex = if (back) {
            if (currentIndex <= 0) {
                (questionBanks.size - 1)
            }
            else {
                (currentIndex - 1) % questionBanks.size
            }
        }
        else {
            (currentIndex + 1) % questionBanks.size
        }
        
        val questionTextResId = if (startCall) {
            questionBanks[0].textResId
        }
        else {
            questionBanks[currentIndex].textResId
        }
        
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


