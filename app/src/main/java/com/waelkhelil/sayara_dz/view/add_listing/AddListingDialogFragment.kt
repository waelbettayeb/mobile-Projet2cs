package com.waelkhelil.sayara_dz.view.add_listing

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.waelkhelil.sayara_dz.R
import com.waelkhelil.sayara_dz.database.model.*
import kotlinx.android.synthetic.main.fragment_add_listing.*
import java.io.InputStream
import java.lang.Float.parseFloat
import java.lang.Integer.parseInt


class AddListingDialogFragment : DialogFragment() {

    companion object {
        const val TAG: String = "AddListingDialogFragment"
        fun newInstance() = AddListingDialogFragment()
        const val RESULT_LOAD_IMG = 0
    }

    private lateinit var viewModel: AddListingViewModel
    private var imageList:ArrayList<Bitmap> = ArrayList()
    var selectedOptions:ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(AddListingViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var brandsAdapter:ArrayAdapter<Brand>
        var brandsList:List<Brand> = emptyList()
        var modelsAdapter:ArrayAdapter<Model>
        var modelsList:List<Model> = emptyList()
        var versionsAdapter:ArrayAdapter<Version>
        var versionsList:List<Version> = emptyList()
        var optionsAdapter:ArrayAdapter<Option>
        var colorsList:List<PaintColor> = emptyList()
        var optionsList:List<Option> = emptyList()

        var colorsAdapter:ArrayAdapter<PaintColor>
        //Bring the system brands
        options_spinner.prompt="selectionner les options.."
        brands_spinner.prompt="selectionner la marque.."
        versions_spinner.prompt="selectionner la version.."
        models_spinner.prompt="selectionner le modéle.."
        colors_spinner.prompt="selectionner la couleur.."
        viewModel.getSystemBrands().observe(
            this, Observer {
                brandsList=it
                brandsAdapter = ArrayAdapter(this.context!!,android.R.layout.simple_spinner_item, it)
                brands_spinner.adapter=brandsAdapter
            }
        )
        brands_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                chipGroup2.removeAllViews()
                selectedOptions.clear()
                viewModel.getBrandModels(brandsList.get(position).id).observe(
                    parentFragment!!.activity!!, Observer {
                        modelsList=it
                        modelsAdapter = ArrayAdapter(  parentFragment!!.activity!!,android.R.layout.simple_spinner_item, it)
                        models_spinner.adapter=modelsAdapter
                    })

            }

        }

        models_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                chipGroup2.removeAllViews()
                selectedOptions.clear()
                viewModel.getModelVersions(modelsList.get(position).id).observe(
                    parentFragment!!.activity!!, Observer {
                        versionsList=it
                        versionsAdapter = ArrayAdapter(parentFragment!!.activity!!,android.R.layout.simple_spinner_item, it)
                        versions_spinner.adapter=versionsAdapter
                    })

             //   Log.i("info",  resources.getIntArray(R.array.colors).get(0).toString())

             viewModel.getModelColors(modelsList.get(position).id).observe(
                    parentFragment!!.activity!!, Observer {
                    colorsList=it

                     colorsAdapter = ArrayAdapter(parentFragment!!.activity!!,android.R.layout.simple_spinner_item, it)
                     colors_spinner.adapter=colorsAdapter


        })}}
        versions_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                chipGroup2.removeAllViews()
                selectedOptions.clear()
                viewModel.getVersionOptions(versionsList.get(position).id).observe(
                    parentFragment!!.activity!!, Observer {
                        optionsList=it
                        optionsAdapter = ArrayAdapter(parentFragment!!.activity!!,android.R.layout.simple_spinner_item, it)
                        options_spinner.adapter=optionsAdapter
                    })

            }

        }
        options_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


                       addChipToGroup(optionsList.get(position).name,chipGroup2,optionsList.get(position).id)
                       selectedOptions.add(optionsList.get(position).id)
            }

        }




        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.title = getString(R.string.new_listing)

        dialog?.setOnKeyListener { _, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_UP && i == KeyEvent.KEYCODE_BACK) {
                context?.let { exitConfirmation(it) }
            }
            true
        }
        toolbar.setNavigationOnClickListener {
            context?.let { exitConfirmation(it) }
        }

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_save -> {
                    if ( et_description.text!!.isEmpty()||et_price.text!!.isEmpty()||imageList.isEmpty())
                    {   if (imageList.isEmpty())
                    {
                        Toast.makeText(this.context,"Veuillez ajouter au moins une image de votre véhicule", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this.context,"Veuillez renseigner tous les champs", Toast.LENGTH_SHORT).show()
                    }}
                    else{
                        /*var i:Int=0
                        var options:ArrayList<String> = ArrayList()
                        while (i<chipGroup2.childCount)
                        {
                            options.add(optionsList.get(i).id)
                        }*/
                        //TODO : change email when authentif fixed
                        viewModel.sendAd("fm_bourouais@esi.dz",et_description.text.toString(),
                            versionsList[versions_spinner.selectedItemPosition].id,
                            colorsList[colors_spinner.selectedItemPosition].code,
                            parseFloat(et_price.text.toString()), selectedOptions).observe(
                            this,Observer<String>{
                                if (it.isNotBlank()){
                                Toast.makeText(this.context,it,Toast.LENGTH_SHORT).show()
                                    viewModel.freeBitmaps()
                                    dismissAllowingStateLoss()
                                }
                            }
                        )

                    }

                    true

        }
                else -> true
            }}









        val lButtonAddImage = view.findViewById<Button>(R.id.button_add_image)

        lButtonAddImage.setOnClickListener { pickPhotos() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = GridLayoutManager(context, 3)
        val recyclerView = view?.findViewById(R.id.rv_image) as RecyclerView
        recyclerView.layoutManager = layoutManager
        viewModel.mBitmaps.observe(viewLifecycleOwner, Observer<List<Bitmap>> {
            it?.also {
                recyclerView.adapter = ImageAdapter(it)
            }
        })
        recyclerView.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RESULT_LOAD_IMG) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    val clipData = data.clipData
                    if (clipData != null) { // handle multiple photo
                        for (i in 0 until clipData.itemCount) {
                            val uri = clipData.getItemAt(i).uri
                            DownloadImageFromUri().execute(uri)
                        }
                    } else { // handle single photo
                        val uri = data.data
                        DownloadImageFromUri().execute(uri)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)
    }



    private fun exitConfirmation(context: Context) {
        MaterialAlertDialogBuilder(context)
            .setTitle(getString(R.string.exit_add_listing))
            .setMessage(getString(R.string.exit_add_listing_confimation_msg))
            .setPositiveButton(getString(R.string.exit)) { _, _ ->
                viewModel.freeBitmaps()
                dismissAllowingStateLoss()
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }

    private fun addChipToGroup(txt: String, chipGroup: ChipGroup,id:String) {
        val chip = Chip(context)
        chip.text = txt
        chip.id=parseInt(id)
        chip.isClickable = false
        chip.isCloseIconVisible = true
        chip.isCheckable = false
        chipGroup.addView(chip as View)
        chip.setOnCloseIconClickListener { chipGroup.removeView(chip as View)
            selectedOptions.remove(chip.id.toString())
        }
        printChipsValue(chipGroup)
    }

    private fun printChipsValue(chipGroup: ChipGroup) {
        for (i in 0 until chipGroup.childCount) {
            val chipObj = chipGroup.getChildAt(i) as Chip
            Log.d("Chips text :: ", chipObj.text.toString())

        }
    }

   /* private fun setColor(context: Context, @ColorInt color: Int) {
        if (color != ColorSheet.NO_COLOR) {
            button_color.setBackgroundColor(color)

            button_color.text = ColorSheetUtils.colorToHex(color)
        } else {
            val primaryColor = ContextCompat.getColor(context, R.color.colorPrimary)
            button_color.setBackgroundColor(primaryColor)
            button_color.text = getString(R.string.no_color)
        }
    }*/

    private fun pickPhotos() {
        val photoPickerIntent = Intent(Intent.ACTION_GET_CONTENT)
        photoPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG)
    }

    private inner class DownloadImageFromUri : AsyncTask<Uri, Void, Bitmap>() {

        override fun doInBackground(vararg uris: Uri): Bitmap? {
            val imageUri = uris[0]
            var bimage: Bitmap? = null
            try {
                val contentResolver = context?.contentResolver
                val imageStream: InputStream? = contentResolver?.openInputStream(imageUri)

                val options = BitmapFactory.Options()
                options.inPreferredConfig = Bitmap.Config.RGB_565

                bimage = BitmapFactory.decodeStream(imageStream, null, options)

            } catch (e: Exception) {
                Log.e("Error Message", e.message)
                e.printStackTrace()
            }

            return bimage
        }

        override fun onPostExecute(result: Bitmap) {
            viewModel.addBitmaps(result)
            imageList.add(result)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                if ( et_description.text!!.isEmpty()||et_price.text!!.isEmpty()||imageList.isEmpty())
                {   if (imageList.isEmpty())
                {
                    Toast.makeText(this.context,"Veuillez ajouter au moins une image de votre véhicule", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this.context,"Veuillez renseigner tous les champs", Toast.LENGTH_SHORT).show()
                }}
                else{


                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
