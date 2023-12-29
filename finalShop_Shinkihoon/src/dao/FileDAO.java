package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import util.Util;

public class FileDAO {


	enum FileName {
		BOARD("board.txt"), MEMBER("member.txt"), ITEM("item.txt"), CART("cart.txt");
		private String name;
		FileName(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		
	
	}

	private static String CUR_PATH = System.getProperty("user.dir")+"\\src\\files\\";
	
	private FileDAO() {}

	private static FileDAO instance = new FileDAO();

	static public FileDAO getInstance() {
		return instance;
	}
	
	private void createFile(FileName name) {
		Path path = Paths.get("src/files/" + name.getName());
		try {
			Files.createFile(path);
		} catch (IOException e) {
			System.out.println("파일이 이미 있음");
		}
	}

	private void init() {
		createFile(FileName.BOARD);
		createFile(FileName.MEMBER);
		createFile(FileName.ITEM);
		createFile(FileName.CART);
	}
	
	public void saveAllFile(MemberDAO memberDAO, ItemDAO itemDAO, CartDAO cartDAO, BoardDAO boardDAO) {
		saveMemberFile(memberDAO);
		saveItemFile(itemDAO);
		saveCartFile(cartDAO);
		saveBoardFile(boardDAO);
	}
	
	
	// 멤버 파일 저장하기
	public void saveMemberFile(MemberDAO memberDAO) {
		try(FileWriter fw = new FileWriter(CUR_PATH+"member.txt")){
			String data = "";
			for(int i = 0; i < memberDAO.memberList.size(); i+=1) {
				data += memberDAO.memberList.get(i).getMemberNum()+"/"+memberDAO.memberList.get(i).getId()+
						"/" + memberDAO.memberList.get(i).getPw() + "/" +memberDAO.memberList.get(i).getMemberName()+"\n";
			}
			fw.write(data);
			System.out.println("member 파일 저장 성공" );
		} catch (IOException e) {
			System.out.println("파일 저장 실패");
		}
	}
	
	// 아이템 파일 저장하기
	public void saveItemFile(ItemDAO itemDAO) {
		try(FileWriter fw = new FileWriter(CUR_PATH+"item.txt")){
			String data = "";
			for(int i = 0; i < itemDAO.itemList.size(); i+=1) {
				data += itemDAO.itemList.get(i).getItemNum()+"/"+itemDAO.itemList.get(i).getCategoryName()+
						"/" + itemDAO.itemList.get(i).getItemName() + "/" +itemDAO.itemList.get(i).getPrice()+"\n";
			}
			fw.write(data);
			System.out.println("item 파일 저장 성공" );
		} catch (IOException e) {
			System.out.println("파일 저장 실패");
		}
	}
	
	// cart파일 저장
	public void saveCartFile(CartDAO cartDAO) {
		try(FileWriter fw = new FileWriter(CUR_PATH+"cart.txt")){
			String data = "";
			for(int i = 0; i < cartDAO.cartList.size(); i+=1) {
				data += cartDAO.cartList.get(i).getCartNum()+"/"+cartDAO.cartList.get(i).getId()+
						"/" + cartDAO.cartList.get(i).getItemNum()+ "/" +cartDAO.cartList.get(i).getItemCnt()+"\n";
			}
			fw.write(data);
			System.out.println("cart 파일 저장 성공" );
		} catch (IOException e) {
			System.out.println("파일 저장 실패");
		}
	}
	
	// board 파일 저장
	public void saveBoardFile(BoardDAO boardDAO) {
		try(FileWriter fw = new FileWriter(CUR_PATH+"board.txt")){
			String data = "";
			for(int i = 0; i < boardDAO.boardList.size(); i+=1) {
				data += boardDAO.boardList.get(i).getBoradNum()+"/"+boardDAO.boardList.get(i).getTitle()+
						"/" + boardDAO.boardList.get(i).getContents()+ "/" +boardDAO.boardList.get(i).getDate()+"/"+ boardDAO.boardList.get(i).getHits()+"\n";
			}
			fw.write(data);
			System.out.println("board 파일 저장 성공" );
		} catch (IOException e) {
			System.out.println("파일 저장 실패");
		}
	}
	
	
	
	
	// 저장된 파일 data로 가져오기
	public String roadFile(String fileName) {
		try(FileReader fr = new FileReader(CUR_PATH+fileName);
			BufferedReader br = new BufferedReader(fr)){
			String data = "";
			while(true) {
				String line = br.readLine();
				if(line == null) break;
				data += line+"\n";
			}
			return data;
		} catch (IOException e) {
			System.out.println(CUR_PATH);
			System.out.println("파일로드 실패");
			e.printStackTrace();
		}
		return null;
	}

}
