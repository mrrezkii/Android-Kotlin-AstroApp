package com.kedirilagi.astro.data.constant

import com.kedirilagi.astro.data.model.ArtikelModel

fun artikelDummy(): ArrayList<ArtikelModel> {
    val listAdapter: ArrayList<ArtikelModel> = ArrayList()
    listAdapter.add(
        ArtikelModel(
            1,
            "Cari Tahu Apa Itu Penyakit Stroke",
            "https://d1bpj0tv6vfxyp.cloudfront.net/articles/713965_12-1-2021_12-5-36.webp"
        )
    )
    listAdapter.add(
        ArtikelModel(
            2,
            "Mengidap Stroke Apa yang Perlu dilakukan?",
            "https://d1vbn70lmn1nqe.cloudfront.net/prod/wp-content/uploads/2021/06/15062450/mengidap-stroke-apa-yang-perlu-dilakukan-halodoc.jpg.webp"
        )
    )
    listAdapter.add(
        ArtikelModel(
            3,
            "Ketahui Penanganan Stroke Berdasarkan Jenisnya",
            "https://d1bpj0tv6vfxyp.cloudfront.net/articles/880906_12-5-2021_4-36-36.webp"
        )
    )

    return listAdapter
}