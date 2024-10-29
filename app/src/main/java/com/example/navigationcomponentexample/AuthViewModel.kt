package com.example.navigationcomponentexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> = _currentUser

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    // Örnek kullanıcı veritabanı (gerçek uygulamada bu veriler veritabanında olmalı)
    private val userDatabase = mutableListOf(
        User("uzayilica2@gmail.com", "uzayilica2@gmail.com", "4267462"),
        User("uzayilica1@gmail.com", "uzayilica1@gmail.com", "admin123")
    )

    init {
        _isLoggedIn.value = false
        _currentUser.value = null
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                when {
                    email.isEmpty() || password.isEmpty() -> {
                        _errorMessage.value = "Email ve şifre boş olamaz"
                        return@launch
                    }
                    !isValidEmail(email) -> {
                        _errorMessage.value = "Geçerli bir email adresi girin"
                        return@launch
                    }
                    password.length < 6 -> {
                        _errorMessage.value = "Şifre en az 6 karakter olmalı"
                        return@launch
                    }
                }

                // Kullanıcı kontrolü
                val user = userDatabase.find { it.email == email && it.password == password }
                if (user != null) {
                    _isLoggedIn.value = true
                    _currentUser.value = user
                } else {
                    _errorMessage.value = "Email veya şifre hatalı"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun signup(email: String, password: String, name: String) {
        viewModelScope.launch {
            try {
                when {
                    email.isEmpty() || password.isEmpty() || name.isEmpty() -> {
                        _errorMessage.value = "Tüm alanları doldurun"
                        return@launch
                    }
                    !isValidEmail(email) -> {
                        _errorMessage.value = "Geçerli bir email adresi girin"
                        return@launch
                    }
                    password.length < 6 -> {
                        _errorMessage.value = "Şifre en az 6 karakter olmalı"
                        return@launch
                    }
                    userDatabase.any { it.email == email } -> {
                        _errorMessage.value = "Bu email adresi zaten kayıtlı"
                        return@launch
                    }
                }

                // Yeni kullanıcı oluştur
                val newUser = User(email, name, password)
                userDatabase.add(newUser)
                _isLoggedIn.value = true
                _currentUser.value = newUser
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun logout() {
        _isLoggedIn.value = false
        _currentUser.value = null
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}

// Güvenlik için password da eklenmiş User data class'ı
data class User(
    val email: String,
    val name: String,
    val password: String  // Gerçek uygulamada şifre hash'lenerek saklanmalı!
)