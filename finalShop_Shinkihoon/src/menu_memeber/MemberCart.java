package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import dao.MemberDAO;
import util.Util;

public class MemberCart implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("==========[ 구매내역 ]==========");
		System.out.println("[1] 쇼핑하기\n[2] 뒤로가기\n[0] 종료");
		
	}

	@Override
	public boolean update() {
		ItemDAO itemDAO = ItemDAO.getInstance();
		CartDAO dao = CartDAO.getInstance();
		dao.print_cartList(cont.getLoginId() , itemDAO);
		int sel = Util.getValue("메뉴", 0, 2);
		if(sel == 1) {
			cont.setNext("MemberShopping");
		} else if(sel == 2) {
			cont.setNext("MemberMain");
		} else {
			System.out.println("종료");
			cont.setNext(null);
		}
		return false;
	}

	
	
}
