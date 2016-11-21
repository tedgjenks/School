package edu.barrons.jenks.ted.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import edu.barrons.jenks.ted.Sentence;

public class SentenceTest {

	@Test
	public void testGetBlankPositions() {
		List<Integer> exp, act;
		Sentence sentence = new Sentence("I love you!");
		exp = new ArrayList<Integer>(Arrays.asList(1, 6));
		act = sentence.getBlankPositions();
		assertEquals(exp, act);
		
		sentence = new Sentence("The cat sat on the mat.");
		exp = new ArrayList<Integer>(Arrays.asList(3, 7, 11, 14, 18));
		act = sentence.getBlankPositions();
		assertEquals(exp, act);
		
		sentence = new Sentence("Why?");
		exp = new ArrayList<Integer>();
		act = sentence.getBlankPositions();
		assertEquals(exp, act);
	}

	@Test
	public void testCountWords() {
		int exp, act;
		Sentence sentence = new Sentence("I love you!");
		exp = 3;
		act = sentence.countWords();
		assertEquals(exp, act);
		
		sentence = new Sentence("The cat sat on the mat.");
		exp = 6;
		act = sentence.countWords();
		assertEquals(exp, act);
		
		sentence = new Sentence("Why?");
		exp = 1;
		act = sentence.countWords();
		assertEquals(exp, act);
	}

	@Test
	public void testGetWords() {
		Sentence sentence;
		String[] exp, act;
		sentence = new Sentence("The bird flew away.");
		exp = new String[]{"The", "bird", "flew", "away."};
		act = sentence.getWords();
		assertArrayEquals(exp, act);
		
		sentence = new Sentence("Wow!");
		exp = new String[]{"Wow!"};
		act = sentence.getWords();
		assertArrayEquals(exp, act);
		
		sentence = new Sentence("Hi! How are you?");
		exp = new String[]{"Hi!", "How", "are", "you?"};
		act = sentence.getWords();
		assertArrayEquals(exp, act);
	}

}
