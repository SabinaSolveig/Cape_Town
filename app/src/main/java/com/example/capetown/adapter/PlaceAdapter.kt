package com.example.capetown.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capetown.databinding.ItemPlaceBinding
import com.example.capetown.model.Place
import com.example.capetown.utils.AnimationUtils

class PlaceAdapter(
    private val places: List<Place>,
    private val onItemClick: (Place) -> Unit
) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ItemPlaceBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = places[position]
        holder.bind(place)

        holder.itemView.setOnClickListener {
            AnimationUtils.animateClick(it) {
                onItemClick(place)
            }
        }

        AnimationUtils.animateRecyclerViewItem(holder.itemView, position)
    }

    override fun getItemCount(): Int = places.size

    class PlaceViewHolder(private val binding: ItemPlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(place: Place) {
            binding.apply {
                placeIcon.setImageResource(place.imageRes)
                placeTitle.text = place.title
                placeDescription.text = place.description
            }
        }
    }
}