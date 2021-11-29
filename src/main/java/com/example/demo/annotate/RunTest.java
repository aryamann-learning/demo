package com.example.demo.annotate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.example.demo.annotate.Test;

public class RunTest {
	public static void main(String[] args) throws Exception {

		System.out.println("Testing...");

		int passed = 0, failed = 0, ignore = 0;

		Class<TestExample> obj = TestExample.class;

		// Process @Test
		for (Method method : obj.getDeclaredMethods()) {

			// if method is annotated with @Test
			if (method.isAnnotationPresent(Test.class)) {

				Annotation annotation = (Annotation) method.getAnnotation(Test.class);
				Test test = (Test) annotation;

				// if enabled = true (default)
				if (test.enabled()) {

					try {
						method.invoke(obj.newInstance());
						System.out.println("Test :" + method.getName() + "-" + "passed");
						passed++;
					} catch (Throwable ex) {
						System.out.println("Test :" + method.getName() + "-" + "failed");
						failed++;
					}

				} else {
					System.out.println("Test :" + method.getName() + "-" + "ignored");
					ignore++;
				}    
			}
		}
		System.out.println(
				"Result :" + " " + "Passed :" + passed + " " + "Failed :" + failed + " " + "Ignored :" + ignore);

	}
}
