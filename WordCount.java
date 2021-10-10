import java.util.Scanner;

import java.io.IOException;
public class WordCount {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter Sentence?");

		String sen = in.nextLine();
		int wordcount = 0;
		for (int i = 0; 
				i < sen.length(); wordcount += (((sen.charAt(i) == ' ') && (sen.charAt(i + 1) != ' ')) ? 1 : 0), i++)
			;
		System.out.println(wordcount + 1);
		in.close();
	}

}