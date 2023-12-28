package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class MemberInfo implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("==========[ 내정보 ]==========");
	}

	@Override
	public boolean update() {
		MemberDAO memberDAO = MemberDAO.getInstance();
		System.out.println("[1] 비밀번호 변경\n[2] 뒤로가기\n[0] 종료");
		System.out.println("==============================");
		memberDAO.print_member(cont.getLoginId());
		int sel = Util.getValue("메뉴", 0, 2);
		if(sel == 1) {
			memberDAO.modificate_my_info(cont.getLoginId());
		} else if(sel == 2) {
			cont.setNext("MemberMain");
		} else {
			System.out.println("종료");
			cont.setNext(null);
		}
		return false;
	}
	
	
	
}
