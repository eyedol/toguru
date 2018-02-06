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
