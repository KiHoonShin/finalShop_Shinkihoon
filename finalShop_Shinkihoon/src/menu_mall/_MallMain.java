package menu_mall;

import java.util.ArrayList;

import _mall.MenuCommand;
import controller.MallController;
import dao.FileDAO;
import dao.MemberDAO;
import util.Util;

public class _MallMain implements MenuCommand {

	MallController con = MallController.getInstance();
	MemberDAO memberDAO =  MemberDAO.getInstance();
	
	@Override
	public void init() {
		while(true) {
			print_start_menu();
			int sel = con.getValue("메뉴", 0, 2);
			if(sel == 1) {
				
			} else if(sel == 2) {
				
			} else {
				con.setNext(null);
				return;
			}
		}
	}

	@Override
	public boolean update() {

		return false;
	}

	// 시작 메뉴
	void print_start_menu() {
		System.out.println("=====[ 쇼핑몰 ] =====");
		System.out.println("[1] 회원가입");
		System.out.println("[2] 로그인");
		System.out.println("[0] 종료");
		System.out.println("====================");
	}
	
//	// 회원가입
//	void new_member() {
//		System.out.println("=====[ 회원가입 ]=====");
//		String id = con.getValue("아이디");
//	}
//	
//	// 아이디 체크
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
