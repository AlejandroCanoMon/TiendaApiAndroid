package com.api.Peliculas.interfaces


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.api.Peliculas.datos.ProductObjectItem
import com.api.Peliculas.databinding.ProductItemBinding

class ProductAdapter(private val onProductClicked: (ProductObjectItem) -> Unit) :
    ListAdapter<ProductObjectItem, ProductAdapter.ViewHolder>(ProductItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ProductItemBinding = ProductItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Producto = getItem(position)

        holder.binding.tvItemName.text = Producto.name
        holder.binding.tvItemPrice.text = Producto.price.toString()
        holder.binding.tvCat.text = Producto.pack

        holder.binding.root.setOnClickListener {
            onProductClicked(Producto)
        }
    }

    inner class ViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)


}

class ProductItemCallback : DiffUtil.ItemCallback<ProductObjectItem>() {
    override fun areItemsTheSame(oldItem: ProductObjectItem, newItem: ProductObjectItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ProductObjectItem,
        newItem: ProductObjectItem
    ): Boolean = oldItem.id == newItem.id

}

