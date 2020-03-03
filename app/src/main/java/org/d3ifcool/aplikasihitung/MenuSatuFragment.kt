package org.d3ifcool.aplikasihitung


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_menu_satu.*
import org.d3ifcool.aplikasihitung.databinding.FragmentMenuSatuBinding

/**
 * A simple [Fragment] subclass.
 */
class MenuSatuFragment : Fragment() {

    lateinit var binding:FragmentMenuSatuBinding
    // KEY untuk SAVEINTANCE
    val key_harga = "hargaKey"
    val key_kue = "kueKey"
    val key_nama = "namaKey"
    val key_jumlah = "jumlahKey"
    val key_topping = "toppingKey"
    val key_rasa = "rasaKey"
    //Varibel yang di inisialisiasi di atas agar bisa dipanggil global (banyak method)
    var harga :Int= 0
    var hasilKue :String =""
    var nama : String = ""
    var jumlahString : String = "0"
    var jumlah = 0
    var topping = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // pemanggilan binding
        binding = DataBindingUtil.inflate<FragmentMenuSatuBinding>(inflater,R.layout.fragment_menu_satu,container,false)


        //pemanggilan method reset() agar Visibility Gone saat pertama kali di jalankan
        reset()

        //saveintance
        if(savedInstanceState != null){
            harga = savedInstanceState.getInt(key_harga,0)
            nama = savedInstanceState.getString(key_nama,"")
            hasilKue = savedInstanceState.getString(key_kue,"")
            jumlah = savedInstanceState.getInt(key_jumlah,0)
            topping = savedInstanceState.getInt(key_topping,0)
            muncul()
        }


        //Kodingan untuk fungsi button tambah dan kurang pada jumlah pemesanan
        binding.tvTambah.setOnClickListener {
            jumlahString=binding.tvKuantitas.text.toString()
            jumlah = jumlahString.toInt()+1
            binding.tvKuantitas.setText(jumlah.toString())
        }
        binding.tbKurang.setOnClickListener {
            if (tv_kuantitas.text.toString().toInt()>0){
                jumlah = binding.tvKuantitas.text.toString().toInt()-1
                binding.tvKuantitas.setText(jumlah.toString())} else{
                Toast.makeText(this.context,"Tidak Bisa kurang dari 0",Toast.LENGTH_LONG).show()
            }
        }
        // fungsi apabila button BELI di tekan
        harga = 0
        binding.btBeli.setOnClickListener {
            /*
            * Harga 1 kue : Rp. 5000
            * topping
            *   -ice Cream = Rp. 2000
            *   -Ceres = Rp.1000
            * */
            //untuk meletakan harga topping Checkbox

            topping  = 0
            if (binding.cbIceCream.isChecked){
                topping += 2000
            }
            if (binding.cbCeres.isChecked){
                topping+=1000
            }

            // jumlah harga dengan rumus jumlah*5000+topping yg dipilih
            harga = binding.tvKuantitas.text.toString().toInt()*5000+topping

            //menampilkan Nama di tampilan Struk nya
            nama = binding.inNama.text.toString()
            binding.tvNama.setText(nama)

            //if untuk pilihan di radio button
            if (binding.rbCoklat.isChecked){
                hasilKue = "Kue Coklat"
                binding.tvKue.setText(hasilKue)
            }else if (binding.rbVanila.isChecked){
                hasilKue="Kue Kue "
                binding.tvKue.setText(hasilKue)
            }

            //Gambar Save Intance


            //memunculkan Struk Pembelian yang telah di GONE kan
            muncul()
        }

        //Button Reset
        binding.btReset.setOnClickListener {
            reset()
            binding.tvKuantitas.setText("0")
            binding.inNama.setText("")
            binding.cbCeres.isChecked = false
            binding.cbIceCream.isChecked = false
            binding.rbCoklat.isChecked= false
            binding.rbVanila.isChecked= false

        }

        //Button Share
        binding.btShare.setOnClickListener {


            val mIntent = Intent(Intent.ACTION_SEND)

            mIntent.data = Uri.parse("mailto:")
            mIntent.type = "text/plain"

            mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("st.sheillyapradina@gmail.com"))
            mIntent.putExtra(Intent.EXTRA_SUBJECT, "Jawaban")
            mIntent.putExtra(Intent.EXTRA_TEXT, "")
            try {
                startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
            }
            catch (e: Exception){

            }
        }

        //lajutan untuk saveintance agar ditampilkan saat rotasi
        harga = jumlah * 7000 + topping
        binding.tvNama.setText(nama)
        binding.tvKuantitas.setText(jumlah.toString())
        binding.tvKue.setText(hasilKue)



        return binding.root
    }

    //untuk GONE Struk pembelian
    fun reset(){

        binding.tvKue.visibility = View.GONE
        binding.tvStruk.visibility = View.GONE
        binding.tvNama.visibility = View.GONE
        binding.btShare.visibility = View.GONE
    }

    //Memunculkan Struk Pembelian
    fun muncul(){
        binding.tvKue.visibility = View.VISIBLE
        binding.tvStruk.visibility = View.VISIBLE
        binding.tvNama.visibility = View.VISIBLE
        binding.btShare.visibility = View.VISIBLE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(key_jumlah,jumlah)
        outState.putString(key_nama,nama)
        outState.putString(key_kue,hasilKue)
        super.onSaveInstanceState(outState)
    }


}
