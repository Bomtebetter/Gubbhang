package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Testing {

	private static Scanner _input = new Scanner(System.in);
	private static ArrayList<String> _wordlist = new ArrayList();
	private static ArrayList<String> _wrongGuesses = new ArrayList();
	private static String _progressWord = "";

	public static void main(String[] args) {
		printWelcomeMessage();
		play();
		printGoodbyeMessage();
	}
	/**
	 * En start meny och en replay funktion 
	 */
	private static void play() {
		while (true) {
			System.out.println("Select difficulty:");
			System.out.println("\t1. Easy");
			System.out.println("\t2. Medium");
			System.out.println("\t3. Hard");
			int difficulty = getValidIntegerInput();
			if (difficulty >= 1 && difficulty <= 3) {
				startGame(difficulty);

				System.out.println("Do you want to play again) y/Y for yes, anything else means no");
				String playAgain = _input.nextLine();
				if (!(playAgain.toLowerCase().equals("y"))) {
					break;
				}
			}
		}
	}

	private static void printGoodbyeMessage() {
		System.out.println("Thats right flee");
	}

	private static void printWelcomeMessage() {
		System.out.println("WhaleCum");
	}
	/**
	 * Tar och gör så man måste skriva in rätt inputtyp som är ett nummer.
	 * @return
	 */
	private static int getValidIntegerInput() {
		while (true) {
			if (!_input.hasNextInt()) {
				_input.nextLine();
				System.out.println("Input a number");
				continue;
			}

			int returnValue = _input.nextInt();
			_input.nextLine();
			return returnValue;
		}
	}

	/**
	 * Tar in easy-, normal- eller hardgame beroende på vilken difficulty som man skriver i konsollen
	 * @param difficulty
	 */
	private static void startGame(int difficulty) {
		switch (difficulty) {
		case 1:
			easyGame();
			break;
		case 2:
			normalGame();
			break;
		case 3:
			hardGame();
			break;
		}
	}

	private static void easyGame() {
		populateEasyWordList();
		gameCore();
	}

	private static void populateEasyWordList() {
		_wordlist.clear();
		_wordlist.add("Sten");
		_wordlist.add("Bamse");
		_wordlist.add("Flaska");
		_wordlist.add("Snigel");

	}

	private static void normalGame() {
		populateNormalWordList();
		gameCore();
	}

	private static void populateNormalWordList() {
		_wordlist.clear();
		_wordlist.add("Yxa");
		_wordlist.add("Sylt");
		_wordlist.add("Sax");
		_wordlist.add("Zebra");

	}

	private static void hardGame() {
		populateHardWordList();
		gameCore();
	}

	private static void populateHardWordList() {
		_wordlist.clear();
		_wordlist.add("Ukulele");
		_wordlist.add("Mustasch");
		_wordlist.add("Fönsterputsare");
		_wordlist.add("Schnitzel");

	}
	/**
	 * Tar och kollar igenom guess först ifall det är hela ordet, sedan ifall det inte är en gissning 
	 * för hela ordet kollar den ifall det är en gissning för en bokstav.
	 */
	private static void gameCore() {
		_wrongGuesses.clear();
		String answer = getRandomWord();
		initProgressWord(answer);
		System.out.println(_progressWord);
		for (int tries = 0; tries < 8; tries++) {
			System.out.println("Guess now ffs");
			String guess = _input.nextLine();

			if (guess.toLowerCase().equals(answer.toLowerCase())) {
				System.out.println("Gratz my brotha");
				break;
			}

			if (guess.length() > 1) {
				System.out.println("Wrong word or input length");
				continue;
			} else if (guess.length() == 0) {
				System.out.println("Poo poo brain");
				tries--;
				continue;
			}

			char guessChar = guess.toLowerCase().charAt(0);

			if (answer.toLowerCase().contains(guess)) {
				printStatus(true, answer, guessChar, tries);
				if (answer.toLowerCase().equals(_progressWord.toLowerCase())) {
					System.out.println("Gratz my brotha");
					break;
				}
				tries--;
				continue;
			}
			_wrongGuesses.add(guess);
			printStatus(false, answer, guessChar, tries);

		}
	}
	/**
	 * Tar och gör ordet som är slumpmässigt valt gömt. ex Stol till ----
	 * @param answer
	 */
	private static void initProgressWord(String answer) {
		_progressWord = "";

		for (int i = 0; i < answer.length(); i++) {
			_progressWord += "-";
		}
	}
	/**
	 * Tar och byter ut den gömda "-" med gissningen ifall det är rätt gissning
	 * @param correct
	 * @param answer
	 * @param guessChar
	 * @param tries
	 */
	private static void printStatus(boolean correct, String answer, char guessChar, int tries) {
		if (correct) {
			// byt ut - i _progressWord där gissningen skall vara. ex --- = kul | guess ==
			// 'k' > k--
			char[] progressArray = _progressWord.toCharArray();

			for (int i = 0; i < answer.length(); i++) {
				if (answer.toLowerCase().charAt(i) == guessChar) {
					progressArray[i] = guessChar;
				}

			}
			_progressWord = String.copyValueOf(progressArray);

			System.out.println(_progressWord);
			System.out.println(_wrongGuesses.toString());
			System.out.println((7 - tries) + " tries left");
			hangmanImg(tries);
			// Måla gubben, skriv ut antal liv kvar
		} else {
			System.out.println(_progressWord);
			System.out.println(_wrongGuesses.toString());
			System.out.println((7 - tries) + " tries left");
			hangmanImg(tries);
			// Måla gubben, skriv ut antal liv kvar
		}
	}
	/**
	 * Tar och väljer ett ord slumpmässigt
	 * @return
	 */
	private static String getRandomWord() {
		int randomIndex = (int) (Math.random() * _wordlist.size());
		String randomWord = _wordlist.get(randomIndex);
		return randomWord;
	}
	/**
	 * En metod med Hangman som skrivs upp
	 * @param tries
	 */
	private static void hangmanImg(int tries) {
		if (tries == 1) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("___|___");

		}
		if (tries == 2) {
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (tries == 3) {
			System.out.println("   ____________");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (tries == 4) {
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (tries == 5) {
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |           |");
			System.out.println("   |           |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (tries == 6) {
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |           |");
			System.out.println("   |           |");
			System.out.println("   |          / \\ ");
			System.out.println("___|___      /   \\");
		}
		if (tries == 7) {
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |          _|_");
			System.out.println("   |         / | \\");
			System.out.println("   |          / \\ ");
			System.out.println("___|___      /   \\");
		}
	}
}
