package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import _mall.MenuCommand;
import dao.FileDAO;
import menu_admin.AdminBoard;
import menu_admin.AdminItem;
import menu_admin._AdminMain;
import menu_admin.AdminMember;
import menu_mall.MallJoin;
import menu_mall.MallLogin;
import menu_mall._MallMain;
import menu_memeber.MemberBoard;
import menu_memeber.MemberCart;
import menu_memeber.MemberInfo;
import menu_memeber._MemberMain;
import menu_memeber.MemberQuit;
import menu_memeber.MemberShopping;

public class MallController {
	private MallController() {}

	static private MallController instance = new MallController();

	static public MallController getInstance() {
		return instance;
	}
	
	private Scanner sc = new Scanner(System.in);
	private String loginId;
	private String next;
	private MenuCommand menuCom;
	public Map<String, MenuCommand> mapCont;

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void init() {
		mapCont = new HashMap<>();
		mapCont.put("MallMain", new _MallMain());
		mapCont.put("MallJoin", new MallJoin());
		mapCont.put("MallLogin", new MallLogin());
		mapCont.put("AdminBoard", new AdminBoard()); // 관리자 쇼핑몰 관리
		mapCont.put("AdminItem", new AdminItem()); // 관리자 쇼핑몰 관리
		mapCont.put("AdminMain", new _AdminMain()); // 관리자 메인
		mapCont.put("AdminMember", new AdminMember()); // 관리자 회원목록 관리
		mapCont.put("MemberBoard", new MemberBoard());
		mapCont.put("MemberCart", new MemberCart());
		mapCont.put("MemberInfo", new MemberInfo());
		mapCont.put("MemberMain", new _MemberMain());
		mapCont.put("MemberShopping", new MemberShopping());
		mapCont.put("MemberQuit", new MemberQuit());

		menuCom = mapCont.get("MallMain");
		menuCom.init();
		update();

	}

	public void update() {
		while (true) {
			if (!menuCom.update()) {
				if (next != null) {
					menuCom = mapCont.get(next);
					menuCom.init();
				} else {
					return;
				}
			}
		}
	}
	
	// int 값 입력하기 
	public int getValue(String msg, int start, int end) {
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
	// ------------------------
	// string 값 입력하기
	public String getValue(String msg) {
		System.out.printf("▶ %s   입력 : " , msg);
		String input = sc.next();
		return input;
	}
	// --------------------

}
