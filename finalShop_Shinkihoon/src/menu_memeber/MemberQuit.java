package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.MemberDAO;
import util.Util;

public class MemberQuit implements MenuCommand{
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.printf("========[ %s 회원탈퇴 ]========%n" , cont.getLoginId());
		System.out.println("회원 탈퇴시 구매 내역이 사라집니다");
		System.out.println("정말 탈퇴하시겠습니까?");
	}

	@Override
	public boolean update() {
		MemberDAO mDAO = MemberDAO.getInstance();
		CartDAO cDAO = CartDAO.getInstance();
		System.out.println("[1] 예\n[2] 뒤로가기\n[0] 종료");
		int sel = Util.getValue("메뉴", 0, 2);
		if(sel == 1) {
			mDAO.quit_member(cont.getLoginId(), cDAO);
			cont.setLoginId(null);
			cont.setNext("MallMain");
		} else if(sel == 2) {
			cont.setNext("MemberMain");
		} else {
			System.out.println("종료");
			cont.setNext(null);
		}
		return false;
	}
	
}
