package dao;

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
	
	
	
	
}
