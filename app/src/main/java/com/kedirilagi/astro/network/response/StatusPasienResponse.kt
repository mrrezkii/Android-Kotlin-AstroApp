package com.kedirilagi.astro.network.response

data class StatusPasienResponse(
    var key: String? = null,
    var kondisi: String? = null,
    var tanggal: String? = null,
    var jam: String? = null
)