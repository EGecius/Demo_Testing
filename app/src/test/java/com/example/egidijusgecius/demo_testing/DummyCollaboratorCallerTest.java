package com.example.egidijusgecius.demo_testing;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import captors.DummyCallback;
import captors.DummyCaller;
import captors.DummyCollaborator;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Single Responsibility:
 *
 * Demo How to use Mockito's {@link ArgumentCaptor}
 */
public class DummyCollaboratorCallerTest {

	// Class under test
	private DummyCaller dummyCaller;

	@Mock
	private DummyCollaborator mockDummyCollaborator;

	@Captor
	private ArgumentCaptor<DummyCallback> dummyCallbackArgumentCaptor;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		dummyCaller = new DummyCaller(mockDummyCollaborator);
	}

	@Test
	public void testDoSomethingAsynchronouslyUsingArgumentCaptor() {
		// Let's call the method under test
		dummyCaller.doSomethingAsynchronously();

		final List<String> results = Arrays.asList("One", "Two", "Three");

		// Let's call the callback. ArgumentCaptor.capture() works like a matcher.
		verify(mockDummyCollaborator, times(1)).doSomethingAsynchronously(
			dummyCallbackArgumentCaptor.capture());

		// Some assertion about the state before the callback is called
		assertThat(dummyCaller.getResult().isEmpty(), is(true));

		// Once you're satisfied, trigger the reply on callbackCaptor.getValue().
		dummyCallbackArgumentCaptor.getValue().onSuccess(results);

		// Some assertion about the state after the callback is called
		assertThat(dummyCaller.getResult(), is(equalTo(results)));
	}

}
