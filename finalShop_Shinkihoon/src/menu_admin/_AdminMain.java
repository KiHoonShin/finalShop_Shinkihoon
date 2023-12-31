package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import dao.CartDAO;
import dao.FileDAO;
import dao.ItemDAO;
import dao.MemberDAO;
import util.Util;

public class _AdminMain implements MenuCommand {
	
	private MallController cont; 
	
	@Override
	public void init() {
		cont = MallController.getInstance();
		MemberDAO mDAO = MemberDAO.getInstance();
		CartDAO cDAO = CartDAO.getInstance();
		ItemDAO iDAO = ItemDAO.getInstance();
		BoardDAO bDAO = BoardDAO.getInstance();
		FileDAO fDAO = FileDAO.getInstance();
		//while(true) {
			print_admin_menu();
			int sel = Util.getValue("메뉴", 0, 5);
			if(sel == 1) {
				cont.setNext("AdminMember");
			} else if(sel == 2) {
				cont.setNext("AdminItem");
			} else if(sel == 3) {
				cont.setNext("AdminBoard");
			} else if(sel == 4) {
				cont.setLoginId(null);
				cont.setNext("MallMain");
			} else if(sel == 5) {
				fDAO.saveAllFile(mDAO, iDAO, cDAO, bDAO);
			} else {
				System.out.println("종료합니다.");
				cont.setNext(null);
				return;
			}
		//}
	}

	@Override
	public boolean update() {


		return false;
	}
	
	void print_admin_menu() {
		System.out.println("===========[ 관리자 ]===========");
		System.out.println("[1] 회원관리");
		System.out.println("[2] 상품관리");
		System.out.println("[3] 게시판관리");
		System.out.println("[4] 로그아웃");
		System.out.println("[5] 파일저장");
		System.out.println("[0] 종료");
	}

}
