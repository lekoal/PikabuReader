package com.private_projects.pikabu_reader.di.hotKoinModule

import com.private_projects.pikabu_reader.data.CommonDatabaseHelperImpl
import com.private_projects.pikabu_reader.domain.CommonDatabaseBuilder
import com.private_projects.pikabu_reader.domain.CommonDatabaseHelper
import com.private_projects.pikabu_reader.ui.best.BestFragment
import com.private_projects.pikabu_reader.ui.best.BestViewModel
import com.private_projects.pikabu_reader.ui.fresh.FreshFragment
import com.private_projects.pikabu_reader.ui.fresh.FreshViewModel
import com.private_projects.pikabu_reader.ui.hot.HotFragment
import com.private_projects.pikabu_reader.ui.hot.HotViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val hotKoinModule = module {
    single(named("common_database")) {
        CommonDatabaseBuilder.getInstance(androidContext())
    }

    single<CommonDatabaseHelper>(named("common_database_helper")) {
        CommonDatabaseHelperImpl(get(named("common_database")))
    }

    scope<HotFragment> {
        viewModel(named("hot_view_model")) {
            HotViewModel()
        }
    }

    scope<BestFragment> {
        viewModel(named("best_view_model")) {
            BestViewModel()
        }
    }

    scope<FreshFragment> {
        viewModel(named("fresh_view_model")) {
            FreshViewModel()
        }
    }
}