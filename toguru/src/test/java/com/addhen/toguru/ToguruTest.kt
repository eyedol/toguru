/*
 * Copyright (c) 2015 - 2018 Henry Addo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.addhen.toguru

import org.junit.Assert.*
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
    val feature = toguru.features().elementAt(0)
    assertEquals("featureOne", feature.name)
    assertEquals("featureDescription", feature.description)
    assertTrue(feature.isEnabled)
  }

  @Test
  fun `should delete a feature by its name`() {
    seed()
    toguru.delete("featureOne")
    assertEquals(0, toguru.features().size)
  }

  @Test
  fun `should disable a feature`() {
    seed()
    toguru.disable("featureOne")
    assertFalse(toguru.isEnabled("featureOne"))
  }

  @Test
  fun `should enable a feature`() {
    seed()
    toguru.enable("featureOne")
    val isEnabled = toguru.isEnabled("featureOne")
    assertTrue(isEnabled)
  }

  @Test
  fun `should get all features`() {
    seed()
    toguru.addFeatures(Feature("featureTwo", "descriptionTwo"))
    assertEquals(2, toguru.features().size)
  }

  @Test
  fun `should update a feature description`() {
    val feature = Feature("featureOne", "description")
    toguru.addFeatures(feature)
    toguru.update(feature.copy(description = "new description"))
    val updatedFeature = toguru.features().elementAt(0)
    assertEquals("featureOne", updatedFeature.name)
    assertEquals("new description", updatedFeature.description)
    assertTrue(updatedFeature.isEnabled)
  }

  @Test
  fun `should delete features`() {
    val feature = Feature("featureOne", "description")
    toguru.addFeatures(feature)
    toguru.delete(feature)
    assertEquals(0, toguru.features().size)
  }

  @Test
  fun `should check feature is enabled`() {
    seed()
    val isEnabled = toguru.isEnabled("featureOne")
    assertTrue(isEnabled)
  }

  @Test
  fun `should set a new store instance`() {
    val localStore = ToguruStore(SharedPreferencesStore(MockSharedPreferences()))
    toguru.apply {
      store = localStore
    }
    assertSame(localStore, toguru.store)
  }

  private fun seed() {
    toguru.addFeatures(Feature("featureOne", "description"))
  }
}
