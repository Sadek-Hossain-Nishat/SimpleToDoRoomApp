package com.example.simpletodoroomapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodoroomapp.R
import com.example.simpletodoroomapp.databinding.UserItemBinding
import com.example.simpletodoroomapp.model.User

class UserListAdapter(private val edittitem:(User)->Unit,private val deleteitem:(User)->Unit,private val itemclicklister:(User)->Unit):RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    class UserViewHolder(val listItemBinding: UserItemBinding) :RecyclerView.ViewHolder(listItemBinding.root){


        fun bind(user: User,itemclick:(User)->Unit,deleteitem: (User) -> Unit,edittitem: (User) -> Unit){

            listItemBinding.idItemName.text =user.name
            listItemBinding.idItemEmail.text = user.email
            listItemBinding.idDeleteItem.setOnClickListener{

                deleteitem(user)




            }
            listItemBinding.idEditItem.setOnClickListener {
                edittitem(user)
            }
            listItemBinding.idUserItem.setOnClickListener {
                itemclick(user)
            }


        }



    }


    private val users  = ArrayList<User>()


    fun setUserList(users:List<User>){
        this.users.clear()
        this.users.addAll(users)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<UserItemBinding>(layoutInflater, R.layout.user_item,parent,false)
        return UserViewHolder(binding)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       holder.bind(users[position],itemclicklister,deleteitem,edittitem)
    }

    override fun getItemCount()=users.size


}