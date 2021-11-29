package com.example.di;

public class ClassA {

		  ClassB classB;

		  /* Constructor Injection */
		  ClassA(ClassB injected) {
		    classB = injected;
		  }

		  int tenPercent() {
		   int a=ClassB.calculate()/10;
		return a;
		  }
		}

