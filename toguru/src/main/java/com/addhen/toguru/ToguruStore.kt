package com.addhen.toguru

class ToguruStore(private val sharedPreferencesStore: SharedPreferencesStore) : Store {

  override fun add(vararg features: Feature) = sharedPreferencesStore.add(*features)

  override fun delete(vararg features: Feature) = sharedPreferencesStore.delete(*features)

  override fun delete(featureName: String) = sharedPreferencesStore.delete(featureName)

  override fun deleteAll() = sharedPreferencesStore.clear()

  override fun features() = sharedPreferencesStore.features()

  override fun feature(featureName: String) = sharedPreferencesStore.feature(featureName)

  override fun update(feature: Feature) = sharedPreferencesStore.update(feature)

  override fun update(featureName: String, enable: Boolean) = sharedPreferencesStore.update(featureName, enable)
}
