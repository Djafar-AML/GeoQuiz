package com.example.geoquiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.geoquiz.R.string
import com.example.geoquiz.model.Question

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
    
    
    private val questionBanks = listOf(
                    Question(string.question_australia , true) ,
                    Question(string.question_oceans , true) ,
                    Question(string.question_mideast , false) ,
                    Question(string.question_africa , false) ,
                    Question(string.question_americas , true) ,
                    Question(string.question_asia , true))
    
    var currentIndex = 0
    
    val currentQuestionAnswer : Boolean
        get() = questionBanks[currentIndex].answer
    
    val currentQuestionText : Int
        get() = questionBanks[currentIndex].textResId
    
    fun moveToAnotherQuestion(back : Boolean = false) {
        
        currentIndex = if (back) {
            
            if (currentIndex <= 0) {
                (questionBanks.size - 1)
            }
            else {
                (currentIndex - 1) % questionBanks.size
            }
        }
        else {
            ((currentIndex + 1) % questionBanks.size)
        }
    }
    
    class QuizViewModelFactory : ViewModelProvider.Factory {
        
        override fun <T : ViewModel?> create(modelClass : Class<T>) : T {
            if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return QuizViewModel() as T
            }
            throw IllegalArgumentException("Cant create ViewModel")
        }
    }
    
}