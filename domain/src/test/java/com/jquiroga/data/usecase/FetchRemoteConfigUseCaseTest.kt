package com.jquiroga.data.usecase

import com.jquiroga.data.utils.TestDataProvider
import com.jquiroga.domain.repository.ConfigRepository
import com.jquiroga.domain.usecase.FetchRemoteConfigUseCase
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class FetchRemoteConfigUseCaseTest {

    @Mock
    private lateinit var configRepository: ConfigRepository

    private lateinit var useCase: FetchRemoteConfigUseCase

    @Before
    fun setUp() {
        useCase = FetchRemoteConfigUseCase(configRepository)
    }

    @Test
    fun `given repository success when invoked then completes successfully`() {
        // GIVEN
        whenever(configRepository.fetchAndPersistRemoteConfig()).thenReturn(Completable.complete())

        // WHEN
        val testObserver = useCase().test()

        // THEN
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        verify(configRepository).fetchAndPersistRemoteConfig()
    }

    @Test
    fun `given repository error when invoked then propagates error`() {
        // GIVEN
        val error = Exception(TestDataProvider.API_ERROR_MESSAGE)
        whenever(configRepository.fetchAndPersistRemoteConfig()).thenReturn(Completable.error(error))

        // WHEN
        val testObserver = useCase().test()

        // THEN
        testObserver.assertNotComplete()
        testObserver.assertError(Exception::class.java)
        testObserver.assertErrorMessage(TestDataProvider.API_ERROR_MESSAGE)
        verify(configRepository).fetchAndPersistRemoteConfig()
    }

    @Test
    fun `given repository completable when observed with test observer then is subscribed`() {
        // GIVEN
        whenever(configRepository.fetchAndPersistRemoteConfig()).thenReturn(Completable.complete())

        // WHEN
        val observer = useCase().test()

        // THEN
        observer.assertSubscribed()
        observer.assertComplete()
    }
}
