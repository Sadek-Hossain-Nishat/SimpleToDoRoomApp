package com.example.simpletodoroomapp.viewmodel

import android.R.attr.data
import android.app.Application
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.simpletodoroomapp.model.User
import com.example.simpletodoroomapp.model.UserDatabase
import com.example.simpletodoroomapp.model.UserRepository
import kotlinx.coroutines.launch


class UserViewModel(application: Application) : AndroidViewModel(application), Observable {

    private val userdao = UserDatabase.getInstance(application.applicationContext).userDao()
    private val repository = UserRepository(userdao)
    val users = repository.users

    lateinit var user: User





    val actionbarTitle = MutableLiveData<String>()
    val action = MutableLiveData<Boolean>()
    val isfabBtnClicked = MutableLiveData<Boolean>()
    val isuserItemClicked = MutableLiveData<Boolean>()


    @Bindable
    val titleText = MutableLiveData<String>()

    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val inputComment = MutableLiveData<String?>()






    val iseditablee = MutableLiveData<Boolean>(true)


    @Bindable
    val editorsaveorupdateButtonText = MutableLiveData<String>()


    fun showUserDetails(user: User) {
        this.user = user

        if (isuserItemClicked.value==true){

            inputName.value ="Name: "+ user.name.toString()
            inputEmail.value ="Email: "+ user.email.toString()
            inputComment.value ="Comment: "+ user.comment.toString()

        }else{

            editorsaveorupdateButtonText.value ="Update"

            titleText.value = "Edit User"
            actionbarTitle.value ="Update User"

            inputName.value = user.name.toString()
            inputEmail.value = user.email.toString()
            inputComment.value = user.comment.toString()

        }



    }




    fun doEditable(){
        iseditablee.value = true


    }


    fun editorsaveorupdateUser() {

        if (editorsaveorupdateButtonText.value=="Update"){

            try {

                this.user.name = inputName.value!!
                this.user.email = inputEmail.value!!
                this.user.comment = inputComment.value!!

                update(this.user)
               action.value= true




            }catch (e:Exception){

            }

        }
        else {
            try {

                val name: String = inputName.value!!
                val email: String = inputEmail.value!!
                val comment: String = inputComment.value!!

                insert(User(0, name, email, comment))

                inputName.value = null
                inputEmail.value = null
                inputComment.value = null

            }catch (e:Exception){

            }



        }


    }


    fun clearAll() =viewModelScope.launch {
        repository.deleteAll()

    }


    fun userItemClick() {


        titleText.value = "Details"
        editorsaveorupdateButtonText.value = "Edit"
        iseditablee.value = false
        actionbarTitle.value = "User Information"

    }


    fun createUserchanegText() {


        titleText.value = "New User"
        editorsaveorupdateButtonText.value = "Create"
        iseditablee.value = true
        actionbarTitle.value = "Register"





        Log.d(
            "viewmodel",
            "changed \n" + titleText.value + "\n" + editorsaveorupdateButtonText.value
        )
    }


    fun insert(user: User) = viewModelScope.launch {

        try {
            repository.insert(user)
            action.value = true
            Log.d("viewmodel", "action " + action.value)

        } catch (e: Exception) {
            Log.d("viewmodel", "insert error: " + e)

        }







    }


    fun update(user: User) = viewModelScope.launch {
        repository.update(user)

    }


    fun delete(user: User) = viewModelScope.launch {
        repository.delete(user)

    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}