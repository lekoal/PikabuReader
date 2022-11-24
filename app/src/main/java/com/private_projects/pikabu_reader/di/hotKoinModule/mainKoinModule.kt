package com.private_projects.pikabu_reader.di.hotKoinModule

import com.private_projects.pikabu_reader.data.CommonDatabaseHelperImpl
import com.private_projects.pikabu_reader.data.ElementsReceiverImpl
import com.private_projects.pikabu_reader.domain.CommonDatabaseBuilder
import com.private_projects.pikabu_reader.domain.CommonDatabaseHelper
import com.private_projects.pikabu_reader.domain.ElementsReceiver
import com.private_projects.pikabu_reader.ui.best.BestFragment
import com.private_projects.pikabu_reader.ui.best.BestViewModel
import com.private_projects.pikabu_reader.ui.fresh.FreshFragment
import com.private_projects.pikabu_reader.ui.fresh.FreshViewModel
import com.private_projects.pikabu_reader.ui.hot.HotFragment
import com.private_projects.pikabu_reader.ui.hot.HotViewModel
import com.private_projects.pikabu_reader.utils.ElementToEntityConverter
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainKoinModule = module {
    single(named("common_database")) {
        CommonDatabaseBuilder.getInstance(androidContext())
    }

    single<CommonDatabaseHelper>(named("common_database_helper")) {
        CommonDatabaseHelperImpl(get(named("common_database")))
    }

    single<ElementsReceiver>(named("element_receiver")) {
        ElementsReceiverImpl()
    }

    single(named("element_converter")) {
        ElementToEntityConverter()
    }

    scope<HotFragment> {
        scoped(named("hot_view_model")) {
            HotViewModel(
                get(named("element_receiver")),
                get(named("common_database_helper")),
                get(named("element_converter"))
            )
        }
    }

    scope<BestFragment> {
        scoped(named("best_view_model")) {
            BestViewModel()
        }
    }

    scope<FreshFragment> {
        scoped(named("fresh_view_model")) {
            FreshViewModel()
        }
    }
}