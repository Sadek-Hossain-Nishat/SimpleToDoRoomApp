package com.example.simpletodoroomapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.simpletodoroomapp.R
import com.example.simpletodoroomapp.databinding.ActivityUserDetailsBinding
import com.example.simpletodoroomapp.model.User
import com.example.simpletodoroomapp.viewmodel.UserViewModel

class UserDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserDetailsBinding
    lateinit var userViewModel: UserViewModel
    lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_details)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_user);// set drawable icon
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        userViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[UserViewModel::class.java]

        binding.lifecycleOwner = this
        binding.userviewmodel = userViewModel

        intent?.let {
            val isitemclicked =it.getBooleanExtra("itemclick",false)
            userViewModel.isuserItemClicked.value = isitemclicked
            user = it.getSerializableExtra("user") as User

            userViewModel.showUserDetails(user)
            userViewModel.userItemClick()
            supportActionBar?.title=userViewModel.actionbarTitle.value


        }


        userViewModel.iseditablee.observe(
            this, Observer {
                event->
                if (event){
                    val  intent = Intent(this,UserActivity::class.java)
                    intent.putExtra("user",user)
                    intent.putExtra("click","item")
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)


                }
            }
        )



    }
}