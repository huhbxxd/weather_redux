package com.lmd.network.router

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.invoke
import io.ktor.http.URLProtocol
import io.ktor.http.path

abstract class BaseRouter(
    val client: HttpClient
) {
    suspend inline fun <reified T> get(
        path: String,
        httpRequestBuilder: HttpRequestBuilder.() -> Unit = {}
    ): T {
        val requestBuilder = getBaseRequestBuilder(path)
        httpRequestBuilder(requestBuilder)

        return client.get {
            takeFrom(requestBuilder)
        }.body()
    }

    fun getBaseRequestBuilder(path: String): HttpRequestBuilder {
        return HttpRequestBuilder.invoke {
            protocol = URLProtocol.HTTPS
            host = baseUrl
            path(path)
        }
    }

    abstract val baseUrl: String
}