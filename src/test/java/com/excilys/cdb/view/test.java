package com.excilys.cdb.view;

import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class test {

	@Test
	public void tests() {
		assertTrue("5333 Computers found".matches("([0-9]*)+\\s+Computers found"));
		Pattern pattern = Pattern.compile("/([0-9]*)+\\s+([a-zA-Z]*)+\\s([a-zA-Z]*)");
		Matcher matcher = pattern.matcher("5333 Computers found\"");
		while (matcher.find()) {
			System.out.println("Trouvé !");
		}
	}
}
