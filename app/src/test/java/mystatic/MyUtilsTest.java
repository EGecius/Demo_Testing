package mystatic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Tests for {@link MyUtils} using '@RunWith' annotation
 */
@PrepareForTest ({MyUtils.class})
@RunWith (PowerMockRunner.class)
public class MyUtilsTest {

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