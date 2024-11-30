package com.lmd.cities

import com.lmd.cities.data.cities.Cities
import com.lmd.network.BaseRouter
import io.ktor.client.HttpClient
import io.ktor.http.parameters

class CitiesRouter(
    client: HttpClient
): BaseRouter(client) {

    override val baseUrl: String
        get() = BASE_URL

    suspend fun searchCities(query: String, page: Int): Cities {
        return get(PATH) {
            parameters {
                append(QUERY_PARAMS, query)
                append(DATASET_PARAMS, DATASET)
                append(START_PARAMS, page.toString())
                append(ROWS_PARAMS, ROWS)
            }
        }
    }

    companion object {
        private const val BASE_URL = "public.opendatasoft.com"
        private const val PATH = "/api/records/1.0/search/"

        private const val QUERY_PARAMS = "q"
        private const val START_PARAMS = "start"

        private const val DATASET_PARAMS = "dataset"
        private const val DATASET = "geonames-all-cities-with-a-population-1000"

        private const val ROWS_PARAMS = "rows"
        private const val ROWS = "30"
    }
}