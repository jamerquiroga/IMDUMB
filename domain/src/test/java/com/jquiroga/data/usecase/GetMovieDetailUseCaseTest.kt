package com.jquiroga.data.usecase

import com.google.common.truth.Truth.assertThat
import com.jquiroga.data.utils.TestDataProvider
import com.jquiroga.domain.repository.MovieRepository
import com.jquiroga.domain.usecase.GetMovieDetailUseCase
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailUseCaseTest {

  @Mock
  private lateinit var movieRepository: MovieRepository

  private lateinit var useCase: GetMovieDetailUseCase

  @Before
  fun setUp() {
    useCase = GetMovieDetailUseCase(movieRepository)
  }

  @Test
  fun `given repository success when invoked with movieId then returns MovieDetail`() {
    // GIVEN
    val detail = TestDataProvider.movieDetail()
    whenever(movieRepository.getMovieDetail(TestDataProvider.MOVIE_ID)).thenReturn(Single.just(detail))

    // WHEN
    val testObserver = useCase(TestDataProvider.MOVIE_ID).test()

    // THEN
    testObserver.assertComplete()
    testObserver.assertNoErrors()
    testObserver.assertValue(detail)
    verify(movieRepository).getMovieDetail(TestDataProvider.MOVIE_ID)
  }

  @Test
  fun `given repository error when invoked then propagates error`() {
    // GIVEN
    val error = Exception(TestDataProvider.API_ERROR_MESSAGE)
    whenever(movieRepository.getMovieDetail(TestDataProvider.MOVIE_ID)).thenReturn(Single.error(error))

    // WHEN
    val testObserver = useCase(TestDataProvider.MOVIE_ID).test()

    // THEN
    testObserver.assertNotComplete()
    testObserver.assertError(Exception::class.java)
    testObserver.assertErrorMessage(TestDataProvider.API_ERROR_MESSAGE)
  }

  @Test
  fun `given different movie ids when invoked then delegates correct id to repository`() {
    // GIVEN
    val movieId = 999
    val detail = TestDataProvider.movieDetail().copy(id = movieId)
    whenever(movieRepository.getMovieDetail(movieId)).thenReturn(Single.just(detail))

    // WHEN
    val result = useCase(movieId).blockingGet()

    // THEN
    assertThat(result.id).isEqualTo(movieId)
    verify(movieRepository).getMovieDetail(movieId)
  }

  @Test
  fun `given repository single when observed with test observer then emits once and completes`() {
    // GIVEN
    whenever(movieRepository.getMovieDetail(TestDataProvider.MOVIE_ID)).thenReturn(
      Single.just(TestDataProvider.movieDetail())
    )

    // WHEN
    val observer = useCase(TestDataProvider.MOVIE_ID).test()

    // THEN
    observer.assertSubscribed()
    observer.assertComplete()
    observer.assertValueCount(1)
  }
}
