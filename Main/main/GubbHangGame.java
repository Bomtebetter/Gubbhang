package main;

import java.util.Random;
import java.util.Scanner;

public class GubbHangGame {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean start = true;

		do {
			intro();
			String Diff = input.nextLine();
			if (Diff.matches("^[0-9]+\\w{0,1}$")) {
				if (Diff.contains("1")) {
					System.out.println("Vi genererar ett ord som du ska gissa :)");
					System.out.println(wordSelect());
					playAgain();
				} else if (Diff.contains("2")) {
					System.out.println("Spelare 1 f�r skriva in ett ord som spelare 2 f�r gissa :)");
					String hiddenWord = input.next();
					System.out.println(hiddenWord);
					playAgain();
				} else {
					System.out.println("fel >:(");
					System.exit(0);
				}
			}  else {
				System.out.println("fel >:(");
			}
		} while (start = true);

	}

	public static void intro() {
		System.out.println("V�lkommen till h�ngagubbe");
		System.out.println("Skriv 1 f�r f�r enspelarsl�ge ");
		System.out.println("Skriv 2 f�r att tv�spelarsl�ge");
	}

	public static String wordSelect() {
		String[] _ord = { "Snigel", "Zebra", "Byxa", "Stol", "Flygplan", "Vispgr�dde", "Ukulele" };
		Random ranNum = new Random();
		int random = ranNum.nextInt(8);
		String hiddenWord = _ord[random];
		return hiddenWord;
	}

	private static boolean playAgain(boolean start) {
		Scanner input = new Scanner(System.in);
		String pAgain = input.nextLine();
		if (pAgain.equalsIgnoreCase("1")) {
			start = true;
		} else {
			start = false;
			System.out.println("Shutting down...");
			System.exit(0);
		}
		return start;
	}

}
