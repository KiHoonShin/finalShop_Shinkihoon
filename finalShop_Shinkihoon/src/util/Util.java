package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
	
	private static Scanner sc = new Scanner(System.in);

	public static void closeScanner() {
		sc.close();
	}
	
	// int 값 입력하기 
	public static int getValue(String msg, int start, int end) {
		while(true) {
			System.out.printf("▶ %s 입력[%d-%d] 입력 : " , msg, start, end);
			try {
				int sel = sc.nextInt();
				sc.nextLine();
				if(sel < start || sel > end) {
					System.out.printf("[%d ~ %d] 입력하세요. %n" , start, end);
					continue;
				}
				return sel;
			} catch (Exception e) {
				System.out.println("숫자만 입력하세요.");
				sc.nextLine();
			}
		}
	}
	
	// string 값 입력하기
	public static String getValue(String msg) {
		System.out.printf("▶ %s   입력 : " , msg);
		String input = sc.next();
		return input;
	}
}
