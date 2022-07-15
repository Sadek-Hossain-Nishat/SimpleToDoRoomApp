package com.example.simpletodoroomapp.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpletodoroomapp.R
import com.example.simpletodoroomapp.databinding.ActivityMainBinding
import com.example.simpletodoroomapp.model.User
import com.example.simpletodoroomapp.viewmodel.UserViewModel

class MainActivity : AppCompatActivity(),CustomListener {
   private lateinit var binding: ActivityMainBinding
   private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =DataBindingUtil.setContentView(this,R.layout.activity_main)
       supportActionBar?.title ="Home"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home);// set drawable icon
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        userViewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application))[UserViewModel::class.java]

        binding.lifecycleOwner = this

        displayList()






        binding.idFloatinBtn.setOnClickListener {

            val intent = Intent(this,UserActivity::class.java)
            intent.putExtra("click","fabbtn")


            startActivity(intent)

        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.alldelete_menu,menu)
        return true
    }

    private fun displayList() {
        binding.idRecyclerviewUserlist.layoutManager = LinearLayoutManager(this)
        val adapter =UserListAdapter(edittitem = {edititemClick(it)},deleteitem = {deleteitemClick(it)}){
            itemClick(it)
        }
        binding.idRecyclerviewUserlist.adapter = adapter


        userViewModel.users.observe(this, Observer {
            adapter.setUserList(it)
            adapter.notifyDataSetChanged()

            Log.d("viewmodel", "displayList: "+it)



        })






    }

    override fun itemClick(user: User) {
        userViewModel.isuserItemClicked.value= true




        val intent = Intent(this,UserDetailsActivity::class.java)
        intent.putExtra("user",user)
        intent.putExtra("itemclick",userViewModel.isuserItemClicked.value)
        startActivity(intent)
    }

    override fun deleteitemClick(user: User) {
        AlertDialog.Builder(this)
            .setTitle("Delete")
           .setMessage("Are you want to delete this item?")
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                userViewModel.delete(user)
//                displayList()


            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            .create()
            .show()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        AlertDialog.Builder(this)
            .setTitle("Delete")
            .setMessage("Are you want to delete all items?")
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                userViewModel.clearAll()
//                displayList()


            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            .create()
            .show()





        return true
    }


    override fun edititemClick(user: User) {


        val  intent = Intent(this,UserActivity::class.java)
        intent.putExtra("user",user)
        intent.putExtra("click","item")
        startActivity(intent)

    }
}


