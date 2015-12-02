package mystatic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * All uni tests
 */
@RunWith(Suite.class)
@Suite.SuiteClasses ({
	MyStaticTest.class,
	MyUtilsTest.class,
	MyUtilsTest2.class
})
public class AllUnitTests {
}
