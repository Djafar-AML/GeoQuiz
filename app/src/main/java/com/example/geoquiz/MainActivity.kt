package com.example.geoquiz

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.geoquiz.QuizViewModel.QuizViewModelFactory
import com.example.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {
    
    private val binding by lazy {
        DataBindingUtil.setContentView(this , R.layout.activity_main) as ActivityMainBinding
    }
    
    
    private val quizViewModel by lazy {
        ViewModelProvider(this , QuizViewModelFactory()).get(QuizViewModel::class.java)
    }
    
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        
        //get viewModel
        Log.d(TAG , "Got a QuizViewModel: $quizViewModel")
        
        // getting saved state of viewModel!
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX , 0) ?: 0
        quizViewModel.currentIndex = currentIndex
        
        // buttons
        binding.trueButton.setOnClickListener {
            checkAnswer(userAnswer = true)
        }
        
        binding.falseButton.setOnClickListener {
            checkAnswer(userAnswer = false)
        }

//        quizViewModel.moveToAnotherQuestion()
        updateQuestion()
        
        binding.nextImgButton.setOnClickListener {
            
            quizViewModel.moveToAnotherQuestion()
            updateQuestion()
            
        }
        
        binding.backImgButton.setOnClickListener {
            
            quizViewModel.moveToAnotherQuestion(true)
            updateQuestion()
            
        }
        
        binding.questionTextView.setOnClickListener {
            
            quizViewModel.moveToAnotherQuestion()
            updateQuestion()
        }
    }
    
    
    private fun updateQuestion() {
        
        val questionTextResId = quizViewModel.currentQuestionText
        Log.d("question" , questionTextResId.toString())
        
        binding.questionTextView.setText(questionTextResId)
        
    }
    
    private fun checkAnswer(userAnswer : Boolean) {
        
        val correctAnswer = quizViewModel.currentQuestionAnswer
        
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        }
        else {
            R.string.incorrect_toast
        }
        
        Toast.makeText(this , messageResId , Toast.LENGTH_SHORT).show()
    }
    
    override fun onSaveInstanceState(outState : Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG , "onSaveInstanceState")
        outState.putInt(KEY_INDEX , quizViewModel.currentIndex)
        
    }
}


