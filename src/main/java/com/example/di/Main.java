package com.example.di;

public class Main {
public static void main(String[]args){
	ClassB classB = new ClassB();

    /* Constructor Injection */
    ClassA classA = new ClassA(classB);

    System.out.println("Ten Percent: " + classA.tenPercent());
  }
}

