package com.example.restapiassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class PostAdapter (private var posts: List<Post>, private val communicator: View) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val itemId: TextView = itemView.findViewById(R.id.tv_id)
        val itemTitle: TextView = itemView.findViewById(R.id.tv_title)


        init {


            itemView.setOnClickListener(this)
        }
//        init {
//            itemView.setOnClickListener { view: View ->
//                val activity = view.context as AppCompatActivity
//                activity.supportFragmentManager.beginTransaction().apply {
//                    replace(R.id.flFragment, MapsFragment())
//                    addToBackStack(null)
//                    commit()
//
//                }
//
//            }
//        }

        override fun onClick(p0: View?) {
            val currentItem = posts[adapterPosition]
//            val bundle = bundleOf("post" to currentItem)
//            communicator.findNavController().navigate(R.id.postDetailsFragment2, bundle)
            val action =MainFragmentDirections.actionMainFragment2ToPostDetailsFragment2(currentItem)
            communicator.findNavController().navigate(action)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.post_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemId.text = posts[position].id.toString()
        holder.itemTitle.text = posts[position].title
//        Log.i("frg","posts")
    }

    override fun getItemCount(): Int {
        return posts.size
    }

}
