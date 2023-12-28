package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import dto.Board;
import util.Util;

public class MemberBoard implements MenuCommand{
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("==========[ 게시판 ]==========");
	}

	@Override
	public boolean update() {
		BoardDAO dao = BoardDAO.getInstance();
		System.out.println("[1] 게시글보기\n[2] 게시글추가\n[3] 내 게시글(삭제)\n[4] 뒤로가기\n"
				+"[0] 종료");
		System.out.println("===============================");
		int sel = Util.getValue("메뉴", 0, 4);
		if(sel == 1) {
			dao.print_boardTitleAll();
		} else if(sel == 2) {
			dao.plus_board(cont.getLoginId());
		} else if(sel == 3) {
			dao.my_board(cont.getLoginId());
		} else if(sel == 4) {
			
		} else {
			System.out.println("종료");
			cont.setNext(null);
		}
		return false;
	}
}
