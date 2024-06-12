package org.d3if3028.assessment1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3028.assessment1.database.HewanDao
import org.d3if3028.assessment1.model.Hewan

class MainViewModel(dao: HewanDao): ViewModel() {
    val listHewan: StateFlow<List<Hewan>> = dao.getHewanDao().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
//    var listHewan: List<Hewan> by mutableStateOf(emptyList())
//    init {
//        fetchDataHewan()
//    }
//    private fun fetchDataHewan() {
//        val listHewan = listOf(
//            Hewan(
//                nama = "Kodok Pohon",
//                gambar = R.drawable.kodokpohon,
//                kingdom = "Amfibi",
//                makan = "Herbivora",
//            ),
//            Hewan(
//                nama = "Katak Goliath",
//                gambar = R.drawable.katakgoliath,
//                kingdom = "Amfibi",
//                makan = "Karnivora"
//            ),
//            Hewan(
//                nama = "Katak darat Amerika",
//                gambar = R.drawable.katakdaratamerika,
//                kingdom = "Amfibi",
//                makan = "Omnivora"
//            ),
//            Hewan(
//                nama = "Burung Beo",
//                gambar = R.drawable.burungbeo,
//                kingdom = "Aves",
//                makan = "Herbivora"
//            ),
//            Hewan(
//                nama = "Elang Botak",
//                gambar = R.drawable.elangbotak,
//                kingdom = "Aves",
//                makan = "Karnivora"
//            ),
//            Hewan(
//                nama = "Ayam",
//                gambar = R.drawable.ayam,
//                kingdom = "Aves",
//                makan = "Omnivora"
//            ),
//            Hewan(
//                nama = "Gajah Afrika",
//                gambar = R.drawable.gajahafrika,
//                kingdom = "Mamalia",
//                makan = "Herbivora"
//            ),
//            Hewan(
//                nama = "Singa",
//                gambar = R.drawable.singa,
//                kingdom = "Mamalia",
//                makan = "Karnivora"
//            ),
//            Hewan(
//                nama = "Babi",
//                gambar = R.drawable.babi,
//                kingdom = "Mamalia",
//                makan = "Omnivora"
//            ),
//            Hewan(
//                nama = "Ikan Teri",
//                gambar = R.drawable.ikanteri,
//                kingdom = "Pisces",
//                makan = "Herbivora"
//            ),
//            Hewan(
//                nama = "Ikan Piranha",
//                gambar = R.drawable.ikanpiranha,
//                kingdom = "Pisces",
//                makan = "Karnivora"
//            ),
//            Hewan(
//                nama = "Ikan Mas",
//                gambar = R.drawable.ikanmas,
//                kingdom = "Pisces",
//                makan = "Omnivora"
//            ),
//            Hewan(
//                nama = "Kura-kura darat",
//                gambar = R.drawable.kuradarat,
//                kingdom = "Reptilia",
//                makan = "Herbivora"
//            ),
//            Hewan(
//                nama = "Buaya Nil",
//                gambar = R.drawable.buayanil,
//                kingdom = "Reptilia",
//                makan = "Karnivora"
//            ),
//            Hewan(
//                nama = "Kura-kura darat Afrika",
//                gambar = R.drawable.kuradaratafrika,
//                kingdom = "Reptilia",
//                makan = "Omnivora"
//            )
//        )
//        listHewan.forEachIndexed { index, item ->
//            addItemToList(item.copy(id = index.toLong()))
//        }
//    }
//
//    private fun addItemToList(hewan: Hewan) {
//        listHewan += hewan
//    }
}