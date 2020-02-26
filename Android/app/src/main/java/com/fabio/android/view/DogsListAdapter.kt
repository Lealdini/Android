package com.fabio.android.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.fabio.android.R
import com.fabio.android.databinding.ItemDogBinding
import com.fabio.android.model.DogBreed
import kotlinx.android.synthetic.main.item_dog.view.*

class DogsListAdapter(val dogList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogsListAdapter.DogViewHolder>(), DogClickListener {
    class DogViewHolder(var view: ItemDogBinding) : RecyclerView.ViewHolder(view.root)

    //Creating a method that updates teh dog's list
    fun updateDigList(newDogList: List<DogBreed>) {
        dogList.clear()
        dogList.addAll(newDogList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemDogBinding>(inflater, R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.dog = dogList[position]
        holder.view.listener = this
    }

    //return the size of list
    override fun getItemCount() = dogList.size

    override fun onDogClick(v: View) {
        val uuid = v.dogId.text.toString().toInt()
        val action = ListFragmentDirections.actionDetailFragment()
        action.dogUuid = uuid
        Navigation.findNavController(v).navigate(action)
    }
}