/*
 * Copyright 2014-2017 original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.rest.core.support;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.data.rest.core.support.DomainObjectMerger.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;

import org.junit.Test;

/**
 * Unit tests for {@link DomainObjectMerger}.
 * 
 * @author Oliver Gierke
 */
public class DomainObjectMergerUnitTests {

	@Test // DATAREST-327
	public void considersEmptyObjectsEmpty() {

		assertThat(isNullOrEmpty(Optional.empty())).isTrue();
		assertThat(isNullOrEmpty(Optional.of(Collections.emptyList()))).isTrue();
		assertThat(isNullOrEmpty(Optional.of(new Object[0]))).isTrue();
		assertThat(isNullOrEmpty(Optional.of(new String[0]))).isTrue();
		assertThat(isNullOrEmpty(Optional.of(new MyIterable()))).isTrue();

		assertThat(isNullOrEmpty(Optional.of(new Object()))).isFalse();
		assertThat(isNullOrEmpty(Optional.of(Collections.singleton(new Object())))).isFalse();
		assertThat(isNullOrEmpty(Optional.of(new Object[] { "1" }))).isFalse();
		assertThat(isNullOrEmpty(Optional.of(new String[] { "1" }))).isFalse();
	}

	class MyIterable implements Iterable<Object> {

		@Override
		public Iterator<Object> iterator() {
			return Collections.emptyList().iterator();
		}
	}
}
