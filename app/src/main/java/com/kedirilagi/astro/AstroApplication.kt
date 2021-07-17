package com.kedirilagi.astro

import android.app.Application
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.kedirilagi.astro.data.viewmodel.factory.*
import com.kedirilagi.astro.network.AstroRepository
import com.kedirilagi.astro.storage.persistence.AstroDatabase
import com.kedirilagi.astro.storage.preferences.AstroPreferences
import com.mapbox.mapboxsdk.Mapbox
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber

class AstroApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@AstroApplication))

        bind() from singleton { AstroPreferences(instance()) }
        bind() from singleton { AstroDatabase(instance()) }
        bind() from singleton { AstroRepository(instance(), instance()) }

        bind() from provider { MainViewModelFactory(instance()) }
        bind() from provider { BerandaViewModelFactory(instance()) }
        bind() from provider { JadwalViewModelFactory(instance()) }
        bind() from provider { PetunjukViewModelFactory(instance()) }
        bind() from provider { ProfilViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Firebase.database.setPersistenceEnabled(true)
        Mapbox.getInstance(this, BuildConfig.MAP_BOX_SECRET_KEY)
    }
}