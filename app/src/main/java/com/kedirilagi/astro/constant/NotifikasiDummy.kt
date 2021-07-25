package com.kedirilagi.astro.constant

import com.kedirilagi.astro.data.model.NotifikasiModel

fun notifikasiDummy(): ArrayList<NotifikasiModel> {
    val listAdapter: ArrayList<NotifikasiModel> = ArrayList()
    listAdapter.add(
        NotifikasiModel(
            1,
            "1",
            "Versi 1.2.1 sudah hadir di Play Store dan App Store, segera lakukan update dan dapatkan fitur terbaru dari Astrp"
        )
    )
    listAdapter.add(
        NotifikasiModel(
            2,
            "3",
            "Tetap jaga kesehatan dengan #dirumahaja dan patuh protokol kesehatan"
        )
    )
    listAdapter.add(
        NotifikasiModel(
            3,
            "4",
            "Artikel mingguan Astro telah di update, ketuk untuk informasi lebih lanjut"
        )
    )

    return listAdapter
}