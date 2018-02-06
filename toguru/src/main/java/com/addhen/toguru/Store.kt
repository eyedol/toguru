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

interface Store {

  fun add(vararg features: Feature)

  fun delete(vararg features: Feature)

  fun delete(featureName: String)

  fun deleteAll()

  fun features(): Set<Feature>

  fun feature(featureName: String): Feature

  fun update(feature: Feature)

  fun update(featureName: String, enable: Boolean)
}
