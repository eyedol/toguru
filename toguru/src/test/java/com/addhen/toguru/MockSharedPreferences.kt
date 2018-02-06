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
import java.util.concurrent.ConcurrentHashMap

internal class MockSharedPreferences : SharedPreferences, SharedPreferences.Editor {

  private var values: Map<String, Any> = ConcurrentHashMap()

  private val tempValues = ConcurrentHashMap<String, Any>()

  override fun edit(): SharedPreferences.Editor {
    return this
  }

  override fun contains(key: String): Boolean {
    return values.containsKey(key)
  }

  override fun getAll(): Map<String, *> {
    return ConcurrentHashMap(values)
  }

  override fun getBoolean(key: String, defValue: Boolean): Boolean {
    return if (values.containsKey(key)) {
      (values[key] as Boolean)
    } else defValue
  }

  override fun getFloat(key: String, defValue: Float): Float {
    return if (values.containsKey(key)) {
      (values[key] as Float).toFloat()
    } else defValue
  }

  override fun getInt(key: String, defValue: Int): Int {
    return if (values.containsKey(key)) {
      (values[key] as Int).toInt()
    } else defValue
  }

  override fun getLong(key: String, defValue: Long): Long {
    return if (values.containsKey(key)) {
      (values[key] as Long).toLong()
    } else defValue
  }

  override fun getString(key: String, defValue: String?): String? {
    return if (values.containsKey(key)) {
      values[key] as String
    } else defValue
  }

  override fun getStringSet(key: String, defValues: Set<String>?): Set<String>? {
    return if (values.containsKey(key)) {
      values[key] as Set<String>
    } else defValues
  }

  override fun registerOnSharedPreferenceChangeListener(
      listener: SharedPreferences.OnSharedPreferenceChangeListener) {
    throw UnsupportedOperationException()
  }

  override fun unregisterOnSharedPreferenceChangeListener(
      listener: SharedPreferences.OnSharedPreferenceChangeListener) {
    throw UnsupportedOperationException()
  }

  override fun putBoolean(key: String, value: Boolean): SharedPreferences.Editor {
    tempValues[key] = java.lang.Boolean.valueOf(value)
    return this
  }

  override fun putFloat(key: String, value: Float): SharedPreferences.Editor {
    tempValues[key] = value
    return this
  }

  override fun putInt(key: String, value: Int): SharedPreferences.Editor {
    tempValues[key] = value
    return this
  }

  override fun putLong(key: String, value: Long): SharedPreferences.Editor {
    tempValues[key] = value
    return this
  }

  override fun putString(key: String, value: String?): SharedPreferences.Editor {
    tempValues[key] = value.toString()
    return this
  }

  override fun putStringSet(key: String, values: Set<String>?): SharedPreferences.Editor {
    tempValues[key] = values as Set<String>
    return this
  }

  override fun remove(key: String): SharedPreferences.Editor {
    tempValues.remove(key)
    return this
  }

  override fun clear(): SharedPreferences.Editor {
    tempValues.clear()
    return this
  }

  override fun commit(): Boolean {
    values = tempValues
    return true
  }

  override fun apply() {
    commit()
  }
}
