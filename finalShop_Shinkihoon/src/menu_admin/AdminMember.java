package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.MemberDAO;
import util.Util;

public class AdminMember implements MenuCommand {
	
	private MallController cont; 

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("==========[ 관리자 회원목록 ]==========");
	}

	@Override
	public boolean update() {
		MemberDAO dao = MemberDAO.getInstance();
		System.out.println("[1] 회원목록\n[2] 회원삭제\n[3] 뒤로가기 \n[0] 종료");
		System.out.println("==================================");
		int sel = Util.getValue("메뉴", 0, 3);
		if(sel == 1) {
			dao.print_all();
		} else if(sel == 2) {
			dao.del_member();
		} else if(sel == 3) {
			cont.setNext("AdminMain");
		} else {
			System.out.println("종료");
			cont.setNext(null);
		}
		
		return false;
	}
	
	
	
	
}
