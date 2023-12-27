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

		return false;
	}

	
	
}
