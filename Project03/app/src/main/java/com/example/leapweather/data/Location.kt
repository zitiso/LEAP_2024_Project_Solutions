package com.example.leapweather.data

data class Location(
    var results: Map<String, List<ZipInfo>>
)

data class ZipInfo(
    val latitude: Double,
    val longitude: Double,
    val city: String,
    val state: String,
    val postal_code: String
) {
    companion object {
        fun empty(): ZipInfo {
            return ZipInfo(
                latitude = 0.0,
                longitude = 0.0,
                city = "",
                state = "",
                postal_code = ""
            )
        }
    }
}
