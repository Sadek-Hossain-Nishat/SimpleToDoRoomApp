package com.example.simpletodoroomapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.simpletodoroomapp.R
import com.example.simpletodoroomapp.databinding.ActivityUserBinding
import com.example.simpletodoroomapp.model.User
import com.example.simpletodoroomapp.viewmodel.UserViewModel

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =DataBindingUtil.setContentView(this,R.layout.activity_user)
        userViewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application))[UserViewModel::class.java]
        binding.lifecycleOwner =this
        binding.userviewmodel = userViewModel
        Log.d("viewmodel", "fabclick "+userViewModel.isfabBtnClicked.value)


        intent?.let {

            val  event = it.getStringExtra("click")
            when(event){
                "fabbtn"->{
                    userViewModel.createUserchanegText()




                }



                "item"->{
                    userViewModel.userItemClick()
                    val user = intent.getSerializableExtra("user")
                    userViewModel.showUserDetails(user as User)
                }

            }
        }

        supportActionBar?.title = userViewModel.actionbarTitle.value





        userViewModel.action.observe(
            this, Observer {

                event->
                if (event){
                    val intent = Intent(this,MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)

                }

            }
        )



      



//        if (userViewModel.iseditablee.value==false){
//
//        }











    }
}