package project.base;

import io.cucumber.java.After;
import io.cucumber.java.Before;

// Before and After each scenario
// This one executes first
public class BaseTests {


	@Before
	public void setUp() {
		DriverFactory.initDriver();
	}


	@After
	public void tearDown() {
		 DriverFactory.quitDriver();
	}
}