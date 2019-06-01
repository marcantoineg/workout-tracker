package com.forkosey.workout_tracker

import io.ktor.client.HttpClient
import io.ktor.client.call.call
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockHttpResponse
import io.ktor.client.engine.mock.responseError
import io.ktor.client.request.get
import io.ktor.config.MapApplicationConfig
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import kotlinx.coroutines.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({
            (environment.config as MapApplicationConfig).apply {
                put("ktor.environment", "dev")
            }
            module(testing = true)
        }) {
            handleRequest(HttpMethod.Get, "/health-check").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("running fine", response.content)
            }
        }
    }

    @Test
    fun testClientMock() {
        runBlocking {
            val client = HttpClient(MockEngine {
                if (url.encodedPath == "/") {
                    MockHttpResponse(
                        call,
                        HttpStatusCode.OK,
                        ByteReadChannel(byteArrayOf(1, 2, 3)),
                        headersOf("X-MyHeader", "MyValue")
                    )
                } else {
                    responseError(HttpStatusCode.NotFound, "Not Found ${url.encodedPath}")
                }
            }) {
                expectSuccess = false
            }
            assertEquals(byteArrayOf(1, 2, 3).toList(), client.get<ByteArray>("/").toList())
            assertEquals("MyValue", client.call("/").response.headers["X-MyHeader"])
            assertEquals("Not Found other/path", client.get<String>("/other/path"))
        }
    }
}
