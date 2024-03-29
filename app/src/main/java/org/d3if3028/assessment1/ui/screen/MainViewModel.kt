package org.d3if3028.assessment1.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3028.assessment1.model.Hewan

class MainViewModel: ViewModel() {
    val data = getDataDummy()

    private fun getDataDummy(): List<Hewan> {
        val data = mutableListOf<Hewan>()
        for (i in 1 until 10) {

        }
        return data
    }
}