package com.example.guessinggame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    val words = listOf("Android", "Activity", "Fragment")
    val secretWord = words.random().uppercase()
    var correctGuesses = ""
    //LiveData properties
    val secretWordDisplay = MutableLiveData<String>("")
    val incorrectGuesses = MutableLiveData<String>("")
    val triesLeft = MutableLiveData<Int>(8)

    init {
        secretWordDisplay.value = deriveSecretWordDisplay()
    }

    fun makeGuess(guess: String) {
        if (guess.length == 1) {
            if (secretWord.contains(guess)) {
                correctGuesses += guess
                secretWordDisplay.value = deriveSecretWordDisplay()
            } else {
                incorrectGuesses.value += "$guess "
                triesLeft.value = triesLeft.value?.minus(1)
            }
        }
    }

    /**
     * Build a string for how the secret word should be displayed on the screen
     */
    fun deriveSecretWordDisplay(): String {
        var display = ""
        secretWord.forEach {
            display += checkLetter(it.toString())
        }
        return display
    }

    fun checkLetter(str: String) = when(correctGuesses.contains(str)) {
        true -> str
        false -> "_"
    }

    fun isWon(): Boolean = secretWord.equals(secretWordDisplay.value, true)

    fun isLost(): Boolean = triesLeft.value ?: 0 <= 0

    fun wonLostMessage(): String {
        var message = ""
        if (isWon()) message = "You won!"
        else if (isLost()) message = "You lost!"
        message += " The word was $secretWord."
        return message
    }

}