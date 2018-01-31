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

  fun update(feature: Feature) {
    store.update(feature)
  }

  fun delete(vararg features: Feature) {
    store.delete(*features)
  }

  fun addFeatures(vararg features: Feature) {
    store.add(*features)
  }

  fun features() = store.features()
}
