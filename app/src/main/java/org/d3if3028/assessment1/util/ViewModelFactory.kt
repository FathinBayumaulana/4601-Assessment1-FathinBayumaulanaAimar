package org.d3if3028.assessment1.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3028.assessment1.database.HewanDao
import org.d3if3028.assessment1.ui.screen.DetailViewModel
import org.d3if3028.assessment1.ui.screen.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val dao: HewanDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}