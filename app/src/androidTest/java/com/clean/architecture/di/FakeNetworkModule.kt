package com.clean.architecture.di

import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

/**
 * Created by Rehan Sarwar on 24/07/2022.
 */
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class FakeNetworkModule : NetworkModule() {
    override fun getBaseUrl() = "http://127.0.0.1:8080"
}
