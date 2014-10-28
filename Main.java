package cmps450.project.part1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public static void main(String[] args){
class Functions {
	static final int EOF = -1;
	static final int LETTER = 0;
	static final int DIGIT = 1;
	static final int UNKNOWN = 99;
	static final int INT_LIT = 10;
	static final int IDENT = 11;
	static final int ASSIGN_OP = 20;
	static final int ADD_OP = 21;
	static final int SUB_OP = 22;
	static final int MULT_OP = 23;
	static final int DIV_OP = 24;
	static final int LEFT_PAREN = 25;
	static final int RIGHT_PAREN = 26;
	private String nextChar[] = new String[100];
	private int charClass;
	private int nextToken;
	private String q;
	private int t3;

	public void getchar(int t, String s) {
		int t1 = t;
		nextChar[t1] = s;
		if (nextChar[t1] != null) {
			if (nextChar[t1].matches("[A-Za-z]")) {
				charClass = LETTER;
			} else if (nextChar[t1].matches("[0-9]")) {
				charClass = DIGIT;
			} else {
				charClass = UNKNOWN;
			}
		} else {
			charClass = EOF;
		}
	}

	public int lookup(String ch, int o) {
		int t4 = o;
		String cc = ch;
		switch (cc) {
		case "(":
			nextToken = LEFT_PAREN;
			q = "(";
			break;
		case ")":
			nextToken = RIGHT_PAREN;
			q = ")";
			break;
		case "+":
			nextToken = ADD_OP;
			q = "+";
			break;
		case "-":
			nextToken = SUB_OP;
			q = "-";
			break;
		case "*":
			nextToken = MULT_OP;
			q = "*";
			break;
		case "/":
			nextToken = DIV_OP;
			q = "/";
			break;
		case "=":
			nextToken = ASSIGN_OP;
			q = "=";
			break;
		default:
			nextToken = EOF;
			q = nextChar[t4];
			break;
		}
		return nextToken;
	}

	public int lex(int u, String s, int v, String s1) {
		t3 = u;
		nextChar[t3] = s;
		int v1 = v;
		String s2 = s1;
		if (nextChar[t3] != null) {
			if (nextChar[t3].matches("[A-Za-z]")) {
				charClass = LETTER;
			} else if (nextChar[t3].matches("[0-9]")) {
				charClass = DIGIT;
			} else {
				charClass = UNKNOWN;
			}
		} else {
			charClass = EOF;
		}
		switch (charClass) {
		case LETTER:
			int k = t3;
			do {
				q = nextChar[t3];
				if (k < v1 - 1) {
					k = k + 1;
					nextChar[k] = String.valueOf(s2.charAt(k));
					q = s2.substring(t3, k);
				} else {
					nextChar[k] = String.valueOf(s2.charAt(k));
					q = s2.substring(t3, k + 1);
					k = k + 1;
					nextChar[k] = "!!!!";
				}
			} while (nextChar[k].matches("[a-zA-Z]"));
			t3 = k - 1;
			nextToken = IDENT;
			break;
		case DIGIT:
			int k1 = t3;
			do {
				q = nextChar[t3];
				if (k1 < v1 - 1) {
					k1 = k1 + 1;
					nextChar[k1] = String.valueOf(s2.charAt(k1));
					q = s2.substring(t3, k1);
				} else {
					nextChar[k1] = String.valueOf(s2.charAt(k1));
					q = s2.substring(t3, k1 + 1);
					k1 = k1 + 1;
					nextChar[k1] = "!!!!";
				}
			} while (nextChar[k1].matches("[0-9]"));
			t3 = k1 - 1;
			nextToken = INT_LIT;
			break;
		case UNKNOWN:
			lookup(nextChar[t3], t3);
			break;
		}
		System.out.println("NEXT TOKEN IS " + nextToken + "   "
				+ "NEXT LEXEME IS " + q.toString());
		return nextToken;
	}

	public int getT3() {
		return t3;
	}

	public void setT3(int t3) {
		this.t3 = t3;
	}
}

public class Main {
	public static void main(String args[]) throws IOException {
		String nextChar[] = new String[100];
		String input;
		System.out.println("ENTER INPUT");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();
		if (input.isEmpty()) {
			do {
				System.out.println("INPUT IS EMPTY; PLEASE ENTER A VALID INPUT");
				input = br.readLine();
			} while (input.isEmpty());
		}
		int q = input.length();
		input.trim();
		Functions f1 = new Functions();
		for (int i = 0; i < q; i++) {
			nextChar[i] = String.valueOf(input.charAt(i));
			if (nextChar[i].matches("[a-zA-Z]") | nextChar[i].matches("[0-9]")) {
				f1.lex(i, nextChar[i], q, input);
				i = f1.getT3();
			} else if (nextChar[i].matches(" ")) {
				StringBuilder sb = new StringBuilder(input);
				sb.deleteCharAt(i);
			} else {
				nextChar[i] = String.valueOf(input.charAt(i));
				do {
					f1.lex(i, nextChar[i], q, input);
				} while (nextChar[q] != null);
			}
		}
		System.out.println("Next Token is: -1  Next Lexeme is EOF ");
	}
}
}