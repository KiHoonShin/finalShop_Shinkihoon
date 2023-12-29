package menu_admin;

import java.util.Map;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import util.Util;

public class AdminItem implements MenuCommand {

	private MallController cont; 
	
	@Override
	public void init() {
		System.out.println("==========[ 관리자 쇼핑몰관리]==========");
		System.out.println("카테고리 순으로 정렬 / 카테고리가 같으면 아이템 이름순으로 정렬");
	}

	@Override
	public boolean update() {
		cont = MallController.getInstance();
		ItemDAO dao = ItemDAO.getInstance();
		System.out.println("[1] 아이템 추가\n[2] 아이템 삭제\n[3] 총 매출 아이템"
				+ "개수 출력(판매량 높은순으로)\n[4] 뒤로가기\n[0] 종료");
		int sel = Util.getValue("메뉴", 0, 4);
		dao.print_item();
		if(sel == 0) {
			System.out.println("종료");
			cont.setNext(null);
		} else if(sel == 1) {
			dao.add_item();
		} else if(sel == 2) {
			dao.del_item();
		}
		return false;
	}
	
}
