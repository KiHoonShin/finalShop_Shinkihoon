package dao;

import java.util.ArrayList;

import dao.FileDAO.FileName;
import dto.Board;
import dto.Item;
import dto.Member;

public class MemberDAO {
	ArrayList<Member> memberList = new ArrayList<>();
	FileDAO fileDAO = FileDAO.getInstance();
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
		}
	}
	
	
	
}
