package mystatic;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.agent.PowerMockAgent;
import org.powermock.modules.junit4.rule.PowerMockRule;

import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Tests for {@link MyUtils} using 'PowerMockAgent.initializeIfNeeded()' and PowerMockRule
 */
@PrepareForTest ({MyUtils.class})
public class MyUtilsTest2 {

	static {
		PowerMockAgent.initializeIfNeeded();
	}

	@Rule
	public PowerMockRule powerMockRule = new PowerMockRule();

	MyObject myObject;

	@Before
	public void setup() {
		myObject = new MyObject();
	}

	@Test
	public void verifyStaticMethodHasBeenCalled() {

		mockStatic(MyUtils.class);

		//WHEN
		myObject.myNormalMethod();

		//THEN
		//Verify
		PowerMockito.verifyStatic(); //Similar to how you mock static methods
		//this is how you verify them.
		MyUtils.myStaticMethod();
	}

}