package dto;

import java.time.LocalDate;

public class Board {
	private static int num;
	private int boardNum;
	private String title;
	private String id;
	private String date;
	private String contents;
	private int hits;
	
	public Board(int boardNum, String title, String contents, String id, String date, int hits) {
		super();
		this.boardNum = boardNum;
		this.title = title;
		this.contents = contents;
		this.id = id;
		this.date = date;
		this.hits = hits;
	}

	public static int getNum() {
		return num;
	}

	public static void setNum(int num) {
		Board.num = num;
	}

	public int getBoradNum() {
		return boardNum;
	}

	public void setBoradNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	@Override
	public String toString() {
		return "( "+boardNum+") "+"[ 제목 : " + title + "\t"+ "작성자 : " + id + "\t" + "날짜 : " + date + "조회수 : "+hits + "]";
	}

	

}
