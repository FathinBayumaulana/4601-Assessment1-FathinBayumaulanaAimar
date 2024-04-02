package org.d3if3028.assessment1.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3028.assessment1.R
import org.d3if3028.assessment1.navigation.Screen
import org.d3if3028.assessment1.ui.theme.Assessment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.List.route) }) {
                        Icon(
                            imageVector = Icons.Outlined.List,
                            contentDescription = stringResource(R.string.list_hewan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        ScreenContent(Modifier.padding(paddingValues))
    }
}

@SuppressLint("StringFormatMatches")
@Composable
fun ScreenContent(modifier: Modifier) {
    val radioOptions = listOf(
        stringResource(id = R.string.amfibi),
        stringResource(id = R.string.aves),
        stringResource(id = R.string.mamalia),
        stringResource(id = R.string.pisces),
        stringResource(id = R.string.reptilia)
    )
    var kategori by rememberSaveable { mutableStateOf(radioOptions[0]) }
    val checkboxOptions = listOf(
        stringResource(id = R.string.herbivora),
        stringResource(id = R.string.karnivora),
        stringResource(id = R.string.omnivora)
    )
    var makanan by rememberSaveable { mutableStateOf(List(checkboxOptions.size) { false }) }
    var pencarian by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.intro1),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.padding(6.dp).border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                KategoriOption(
                    label = radioOptions[0],
                    isSelected = kategori == radioOptions[0],
                    onSelected = { kategori = radioOptions[0] },
                    modifier = Modifier.padding(16.dp)
                )
                KategoriOption(
                    label = radioOptions[1],
                    isSelected = kategori == radioOptions[1],
                    onSelected = { kategori = radioOptions[1] },
                    modifier = Modifier.padding(16.dp)
                )
                KategoriOption(
                    label = radioOptions[2],
                    isSelected = kategori == radioOptions[2],
                    onSelected = { kategori = radioOptions[2] },
                    modifier = Modifier.padding(16.dp)
                )
            }
            Spacer(
                modifier = Modifier.width(16.dp)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                KategoriOption(
                    label = radioOptions[3],
                    isSelected = kategori == radioOptions[3],
                    onSelected = { kategori = radioOptions[3] },
                    modifier = Modifier.padding(16.dp)
                )
                KategoriOption(
                    label = radioOptions[4],
                    isSelected = kategori == radioOptions[4],
                    onSelected = { kategori = radioOptions[4] },
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        Text(
            text = stringResource(id = R.string.intro2),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.padding(6.dp).border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                checkboxOptions.take(3).forEachIndexed { index, label ->
                    MakananOption(
                        label = label,
                        isChecked = makanan[index],
                        onCheckedChange = { makanan = makanan.toMutableList().also { it[index] = !it[index] } },
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
        Button(
            onClick = { pencarian = cariHewan(kategori, makanan, checkboxOptions) },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.cari)
            )
        }
        if (pencarian.isNotBlank()) {
            Divider(
                modifier =Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = pencarian,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick =
                {
                    val message = when {
                        pencarian.startsWith("Tidak ada preferensi makanan yang dipilih") -> context.getString(R.string.bagikan_template1)
                        else -> context.getString(R.string.bagikan_template2, pencarian)
                    }
                    shareData(
                        context = context,
                        message = message
                    )
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.bagikan)
                )
            }
        }
    }
}

@Composable
fun KategoriOption(label: String, isSelected: Boolean, onSelected: () -> Unit, modifier: Modifier) {
    Row(
        modifier = modifier.clickable (onClick = onSelected),
        verticalAlignment = Alignment.CenterVertically
    )
     {
        RadioButton(
            selected = isSelected,
            onClick = null, modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun MakananOption(label: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit, modifier: Modifier) {
    Row(
        modifier = modifier.clickable(onClick = { onCheckedChange(!isChecked) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

private fun cariHewan(kategori: String, makanan: List<Boolean>, checkboxOptions: List<String>): String {
    val hewanMap = mapOf(
        "Amfibi" to mapOf(
            "Herbivora" to listOf("Kodok Pohon, Katak Amerika, Salamander Cecak"),
            "Karnivora" to listOf("Katak Goliath, Salamander berjengger, Axolotl, Katak darat Afrika, Katak pohon Amerika Selatan"),
            "Omnivora" to listOf("Katak darat Amerika, Katak darat Eropa, Katak pohon merah, Salamander warna-warni, Katak merah")
        ),
        "Aves" to mapOf(
            "Herbivora" to listOf("Burung Beo, Burung Puyuh, Burung Merpati, Kalkun, Burung Flamengo"),
            "Karnivora" to listOf("Elang Botak, Falcon, Burung hantu, Rajawali, Alap-alap"),
            "Omnivora" to listOf("Ayam, Merak, Burung pelatuk, Burung Kutilang, Burung Merpati Bandar")
        ),
        "Mamalia" to mapOf(
            "Herbivora" to listOf("Gajah Afrika, Sapi, Kuda, Rusa, Kelinci"),
            "Karnivora" to listOf("Singa, Harimau, Serigala abu-abu, Macan Tutul, Beruang cokelat"),
            "Omnivora" to listOf("Babi, Beruang hitam, Tupai, Rakun, Musang")
        ),
        "Pisces" to mapOf(
            "Herbivora" to listOf("Ikan Teri, Ikan Gurami, Ikan Sargassum, Ikan Parrot, Ikan Koi"),
            "Karnivora" to listOf("Ikan Piranha, Ikan Hiu, Ikan Lele, Ikan Tuna, Ikan Bass"),
            "Omnivora" to listOf("Ikan Mas, Ikan Mas Koki, Ikan Guppy, Ikan Betta, Ikan Rainbow")
        ),
        "Reptilia" to mapOf(
            "Herbivora" to listOf("Kura-kura darat, Iguana hijau, Kura-kura air tawar, Kadal Iguana, Kura-kura Galapagos"),
            "Karnivora" to listOf("Buaya Nil, Ular King Cobra, Kadal Komodo, Ular Piton, Ular Kobra"),
            "Omnivora" to listOf("Kura-kura darat Afrika, Kura-kura sawah, Kadal semut, Kadal air, Ular Mamba hitam")
        )
    )
    val selectedMakan = mutableListOf<String>()
    makanan.forEachIndexed { index, checked ->
        if (checked) {
            selectedMakan.add(checkboxOptions[index])
        }
    }
    if (selectedMakan.isEmpty()) {
        return "Tidak ada jenis makanan yang dipilih"
    }
    val hewanList = hewanMap[kategori]?.let { map ->
        map.filterKeys { selectedMakan.contains(it) }.flatMap { (_, hewan) -> hewan }
    } ?: emptyList()
    val teks = "Hewan $kategori yang ${selectedMakan.joinToString(", ")} ada: "
    return "$teks${hewanList.joinToString(", ")}"
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainPreview() {
    Assessment1Theme {
        MainScreen(rememberNavController())
    }
}