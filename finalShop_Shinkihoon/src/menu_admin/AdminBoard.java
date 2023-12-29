package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class AdminBoard implements MenuCommand {

	private MallController cont; 

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("==========[ 관리자 게시판]==========");
	}

	@Override
	public boolean update() {
		BoardDAO dao = BoardDAO.getInstance();
		System.out.println("[1] 게시글 목록\n[2] 게시글 삭제\n[3] 뒤로가기\n[0] 종료");
		int sel = Util.getValue("메뉴", 0, 3);
		if(sel == 0) {
			System.out.println("종료");
			cont.setNext(null);
		} else if(sel == 1) {
			dao.print_boardTitleAll();
		} else if(sel == 2) {
			dao.remove_board();
		} else if(sel == 3) {
			cont.setNext("AdminMain");
		}
		return false;
	}

}
