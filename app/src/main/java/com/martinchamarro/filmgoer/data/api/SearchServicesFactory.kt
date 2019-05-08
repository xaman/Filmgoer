/*
 * Copyright 2017 Martin Chamarro (@martinchamarro)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.martinchamarro.filmgoer.data.api

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.martinchamarro.filmgoer.BuildConfig
import com.martinchamarro.filmgoer.Config
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val SEARCH_LOGGER_TAG = "SearchServices"

class SearchServicesFactory {

    private companion object {
        const val PARAM_API_KEY = "apikey"
    }

    private val commonParamsInterceptor = { chain: Interceptor.Chain ->
        var request = chain.request()
        val urlBuilder = request.url().newBuilder()
        addCommonParams(urlBuilder)
        request = request.newBuilder().url(urlBuilder.build()).build()
        chain.proceed(request)
    }

    fun create(): SearchServices {
        val retrofit = getHttpAdapter()
        return retrofit.create(SearchServices::class.java)
    }

    private fun getHttpAdapter(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Config.OMDB_BASE_DOMAIN)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(getGsonConverter())
                .client(createClient())
                .build()
    }

    /*
     * This is the place to register type adapters
     */
    private fun getGsonConverter(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        return GsonConverterFactory.create(gson)
    }

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(createLoggingInterceptor())
                .addInterceptor(commonParamsInterceptor)
                .build()
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message -> Log.d(SEARCH_LOGGER_TAG, message) }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun addCommonParams(builder: HttpUrl.Builder) {
        builder.addQueryParameter(PARAM_API_KEY, BuildConfig.OMDB_API_KEY)
    }
}
