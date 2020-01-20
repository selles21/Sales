package br.selles21.sales.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.selles21.sales.databinding.ItemKeyboardBinding
import java.util.*

class KeyBoardAdapter(
    private val viewModel: PaymentViewModel,
    private val items: ArrayList<KeyBoardItem>
) : RecyclerView.Adapter<KeyBoardAdapter.ViewHolder>() {

    class ViewHolder(val itemBinding: ItemKeyboardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(viewModel: PaymentViewModel, item: KeyBoardItem) {
            itemBinding.item = item
            itemBinding.handler = viewModel
            itemBinding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemKeyboardBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(
                    binding
                )
            }
        }
    }

    //Add items to RecyclerView
    fun addItems(newItems: ArrayList<KeyBoardItem>) {
        items.addAll(newItems)
        notifyItemRangeInserted(items.size - newItems.size, newItems.size)
    }

    //Update items to RecyclerView
    fun submitItems(newItems: ArrayList<KeyBoardItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(viewModel, items[position])
}