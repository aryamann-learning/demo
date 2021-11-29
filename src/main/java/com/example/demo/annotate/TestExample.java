package com.example.demo.annotate;

import com.example.demo.annotate.Test;

public class TestExample {

	@Test
	void testA() {
		if (true)
			throw new RuntimeException("This test always failed");
	}

	@Test(enabled = false)
	void testB() {
		if (false)
			throw new RuntimeException("This test always passed");
	}

	@Test(enabled = true)
	void testC() {
		if (true) {
			// do nothing, this test always passed.
		}
	}
}