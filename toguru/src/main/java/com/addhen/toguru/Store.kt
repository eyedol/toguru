package com.addhen.toguru

interface Store {

  fun add(vararg feature: Feature)

  fun delete(vararg feature: Feature)

  fun delete(featureName: String)

  fun features(): Set<Feature>

  fun feature(featureName: String): Feature

  fun update(feature: Feature)

  fun update(featureName: String, enable: Boolean)
}
