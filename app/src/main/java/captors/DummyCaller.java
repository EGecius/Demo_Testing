package captors;

import java.util.ArrayList;
import java.util.List;

/**
 * Single Responsibility:
 *
 * Dummy class calling an external asynchronous operation and receiving results via callback.
 */
public class DummyCaller implements DummyCallback {

	private final DummyCollaborator dummyCollaborator;

	private List<String> result = new ArrayList<String>();

	public DummyCaller(DummyCollaborator dummyCollaborator) {
		this.dummyCollaborator = dummyCollaborator;
	}

	public void doSomethingAsynchronously() {
		dummyCollaborator.doSomethingAsynchronously(this);
	}

	public List<String> getResult() {
		return this.result;
	}

	@Override
	public void onSuccess(List<String> result) {
		this.result = result;
		System.out.println("On success");
	}

	@Override
	public void onFail(int code) {
		System.out.println("On Fail");
	}
}
