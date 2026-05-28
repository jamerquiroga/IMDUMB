package com.jquiroga.data.usecase

import com.jquiroga.data.utils.TestDataProvider
import com.jquiroga.domain.repository.MovieRepository
import com.jquiroga.domain.usecase.GetCategoriesUseCase
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetCategoriesUseCaseTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var useCase: GetCategoriesUseCase

    @Before
    fun setUp() {
        useCase = GetCategoriesUseCase(movieRepository)
    }

    @Test
    fun `given repository success when invoked then returns movie categories`() {
        // GIVEN
        val categories = TestDataProvider.movieCategories()
        whenever(movieRepository.getAllCategories()).thenReturn(Single.just(categories))

        // WHEN
        val testObserver = useCase().test()

        // THEN
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValue(categories)
        verify(movieRepository).getAllCategories()
    }

    @Test
    fun `given repository error when invoked then propagates error`() {
        // GIVEN
        val error = Exception(TestDataProvider.API_ERROR_MESSAGE)
        whenever(movieRepository.getAllCategories()).thenReturn(Single.error(error))

        // WHEN
        val testObserver = useCase().test()

        // THEN
        testObserver.assertNotComplete()
        testObserver.assertError(Exception::class.java)
        testObserver.assertErrorMessage(TestDataProvider.API_ERROR_MESSAGE)
        verify(movieRepository).getAllCategories()
    }
}
