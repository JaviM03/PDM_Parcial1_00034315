package com.example.parcial1.Fragments

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial1.FragmentComunication
import com.example.parcial1.MatchViewModel
import com.example.parcial1.R
import com.example.parcial1.adapters.MatchAdapter
import com.example.parcial1.database.entities.Match
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var matchesList: List<Match>

    private lateinit var comunicacion: FragmentComunication
    private lateinit var activity: Activity

    private lateinit var viewAdapter: MatchAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var viewModel: MatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)

        matchesList = listOf(Match(0,"0", "A", "B", 0, 0,false, Calendar.getInstance().time))
        viewModel.getAllMatches.observe(this, androidx.lifecycle.Observer {
            matchesList = it
            viewAdapter.setData(matchesList)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Activity){
            activity = context
            if (activity is FragmentComunication){
                comunicacion = activity as FragmentComunication
            }
        }

        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onStart() {
        super.onStart()

        btn_addMatch.setOnClickListener {
            comunicacion.addMatch()
        }

        val click = View.OnClickListener { v ->
            comunicacion.sendData(rv_matches.getChildAdapterPosition(v))
        }

        viewAdapter = MatchAdapter(matchesList, click)
        viewManager = LinearLayoutManager(context)

        initRecycler()

    }

    private fun initRecycler() {
        with(rv_matches){
            adapter = viewAdapter
            layoutManager = viewManager
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
