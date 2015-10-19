package captors;

import java.util.List;

public interface DummyCallback {
	void onSuccess(List<String> result);
	void onFail(int code);
}
