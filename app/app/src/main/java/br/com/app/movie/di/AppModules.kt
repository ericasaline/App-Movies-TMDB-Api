package br.com.app.movie.di

import br.com.app.movie.data.remote.service.MovieService
import br.com.app.movie.data.repository.MovieRepository
import br.com.app.movie.data.repository.MovieRepositoryImpl
import br.com.app.movie.ui.viewmodel.MovieViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"

val dataModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    single { get<Retrofit>().create(MovieService::class.java) }

    single { Dispatchers.IO }

    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }

}

val uiModule = module {

    viewModel { MovieViewModel(get()) }

}