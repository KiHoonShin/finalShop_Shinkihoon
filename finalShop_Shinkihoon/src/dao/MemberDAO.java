package dao;

import java.util.ArrayList;

import controller.MallController;
import dao.FileDAO.FileName;
import dto.Board;
import dto.Item;
import dto.Member;

public class MemberDAO {
	MallController con = MallController.getInstance();
	ArrayList<Member> memberList = new ArrayList<>();
	FileDAO fileDAO = FileDAO.getInstance();
	int maxNo;
//	final String boardName = FileName.BOARD.getName();
//	final String cartName = FileName.CART.getName();
//	final String itemName = FileName.ITEM.getName();
//	final String memberName = FileName.MEMBER.getName();
	
	MemberDAO(){
		
	}
	
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	
	// member.txt 파일로부터 data 받은 후 memberList 생성
	public void roadToFile() {
		String member = fileDAO.roadFile(FileName.MEMBER.getName());
		String[] temp = member.split("\n");
		for(int i = 0; i < temp.length; i++) {
			String[] info = temp[i].split("/");
			memberList.add(new Member(Integer.parseInt(info[0]), info[1], info[2], info[3]));
			maxNo = Integer.parseInt(info[0]);
		}
		
	}
	
	// 회원가입
	void new_member() {
		System.out.println("=====[ 회원가입 ]=====");
		String id = con.getValue("아이디");
		Member mem = idCheck(id);
		if(mem != null) {
			System.out.println("이미 사용하는 아이디");
			return;
		}
		String pw = con.getValue("비밀번호");
		String name = con.getValue("이름");
		memberList.add(new Member(maxNo++, id, pw, name));
		//System.out.println(); 회원 추가 출력문 추가해야됨
		System.out.println("[ 회원 추가 완료 ]");
	}
	
	// 아이디 체크
	Member idCheck(String id) {
		if(memberList == null) return null;
		for(Member m : memberList) {
			if(m.getId().equals(id)) {
				return m;
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
}
