package com.example.parcial1

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.parcial1.Fragments.ListFragment
import com.example.parcial1.Fragments.MatchF
import com.example.parcial1.Fragments.NewMatchF

class MainActivity : AppCompatActivity(),
        FragmentComunication,
        ListFragment.OnFragmentInteractionListener,
        NewMatchF.OnFragmentInteractionListener,
        MatchF.OnFragmentInteractionListener {

    private lateinit var viewModel: MatchViewModel

    override fun sendData(des: Int) {
        viewModel.getAllMatches.observe(this, Observer {
            if(!it[des].MatchEnd){

                var partido = MatchF()
                var datos = Bundle()
                datos.putInt("des", des)
                partido.arguments = datos
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, partido)
                        .addToBackStack("prev")
                        .commit()


            }else{

            }
            viewModel.getAllMatches.removeObservers(this)
        })

    }

    override fun viewMatches() {
        var lista = ListFragment()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, lista)
                .commit()
    }

    override fun addMatch() {
        var crear = NewMatchF()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, crear)
                .addToBackStack("prev")
                .commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)

        if (savedInstanceState == null) {
            var lista = ListFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, lista).commit()
        }

    }
}
