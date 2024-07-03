package com.example.guessinggame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val words = listOf("Android", "Activity", "Fragment")
    private val secretWord = words.random().uppercase()
    private var correctGuesses = ""
    //LiveData properties
    private val _secretWordDisplay = MutableLiveData<String>("")
    val secretWordDisplay: LiveData<String>
        get() = _secretWordDisplay
    private val _incorrectGuesses = MutableLiveData<String>("")
    val incorrectGuesses: LiveData<String>
        get() = _incorrectGuesses
    private val _triesLeft = MutableLiveData<Int>(8)
    val triesLeft: LiveData<Int>
        get() = _triesLeft
    private val _gameOver = MutableLiveData<Boolean>(false)
    val gameOver: LiveData<Boolean>
        get() = _gameOver


    init {
        _secretWordDisplay.value = deriveSecretWordDisplay()
    }

    fun makeGuess(guess: String) {
        if (guess.length == 1) {
            if (secretWord.contains(guess)) {
                correctGuesses += guess
                _secretWordDisplay.value = deriveSecretWordDisplay()
            } else {
                _incorrectGuesses.value += "$guess "
                _triesLeft.value = triesLeft.value?.minus(1)
            }
        }
        if (isWon() || isLost()) _gameOver.value = true
    }

    /**
     * Build a string for how the secret word should be displayed on the screen
     */
    private fun deriveSecretWordDisplay(): String {
        var display = ""
        secretWord.forEach {
            display += checkLetter(it.toString())
        }
        return display
    }

    private fun checkLetter(str: String) = when(correctGuesses.contains(str)) {
        true -> str
        false -> "_"
    }

    fun isWon(): Boolean = secretWord.equals(secretWordDisplay.value, true)

    fun isLost(): Boolean = (triesLeft.value ?: 0) <= 0

    fun wonLostMessage(): String {
        var message = ""
        if (isWon()) message = "You won!"
        else if (isLost()) message = "You lost!"
        message += " The word was $secretWord."
        return message
    }

}