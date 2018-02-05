package com.addhen.toguru

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ToguruTest {

  private lateinit var toguru: Toguru

  @Before
  fun setUp() {
    val store = ToguruStore(SharedPreferencesStore(MockSharedPreferences()))
    toguru = Toguru(store)
  }

  @Before
  fun tearDown() {
    toguru.deleteAll()
  }

  @Test
  fun `should add features`() {
    toguru.addFeatures(Feature("featureOne", "featureDescription"))
    assertEquals(1, toguru.features().size)
  }

  @Test
  fun `should delete a feature`() {
    toguru.delete("featureOne")
    // TODO Make necessary assertions
  }

  @Test
  fun `should disable a feature`() {
    toguru.disable("featureOne")
    // TODO Make necessary assertions
  }

  @Test
  fun `should enable a feature`() {
    toguru.enable("featureOne")
    // TODO Make necessary assertions
  }

  @Test
  fun `should get all features`() {
    val features = toguru.features()
    // TODO Make necessary assertions
  }

  @Test
  fun `should update a feature`() {
    toguru.update(Feature("featureOne", "description"))
    // TODO Make necessary assertions
  }

  @Test
  fun `should delete features`() {
    toguru.delete(Feature("featureOne"), Feature("featureTwo"))
    // TODO Make necessary assertions
  }

  @Test
  fun `should check feature is enabled`() {
    toguru.isEnabled("featureOne")
    // TODO Make necessary assertions
  }

  @Test
  fun `should set a new store instance`() {
    toguru.apply {
      store = ToguruStore(SharedPreferencesStore(MockSharedPreferences()))
    }
    // TODO Make necessary assertions
  }
}
