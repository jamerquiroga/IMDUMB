package com.jquiroga.data.repository

import com.jquiroga.data.datasource.remote.source.MovieRemoteDataRepository
import com.jquiroga.data.mapper.MovieDetailRemoteMapper
import com.jquiroga.data.mapper.MovieRemoteMapper
import com.jquiroga.domain.model.MovieCategory
import com.jquiroga.domain.model.MovieCategoryType
import com.jquiroga.domain.model.MovieDetail
import com.jquiroga.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieDataRepository @Inject constructor(
    private val movieRemoteDataRepository: MovieRemoteDataRepository,
    private val movieRemoteMapper: MovieRemoteMapper,
    private val movieDetailRemoteMapper: MovieDetailRemoteMapper
) : MovieRepository {

    override fun getAllCategories(): Single<List<MovieCategory>> {
        val popular =
            movieRemoteDataRepository.getMoviesByCategory(MovieCategoryType.POPULAR.categoryName)
        val topRated =
            movieRemoteDataRepository.getMoviesByCategory(MovieCategoryType.TOP_RATED.categoryName)
        val upcoming =
            movieRemoteDataRepository.getMoviesByCategory(MovieCategoryType.UPCOMING.categoryName)
        val nowPlaying =
            movieRemoteDataRepository.getMoviesByCategory(MovieCategoryType.NOW_PLAYING.categoryName)

        return Single.zip(
            popular, topRated, upcoming, nowPlaying
        ) { popular, topRated, upcoming, nowPlaying ->
            listOf(
                MovieCategory(
                    displayName = MovieCategoryType.POPULAR.displayName,
                    movies = popular.results.map { movieRemoteMapper.toDomain(it) }
                ),
                MovieCategory(
                    displayName = MovieCategoryType.TOP_RATED.displayName,
                    movies = topRated.results.map { movieRemoteMapper.toDomain(it) }
                ),
                MovieCategory(
                    displayName = MovieCategoryType.UPCOMING.displayName,
                    movies = upcoming.results.map { movieRemoteMapper.toDomain(it) }
                ),
                MovieCategory(
                    displayName = MovieCategoryType.NOW_PLAYING.displayName,
                    movies = nowPlaying.results.map { movieRemoteMapper.toDomain(it) }
                )
            )
        }
    }

    override fun getMovieDetail(movieId: Int): Single<MovieDetail> {
        val detailSingle = movieRemoteDataRepository.getMovieDetail(movieId)
        val creditsSingle = movieRemoteDataRepository.getMovieCredits(movieId)
        val imagesSingle = movieRemoteDataRepository.getMovieImages(movieId)

        return Single.zip(detailSingle, creditsSingle, imagesSingle) { detail, credits, images ->
            movieDetailRemoteMapper.toDomain(detail, credits, images)
        }
    }
}