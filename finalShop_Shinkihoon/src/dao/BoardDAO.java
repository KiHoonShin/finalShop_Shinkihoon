package dao;

import java.time.LocalDate;
import java.util.ArrayList;

import dao.FileDAO.FileName;
import dto.Board;
import dto.Item;
import dto.Member;
import util.Util;

public class BoardDAO {

	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	int maxNo;
	int cnt;
	String fileName = FileName.BOARD.getName();
	ArrayList<Board> boardList = new ArrayList<>();
	
	public void roadFromBoardFile() {
		FileDAO fileDAO = FileDAO.getInstance();
		String board = fileDAO.roadFile(fileName);
		if(board.equals("")) {
			System.out.println("게시판 데이터 없음");
			return;
		}
		String[] temp = board.split("\n");
		for(int i = 0; i < temp.length; i ++) {
			String[] info = temp[i].split("/");
			boardList.add(new Board(Integer.parseInt(info[0]), info[1], info[2], info[3], info[4], Integer.parseInt(info[5])));
			maxNo = Integer.parseInt(info[0]);
			cnt +=1;
		}
	}
	
	public void print_boardTitleAll() {
		int curPage = 1; // 현재 페이지 번호
		int allPage = 0; //전체 페이지 수
		int num = 1; // 게시글 번호
		int startRow = 0; // 현재 페이지 게시글 시작 번호
		int endRow = 0; // 현재 페이지 게시글 마지막 번호
		int pageSize = 5; // 한 페이지에 보여줄 게시글 수 
		int count = boardList.size(); // 전체 게시글 수 
		
		allPage = count / pageSize;
		if(count % pageSize > 0) {
			allPage +=1;
		}
		
		while(true) {
			startRow = (curPage-1) * pageSize;
			num = startRow+1;
			endRow = startRow + pageSize;
			if(endRow > cnt) {
				endRow = cnt;
			}
			System.out.printf("총 게시글 %d 개 %n", cnt);
			System.out.printf("현재 페이지 [%d / %d] %n", curPage, allPage);
			for(int i = startRow; i < endRow; i+=1) {
			//	boardList.get(i).setNum(num);
			//	num += 1;
				System.out.println(boardList.get(i));
			}
			System.out.println("[1]이전\n[2]이후\n[3]게시글보기\n[0]종료");
			int sel = Util.getValue("메뉴", 0, 3);
			if(sel == 1) {
				curPage -=1;
				if(curPage < 1) {
					curPage = 1;
					System.out.println("이전 페이지 존재 안함");
					return;
				}
			} else if(sel == 2) {
				curPage += 1;
				if(curPage > allPage) {
					curPage = allPage;
					System.out.println("이후 페이지 존재 안함");
					return;
				}
			} else if(sel == 3) {
				int input = Util.getValue("게시글 번호",startRow+1 ,endRow )-1;
				for(int i = startRow; i < endRow; i+=1) {
					if(i == input) {
						int hit = boardList.get(i).getHits();
						hit+=1;
						boardList.get(i).setHits(hit);
						System.out.println(boardList.get(i));
						System.out.println("-------------");
						System.out.println(boardList.get(i).getContents());
						System.out.println();
						System.out.println();
						System.out.println();
						return;
					}
				}
			} else {
				return;
			}
		} //while
	}
	
	public void my_board(String log) {
		for(Board b : boardList) {
			if(b.getId().equals(log)) {
				System.out.println(b);
				System.out.println(b.getContents());
				System.out.println("----------------------------------------");
			}
		}
		System.out.println("[1]삭제\n[0]돌아가기");
		int sel = Util.getValue("", 0, 1);
		if(sel == 0) {
			return;
		} else {
			int delNum = Util.getValue("삭제할 게시글 번호", 1 , cnt)-1;
			if(!boardList.get(delNum).getId().equals(log)) {
				System.out.println("본인 게시글만 삭제하실 수 있습니다.");
				return;
			}
			for(int i = 0; i < boardList.size(); i+=1) {
				if(i == delNum) {
					boardList.remove(delNum);
				}
			}
			this.cnt -=1;
			System.out.println("게시글 삭제 완료");
			int cnt = 1;
			for(Board b : boardList) {
				b.setBoradNum(cnt++);
				System.out.println(b);
				maxNo = cnt;
				}
			return;
		}
	}
	
	public void plus_board(String log) {
		String id = log;
		System.out.println("[ 게시글 추가하기 ]");
		String title = Util.getValue("게시글 제목");
		String contents = Util.getValue("게시글 내용");
		LocalDate now = LocalDate.now();
		String date = now.toString();
		boardList.add(new Board(++maxNo, title, contents, id, date, 1));
		cnt +=1;
		System.out.println(boardList.get(maxNo-1));
	}
	
	// 게시글 삭제
	public void remove_board() {
		int delNum = Util.getValue("삭제할 게시글 번호", 1, maxNo);
		for(int i = 0; i < boardList.size(); i+=1) {
			if(boardList.get(i).getBoradNum() == delNum) {
				boardList.remove(i);
			}
		}
		this.cnt -=1;
		
		// 보드넘 재할당
		int bNum = 1;
		for(int i = 0; i < boardList.size(); i+=1) {
			boardList.get(i).setBoradNum(bNum);
			bNum+=1;
		}
		System.out.println("게시글 삭제 완료");
	}
}
