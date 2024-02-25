package com.clean.architecture.mockserver

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.clean.architecture.mockserver.AssetReaderUtil.asset
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

const val COUNTRIES_SUCCESS = "countries.json"

/**
 * Created by Rehan Sarwar on 24/07/2022.
 */
class SuccessDispatcher(
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
) : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        val responseBody = asset(context, COUNTRIES_SUCCESS)
        return MockResponse().setResponseCode(200).setBody(responseBody)
    }
}
