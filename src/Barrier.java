//********************************************************************
//
//  Author:        Jeremy Aubrey
//
//  Program #:     6
//
//  File Name:     Barrier.java
//
//  Course:        COSC-4302 Operating Systems
//
//  Due Date:      04/09/2022 (Original, moved)
//
//  Instructor:    Fred Kumi 
//
//  Chapter:       6 & 7
//
//  Description:   An abstract class that requires concrete classes to 
//                 implement methods required for synchronization using 
//                 barrier techniques.
//
//*********************************************************************

public abstract class Barrier {
	
	public abstract void waitForOthers();
	public abstract void freeAll();

}// end Barrier class