package com.freeletics.flowredux.dsl

import kotlin.test.Test
import kotlin.test.assertEquals

class ReducerTest {

    private sealed class TestState {
        object A : TestState()
        object B : TestState()
    }

    @Test
    fun `run SelfReducableAction on returning True`() {
        val action =
            SetStateAction<TestState, Any>(
                loggingInfo = "info",
                reduce = { TestState.B },
                runReduceOnlyIf = { true }
            )

        val expected = TestState.B
        val actual = reducer(
            TestState.A,
            action
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `dont run SelfReducableAction on returning False`() {
        val action =
            SetStateAction<TestState, Any>(
                loggingInfo = "info",
                reduce = { TestState.B },
                runReduceOnlyIf = { false }
            )

        val expected = TestState.A
        val actual = reducer(
            TestState.A,
            action
        )

        assertEquals(expected, actual)
    }
}