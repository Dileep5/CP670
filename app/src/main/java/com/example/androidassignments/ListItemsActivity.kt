package com.example.androidassignments
import android.widget.ImageView
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ListItemsActivity : AppCompatActivity() {

    companion object {
        private const val IMAGE_WIDTH = 200
        private const val IMAGE_HEIGHT = 200
        private const val TAG = "ListItemsActivity"
    }

    private lateinit var radioButton: RadioButton
    private lateinit var checkBox: CheckBox
    private lateinit var switchCompat: SwitchCompat
    private lateinit var imageButton: ImageButton
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>
    private lateinit var photoUri: Uri
    private lateinit var currentPhotoPath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "inside onCreate")
        setContentView(R.layout.activity_list_items)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        radioButton = findViewById(R.id.radioButton)
        checkBox = findViewById(R.id.checkBox)
        switchCompat = findViewById(R.id.switch1)
        imageButton = findViewById(R.id.imageButton)

        radioButton.setOnClickListener {
            print(getString(R.string.radio_clicked))
        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AlertDialog.Builder(this@ListItemsActivity)
                    .setMessage(getString(R.string.dialog_message))
                    .setTitle(getString(R.string.dialog_title))
                    .setPositiveButton(getString(R.string.ok)) { _, _ ->
                        val resultIntent = Intent()
                        resultIntent.putExtra("Response", "Here is my response")
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    }
                    .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                        checkBox.isChecked = false
                        dialog.dismiss()
                    }
                    .show()
            }
        }

        switchCompat.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) getString(R.string.switch_on) else getString(R.string.switch_off)
            print(message)
        }

        takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                Log.d(TAG, "Photo saved to: $currentPhotoPath")
                val bitmap = decodeSampledBitmapFromFile(currentPhotoPath, IMAGE_WIDTH, IMAGE_HEIGHT)
                bitmap?.let {
                    imageButton.setImageBitmap(it)
                    imageButton.scaleType = ImageView.ScaleType.CENTER_CROP
                    print("Image loaded successfully")
                } ?: print("Failed to decode image")
            } else {
                print("Image capture failed")
            }
        }


        imageButton.setOnClickListener {
            val imageFile = createImageFile()
            photoUri = FileProvider.getUriForFile(
                this,
                "$packageName.fileprovider",
                imageFile
            )
            takePictureLauncher.launch(photoUri)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                AlertDialog.Builder(this@ListItemsActivity)
                    .setMessage(getString(R.string.dialog_message))
                    .setTitle(getString(R.string.dialog_title))
                    .setPositiveButton(getString(R.string.ok)) { _, _ ->
                        isEnabled = false
                        onBackPressedDispatcher.onBackPressed()
                    }
                    .setNegativeButton(getString(R.string.cancel), null)
                    .show()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "inside onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "inside onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "inside onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "inside onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "inside onDestroy")
    }

    override fun finish() {
        Log.d(TAG, "onFinish")
        super.finish()
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = cacheDir
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun decodeSampledBitmapFromFile(path: String, reqWidth: Int, reqHeight: Int): Bitmap? {
        val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        BitmapFactory.decodeFile(path, options)

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        options.inJustDecodeBounds = false

        return BitmapFactory.decodeFile(path, options)
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }

    private fun print(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
