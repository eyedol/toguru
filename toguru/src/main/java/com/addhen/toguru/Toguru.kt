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

class Toguru(var store: Store, vararg features: Feature = emptyArray()) {

  init {
    store.add(*features)
  }

  fun isEnabled(featureName: String): Boolean {
    return store.feature(featureName).isEnabled
  }

  fun disable(featureName: String) {
    store.update(featureName, false)
  }

  fun enable(featureName: String) {
    store.update(featureName, true)
  }

  fun delete(featureName: String) {
    store.delete(featureName)
  }

  fun deleteAll() = store.deleteAll()

  fun update(feature: Feature) = store.update(feature)

  fun delete(vararg features: Feature) {
    store.delete(*features)
  }

  fun addFeatures(vararg features: Feature) {
    store.add(*features)
  }

  fun features() = store.features()
}
