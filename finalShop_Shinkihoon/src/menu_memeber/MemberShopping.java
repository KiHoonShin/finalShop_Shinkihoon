package menu_memeber;

import java.util.ArrayList;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import util.Util;

public class MemberShopping implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("======= 쇼핑몰에 오신것을 환영합니다 =======");
		//System.out.println("[1] 과자\n[2] 생선\n[3] 음료\n[4] 고기\n[5] 과일\n[0] 뒤로가기");
	}

	@Override
	public boolean update() {
		ItemDAO dao = ItemDAO.getInstance();
		if(!dao.print_category()) {
			cont.setNext("MemberMain");
		} else {
			cont.setNext("MemberCart");
		}
		//dao.print_category();
		//int sel = Util.getValue("메뉴", 0, 5);
//		if(sel == 0) {
//			cont.setNext("MemberMain");
//		} else {
//			dao.shoppingItem(sel);
//		}
		
		return false;
	}

	
	
	
}
