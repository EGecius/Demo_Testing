package com.example.egidijusgecius.demo_testing;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;

import captors.DummyCallback;
import captors.DummyCaller;
import captors.DummyCollaborator;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doAnswer;

/**
 * Single Responsibility:
 *
 * Demo How to use Mockito's {@link ArgumentCaptor} & PowerMockito's doAnswer()
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
		verify(mockDummyCollaborator).doSomethingAsynchronously(
			dummyCallbackArgumentCaptor.capture());

		// Some assertion about the state before the callback is called
		assertThat(dummyCaller.getResult().isEmpty(), is(true));

		// Once you're satisfied, trigger the reply on callbackCaptor.getValue().
		dummyCallbackArgumentCaptor.getValue().onSuccess(results);

		// Some assertion about the state after the callback is called
		assertThat(dummyCaller.getResult(), is(equalTo(results)));
	}

	@Test
	public void testDoSomethingAsynchronouslyUsingDoAnswer() {
		final List<String> results = Arrays.asList("One", "Two", "Three");

		// Let's do a synchronous answer for the callback
		doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				((DummyCallback)invocation.getArguments()[0]).onSuccess(results);
				return null;
			}
		}).when(mockDummyCollaborator).doSomethingAsynchronously(any(DummyCallback.class));

		// Let's call the method under test
		dummyCaller.doSomethingAsynchronously();

		// Verify state and interaction
		verify(mockDummyCollaborator).doSomethingAsynchronously(any(DummyCallback.class));

		assertThat(dummyCaller.getResult(), is(equalTo(results)));
	}

}
