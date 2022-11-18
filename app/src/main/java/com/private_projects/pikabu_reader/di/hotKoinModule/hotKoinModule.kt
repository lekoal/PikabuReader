package com.private_projects.pikabu_reader.di.hotKoinModule

import com.private_projects.pikabu_reader.data.CommonDatabaseHelperImpl
import com.private_projects.pikabu_reader.domain.CommonDatabaseBuilder
import com.private_projects.pikabu_reader.domain.CommonDatabaseHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val hotKoinModule = module {
    single(named("common_database")) {
        CommonDatabaseBuilder.getInstance(androidContext())
    }

    single<CommonDatabaseHelper>(named("common_database_helper")) {
        CommonDatabaseHelperImpl(get(named("common_database")))
    }
}