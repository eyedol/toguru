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

import android.content.SharedPreferences
import kotlinx.serialization.json.JSON

class SharedPreferencesStore(private val sharedPreferences: SharedPreferences) {

  fun add(vararg features: Feature) {
    features.forEach {
      sharedPreferences
          .edit {
            putString(it.name, serialize(it))
          }
    }
  }

  fun delete(vararg feature: Feature) {
    feature.forEach {
      delete(it.name)
    }
  }

  fun delete(featureName: String) {
    sharedPreferences
        .edit {
          remove(featureName).apply()
        }
  }

  fun features(): Set<Feature> {
    val features = mutableSetOf<Feature>()
    sharedPreferences.all.forEach {
      val feature = deserialize(it.value.toString())
      features.add(feature)
    }
    return features
  }

  fun clear() {
    sharedPreferences
        .edit {
          clear()
        }
  }

  fun feature(featureName: String): Feature {
    val json = sharedPreferences.getString(featureName, "")
    return deserialize(json)
  }

  fun update(feature: Feature) {
    add(feature)
  }

  fun update(featureName: String, enable: Boolean) {
    val feature = feature(featureName)
    add(feature.copy(isEnabled = enable))
  }

  private fun serialize(feature: Feature): String {
    return JSON.unquoted.stringify(feature)
  }

  private fun deserialize(json: String): Feature {
    return JSON.parse(json)
  }
}
