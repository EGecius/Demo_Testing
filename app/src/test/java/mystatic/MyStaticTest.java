package mystatic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;


/**
 * Single Responsibility:
 *
 * Tests for {@link MyStatic}
 */
@RunWith (PowerMockRunner.class)
@PrepareForTest(MyStatic.class)
public class MyStaticTest {

	@Before
	public void setup() {
		PowerMockito.mockStatic(MyStatic.class);
	}

	@Test
	public void multiplyThrees() {
		//WHEN
		PowerMockito.when(MyStatic.multiply(3, 3)).thenReturn(10);
		//THEN
		int result = MyStatic.multiply(3, 3);
		assertEquals(10, result);
	}

	@Test
	public void multiplyUnMocked() {
		//THEN
		int result = MyStatic.multiply(4, 4);
		assertEquals(16, result);
	}

	@Test
	public void multiplyFours() {
		//WHEN
		PowerMockito.when(MyStatic.multiply(4, 4)).thenReturn(2);
		//THEN
		int result = MyStatic.multiply(4, 4);
		assertEquals(2, result);
	}

}