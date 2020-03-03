package org.d3ifcool.aplikasihitung


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.whenCreated
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import org.d3ifcool.aplikasihitung.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false)

        binding.btMenuSatu.setOnClickListener {
            v:View ->
            v.findNavController().navigate(R.id.action_mainFragment_to_menuSatuFragment)
        }
        binding.btMenuDua.setOnClickListener {

            v: View ->
            v.findNavController().navigate(R.id.action_mainFragment_to_menuDuaFragment)
        }
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mi_about -> tampilAbout()
        }
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController() ) || super.onOptionsItemSelected(item)
    }

    private fun tampilAbout(){
        view!!.findNavController().navigate(R.id.action_mainFragment_to_aboutFragment)
    }

}
