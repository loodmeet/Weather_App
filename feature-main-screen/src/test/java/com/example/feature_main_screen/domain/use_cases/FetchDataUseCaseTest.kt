package com.example.feature_main_screen.domain.use_cases

import com.example.core.ui.DisplayableItem
import com.example.feature_main_screen.domain.models.UpdateDateDisplayableItem
import com.example.feature_main_screen.domain.repository.MainRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class) class FetchDataUseCaseTest {

    private val repository = mockk<MainRepository>()
    private val locale = Locale.CANADA
    private val hourlyFormatter = DateTimeFormatter.ofPattern("HH:mm", locale)
    private val baseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.CANADA)
    private val useCase = FetchDataUseCase.Base(
        repository = repository,
        formatter = hourlyFormatter,
        locale = Locale.CANADA
    )

    @Test fun `should return the Success`() = runTest {
        val inputDateString = "2022-07-05T11:20"
        val expectedDateString = "11:20"
        val date = LocalDateTime.parse(inputDateString, baseFormatter)
        val testDisplayableItems = mutableListOf(
            TestDisplayableItem1(), TestDisplayableItem2(), TestDisplayableItem3()
        )

        coEvery { repository.fetchData() }.returns(testDisplayableItems)
        coEvery { repository.currentDate() }.returns(date)
        val actual = useCase.execute()

        val expected = Result.success(
            testDisplayableItems.apply {
                add(UpdateDateDisplayableItem(date = expectedDateString))
            }
        )

        assertEquals(expected, actual)
    }

    @Test fun `should return the Failure`() = runTest {
        coEvery { repository.fetchData() }.throws(RuntimeException())
        val expected = true
        val actual = useCase.execute().isFailure

        assertEquals(expected, actual)
    }

    class TestDisplayableItem1 : DisplayableItem
    class TestDisplayableItem2 : DisplayableItem
    class TestDisplayableItem3 : DisplayableItem
}
