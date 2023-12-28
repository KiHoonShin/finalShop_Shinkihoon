package dao;

import java.util.ArrayList;

import controller.MallController;
import dao.FileDAO.FileName;
import dto.Board;
import dto.Item;
import dto.Member;
import util.Util;

public class MemberDAO {
	
	ArrayList<Member> memberList = new ArrayList<>();
	int maxNo = 1000;
	int cnt;
	
	final String memberName = FileName.MEMBER.getName();
	
	private MemberDAO(){
		
	}
	
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	
	
	// member.txt 파일로부터 data 받은 후 memberList 생성
	public void roadToFile() {
		Member mm = new Member();
		int num = mm.getNum();
		FileDAO fileDAO = FileDAO.getInstance();
		String member = fileDAO.roadFile(memberName);
		if(member.equals("")) {
			System.out.println("데이터 없음");
			return;
		}
		String[] temp = member.split("\n");
		for(int i = 0; i < temp.length; i++) {
			String[] info = temp[i].split("/");
			memberList.add(new Member(Integer.parseInt(info[0]), info[1], info[2], info[3]));
			maxNo = Integer.parseInt(info[0]);
			mm.setNum(++num);
			cnt += 1;
		}
		System.out.println("== 데이터 로드 완료 ==");
	}
	
	// 회원가입
	public boolean insertMember(String id, String pw , String name) {
		Member mem = getMemberById(id);
		memberList.add(new Member(++maxNo, id, pw, name));
		System.out.println(memberList.get(cnt)); //회원 추가 출력문 추가해야됨
		cnt += 1; 
		return true;
	}
	
	// 아이디 체크
	public Member getMemberById(String id) {
		if(memberList == null) return null;
		for(Member m : memberList) {
			if(m.getId().equals(id)) {
				return m;
			}
		}
		return null;
	}
	
	public Member isValidMember(String id, String pw) {
		for(Member m : memberList) {
			if(m.getId().equals(id) && m.getPw().equals(pw)) {
				return m;
			}
		}
		return null;
	}
	
	
	public void print_member() {
		for(Member m : memberList) {
			System.out.println(m);
		}
	}
	
	// 아이디가 같을때 내 정보 출력
	public void print_member(String log) {
		for(Member m : memberList) {
			if(m.getId().equals(log))
			System.out.println(m);
		}
	}
	
	// 나의정보 비밀번호 수정
	public void modificate_my_info(String log) {
		Member mm = null;
		for(Member m : memberList) {
			if(m.getId().equals(log)) {
				mm = m;
				break;
			}
		}
			String pw = Util.getValue("패스워드");
			if(!isValidPw(mm, pw)) return;
	}
	
	// 패스워드 일치하는지
	private boolean isValidPw(Member mm , String pw) {
		if(!pw.equals(mm.getPw())) {
			System.out.println("비밀번호가 틀렸습니다");
			return false;
		}
		String newPw = Util.getValue("신규 패스워드");
		if(pw.equals(newPw)) {
			System.out.println("다른 비밀번호를 입력해주세요");
			return false;
		}
		System.out.println("비밀번호 변경 완료");
		mm.setPw(newPw);
		return true;
	}
	
}
