package com.koby.cocktailapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.koby.cocktailapp.R
import com.koby.cocktailapp.models.Cocktail
import com.koby.cocktailapp.ui.adapters.CocktailAdapter.CocktailViewHolder
import kotlinx.android.synthetic.main.cocktail_item.view.*

class CocktailAdapter
    constructor(private val interaction: Interaction): ListAdapter<Cocktail, CocktailViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        return CocktailViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cocktail_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        when (holder) {
            is CocktailViewHolder ->
                holder.bind(getItem(position),interaction)
        }
    }

    class CocktailViewHolder
    constructor(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        val cocktailName = itemView.cocktail_item_name
        val cocktailStar = itemView.cocktail_item_star
        val cocktailImage = itemView.cocktail_item_image

        fun bind(cocktail: Cocktail,interaction: Interaction) {
            with(itemView){
                this.setOnClickListener{
                    interaction.onItemClick(cocktail)
                }

                cocktailStar.setOnClickListener{
                    interaction.onItemSave(cocktail)
                }
            }

            cocktailName.setText(cocktail.name)

            //Set star
            if (cocktail.isFavorite) {
                cocktailStar.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_star_gold_32dp))
            } else {
                cocktailStar.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_star_hollow_black_32dp))
            }

            //Set image
            Glide.with(itemView.context)
                .load(cocktail.imageUri)
                .centerCrop()
                .into(cocktailImage)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Cocktail>() {

        override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
            return oldItem == newItem
        }
    }

    interface Interaction {
        fun onItemClick(cocktail: Cocktail)
        fun onItemSave(cocktail: Cocktail)
    }
}