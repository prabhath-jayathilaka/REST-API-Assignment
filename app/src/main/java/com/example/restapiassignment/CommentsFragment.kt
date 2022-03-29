package com.example.restapiassignment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CommentsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val apiService = APIService.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comments, container, false)
//        initRecyclerView(view)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val comment = apiService.getComments()

        comment.enqueue(object : Callback <List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                val postList = response.body()

                val recyclerview = view.findViewById<RecyclerView>(R.id.rv_comments)
                recyclerview.layoutManager = LinearLayoutManager(activity)

                recyclerview.adapter = CommentAdapter(postList!!,view)
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {

            }

        })


    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CommentsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CommentsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


//    private fun initRecyclerView(view: View) {
//
//
//        val comment = apiService.getComments()
//
//        comment.enqueue(object : Callback <List<Comment>> {
//            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
//                val postList = response.body()
//
//                val recyclerview = view.findViewById<RecyclerView>(R.id.rv_comments)
//                recyclerview.layoutManager = LinearLayoutManager(activity)
//
//                recyclerview.adapter = CommentAdapter(postList!!,view)
////
//            }
//
//            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
//
//            }
//
//        })
//
//    }
}