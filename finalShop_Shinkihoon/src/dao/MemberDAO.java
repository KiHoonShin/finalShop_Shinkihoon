package dao;

import java.io.FileWriter;
import java.io.IOException;
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
	
	// 멤버 전체 출력
	public void print_all() {
		System.out.println("==== 전체 회원 목록 ====");
		for(Member m : memberList) {
			System.out.println(m);
		}
	}
	
	// 회원 삭제 (admin)
	public void del_member() {
		System.out.println("회원 삭제시 구매 내역이 사라집니다");
		String id = Util.getValue("삭제할 회원 아이디");
		if(!id_check(id)) {
			System.out.println("삭제 실패");
			return;
		}
		System.out.println("삭제 완료");
	}
	
	
	// 관리자메뉴 전용 아이디 체크
	public boolean id_check(String id) {
		if(id.equals("admin")) {
			System.out.println("관리자 회원 삭제 불가능");
			return false;
		}
		Member m = getMemberById(id);
		if(m == null) {
			System.out.println("존재하지 않는 아이디");
			return false;
		}
		 int delIdx = delIdx(m);
		for(int i = 0; i < memberList.size(); i+=1) {
			if(i == delIdx) {
				memberList.remove(delIdx);
			}
		}
		this.cnt -=1;
		System.out.println("회원 구매 내역 삭제 완료");
		return true;
	}
	
	private int delIdx(Member m) {
		int idx = 0;
		for(Member mm : memberList) {
			if(m == mm) {
				return idx;
			}
			idx+=1;
		}
		return -1;
	}
	
	// 로그인 -> 회원 탈퇴
	public void quit_member(String log , CartDAO cartDAO) {
		for(int i = 0; i < memberList.size(); i+=1) {
			if(log.equals(memberList.get(i).getId())) {
				memberList.remove(i);
			}
		}
		cartDAO.quit_member(log);
		System.out.println("회원 구매 내역 삭제 완료");
		System.out.println("탈퇴 완료");
		this.cnt -=1;
	}
	
//	// 멤버 파일 저장
//	public void saveMemberFile() {
//		try(FileWriter fw = new FileWriter(CUR_PATH+FileName.MEMBER)){
//			String data = "";
//			for(Member mm : memberList) {
//				data += mm.getMemberNum()+"/"+mm.getId()+"/"+mm.getPw() +mm.getMemberName()+"\n";
//			}
////			for(int i = 0; i < memberDAO.memberList.size(); i+=1) {
////				data += memberDAO.memberList.get(i).getMemberNum()+"/"+memberDAO.memberList.get(i).getId()+
////						"/" + memberDAO.memberList.get(i).getPw() + "/" +memberDAO.memberList.get(i).getMemberName()+"\n";
////			}
//			fw.write(data);
//			System.out.println("member 파일 저장 성공" );
//		} catch (IOException e) {
//			System.out.println("파일 저장 실패");
//		}
//	}
}
