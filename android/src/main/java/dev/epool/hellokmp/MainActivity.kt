package dev.epool.hellokmp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dev.epool.hellokmp.databinding.ActivityMainBinding
import dev.epool.hellokmp.extensions.observe
import dev.epool.hellokmp.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.viewState.observe(this) { viewState ->
            binding.viewState = viewState
            if (viewState.hasError) {
                Toast.makeText(this, viewState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.getUser("epool")
    }

}