package org.d3if3028.assessment1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3028.assessment1.R
import org.d3if3028.assessment1.database.HewanDao
import org.d3if3028.assessment1.model.Hewan

class DetailViewModel(private val dao: HewanDao) : ViewModel() {
    fun insertHewan(nama: String, kingdom: String, makan: String) {
        val hewan = Hewan(
            nama = nama,
            kingdom = kingdom,
            makan = makan
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(hewan)
        }
    }
    suspend fun getHewan(id: Long): Hewan? {
        return dao.getHewanById(id)
    }
    fun updateHewan(id: Long, nama: String, kingdom: String, makan: String) {
        val hewan = Hewan(
            id = id,
            nama = nama,
            kingdom = kingdom,
            makan = makan
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(hewan)
        }
    }
    fun deleteHewan(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}