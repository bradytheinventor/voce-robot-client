//Brady Anderson
//
//Class that converts words (e.g. "one", "five", "twenty") to their numeric equivalent (e.g. 1, 5, 20)

import java.util.Arrays;

public class WordToDigit {
	
	private static final String[] numWords = {"oh", "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
										"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
										"eighteen", "nineteen", "twenty", "thirty", "forty", "fifty", "sixty", "seventy",
										"eighty", "ninety", "hundred", "thousand"};
	
	private static final String[] numDigits = {"0", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
												"14", "15", "16", "17", "18", "19", "20", "30", "40", "50", "60", "70", "80",
												"90", "00", "000"};
	
	/** This function takes a series of words, converts all 'word numbers' to digits, then returns the string*/
	public static String convertAllDigits(String s) {
		//declare variables
		String input;
		String result = "";
		String[] inputArray;
		String[] resultArray;
		
		//get initial string
		input = s;
		
		//break string into array of words
		inputArray = input.split(" ");
		resultArray = inputArray;
		
		//check each word to see if it occurs in numWords; if it does, replace it with the element at the same index in numDigits
		for(int w = 0; w < inputArray.length; w++) {
			if(Arrays.asList(numWords).contains(inputArray[w])) {
				resultArray[w] = numDigits[Arrays.asList(numWords).indexOf(inputArray[w])];
			}
		}
		
		//combine array of words back into a single string (without spaces between words)
		for(String word : resultArray) {
			result += word;
		}
		
		//return value
		return result;
	}
	
	/** This function takes a series of words, sums the 'word numbers', then returns the sum*/
	public static double parseNumber(String s) {
		//declare variables
		double value = 0;
		String input;
		String[] inputArray;
		
		//get initial string
		input = s;
		
		//break string into array of words
		inputArray = input.split(" ");
		
		//check each word to see if it occurs in numWords; if it does, replace it with the element at the same index in numDigits
		//then add up all the values to form the final number
		for(int w = 0; w < inputArray.length; w++) {
			if(Arrays.asList(numWords).contains(inputArray[w])) {
				String n = numDigits[Arrays.asList(numWords).indexOf(inputArray[w])];
				
				if(n == "00") {
					value *= 100;
				} else if(n == "000") {
					value *= 1000;
				} else {
					value += Double.parseDouble(n);
				}
			}
		}
		
		//return value
		return value;
	}
}