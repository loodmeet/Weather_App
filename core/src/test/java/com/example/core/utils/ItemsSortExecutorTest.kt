package com.example.core.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import com.example.core.utils.ItemsSortExecutorTest.ForTest.*

@OptIn(ExperimentalCoroutinesApi::class) class ItemsSortExecutorTest {

    private val executor = ItemsSortExecutor.Base(coroutineContext = Dispatchers.IO)
    private val first = First()
    private val second = Second()
    private val third = Third()
    private val items = mutableListOf(first, second, third)

    @Test fun `test to sort sort in direct order`() = runTest {
        val rule = arrayOf(First::class, Second::class, Third::class)
        val expected = Result.success(listOf(first, second, third))
        val actual = executor.sortByRule(items = items, rule = rule)
        assertEquals(expected, actual)
    }

    @Test fun `test to sort sort in reverse order`() = runTest {
        val rule = arrayOf(Third::class, Second::class, First::class)
        val expected = Result.success(listOf(third, second, first))
        val actual = executor.sortByRule(items = items, rule = rule)
        assertEquals(expected, actual)
    }

    @Test fun `should return Success`() = runTest {
        val rule = arrayOf(Third::class, First::class, Second::class, Four::class)
        val expected = Result.success(listOf(third, first, second))
        val actual = executor.sortByRule(items = items, rule = rule)
        assertEquals(expected, actual)
    }

    @Test fun `should return Failure`() = runTest {
        val rule = arrayOf(Third::class, First::class, Four::class, Second::class)
        val expected = true
        val actual = executor.sortByRule(items = items, rule = rule).isFailure
        assertEquals(expected, actual)
    }

    @Test fun `should throw ArrayIndexOutOfBoundsException`() = runTest {
        val rule = arrayOf(Third::class, Second::class)
        val expected = true
        val actual = try {
            executor.sortByRule(items = items, rule = rule)
            false
        } catch (e: ArrayIndexOutOfBoundsException) { true }
        assertEquals(expected, actual)
    }

    open class ForTest {
        class First : ForTest()
        class Second : ForTest()
        class Third : ForTest()
        class Four : ForTest()
    }


}