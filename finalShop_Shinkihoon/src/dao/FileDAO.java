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
	
	// 저장된 파일 data로 가져오기
	 String roadFile(String fileName) {
		try(FileReader fr = new FileReader(CUR_PATH+fileName);
			BufferedReader br = new BufferedReader(fr)){
			String data = "";
			while(true) {
				String line = br.readLine()+"\n";
				if(line == null) break;
				data += line;
			}
			return data;
		} catch (IOException e) {
			System.out.println("파일로드 실패");
			e.printStackTrace();
		}
		return null;
	}

}