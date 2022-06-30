package com.example.megabill.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ModuleKey(val value : KClass<out ViewModel>)
