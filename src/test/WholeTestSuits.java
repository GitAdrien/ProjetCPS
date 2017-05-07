package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value=Suite.class)
@SuiteClasses(value={
		EngineTest.class,
		CharacterTest.class,
		HitboxTest.class,
		PlayerTest.class,
		HitboxTestError.class,
})
public class WholeTestSuits {

}
