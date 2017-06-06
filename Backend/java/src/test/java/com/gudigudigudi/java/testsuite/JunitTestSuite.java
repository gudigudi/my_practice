package com.gudigudigudi.java.testsuite;

import com.gudigudigudi.java.TestJunit1;
import com.gudigudigudi.java.TestJunit2;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// JUnit Suite Test
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestJunit1.class,
        TestJunit2.class
})
public class JunitTestSuite {

}
