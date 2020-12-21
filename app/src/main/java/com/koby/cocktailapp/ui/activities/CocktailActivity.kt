package com.koby.cocktailapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.koby.cocktailapp.R
import com.koby.cocktailapp.models.Cocktail
import kotlinx.android.synthetic.main.activity_cocktail.*

class CocktailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail)

        val cocktail = intent.getParcelableExtra<Cocktail>(getString(R.string.item))

        Glide.with(this)
            .load(cocktail.imageUri)
            .centerCrop()
            .into(cocktail_image)
        cocktail_name.setText(cocktail.name)
        cocktail_instruction.setText(cocktail.instruction)
        cocktail_glass.setText("Glass: " + cocktail.glass)
        cocktail_tags.setText("Tags: " + cocktail.tags)

    }
}