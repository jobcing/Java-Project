import java.io.*;
import java.util.*;
import java.util.regex.*;

public class ConsoleEx {
	static String[] argArr; // 입력한 매개변수를 담기위한 문자열 배열
	static LinkedList q = new LinkedList(); // 사용자가 입력한 내용을 저장할 큐
	static final int MAX_SIZE = 5; // 큐에 최대 5개까지만 저장되도록한다.
	
	static File curDir;
	
	static{
		try{
			curDir = new File(System.getProperty("user.dir"));
		}catch(Exception e){}
	}
	
	public static void main(String[] ar){
		Scanner scan = new Scanner(System.in);
		
		while(true){
			try{
				String prompt = curDir.getCanonicalPath() + ">>";
				System.out.println(prompt);
				
				// 화면으로부터 라인 단위로 입력받는다.
				String input = scan.nextLine();
				
				save(input);
				
				input = input.trim(); // 입력받은 값에서 앞뒤 공백 제거
				argArr = input.split(" +"); // 정규표현식
				
				String command = argArr[0].trim();
				
				if("".equals(command)) continue;
				
				command = command.toLowerCase(); // 명령어를 소문자로 바꾼다.
				
				if(command.equals("q")){
					System.exit(0);
				}else if(command.equals("history")){
					history();
				}else if(command.equals("dir")){
					dir();
				}else if(command.equals("type")){
					type();
				}else if(command.equals("find")){
					find();
				}else if(command.equals("find2")){
					find2();
				}else if(command.equals("cd")){
					cd();
				}else {
					for(int i = 0; i < argArr.length; i++){
						System.out.println(argArr[i]);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Input Error !");
			}
		} // while(true)
	} // main
	
	public static void save(String input){
		if(input == null || "".equals(input)) return;
		
		q.add(input); // q에 저장
		
		// queue 최대크기를 넘으면 제일 오래된 값을 삭제한다.
		if(((LinkedList)q).size() > MAX_SIZE)
			q.remove();
	}
	
	public static void history(){
		int i = 0;
		
		// LinkedList의 내용을 보여준다.
		ListIterator it = q.listIterator();
		
		while(it.hasNext()){
			System.out.println(++i + "." + it.next());
		}
	}
	
	public static void dir(){
		String pattern = "";
		
		switch(argArr.length){
		case 1:
			for(File f : curDir.listFiles()){
				if(f.isDirectory()){
					System.out.println("[" + f.getName() + "]");
				}else{
					System.out.println(f.getName());
				}
			}
			break;
		case 2:
			pattern = argArr[1];
			pattern = pattern.toUpperCase();
			pattern = pattern.replace(".","\\."); 
			pattern = pattern.replace("*", ".*");
			pattern = pattern.replace("?", ".{1}");
			
			Pattern p = Pattern.compile(pattern);
			
			for(File f : curDir.listFiles()){
				String tmp = f.getName().toUpperCase();
				Matcher m = p.matcher(tmp);
				
				if(m.matches()){
					if(f.isDirectory()) { 
						System.out.println("["+f.getName()+"]"); 
					} else { 
						System.out.println(f.getName()); 
					} 
				} 
			} // for 
			break; 
		default : 
			System.out.println("USAGE : dir [FILENAME]");
		} // switch 
	} // dir() 

	public static void type() throws IOException { 
		if(argArr.length !=2) { 
			System.out.println("Usage : type FILE_NAME"); 
			return; 
		} 

		String fileName = argArr[1]; 

		File tmp = new File(curDir, fileName); 

		if(tmp.exists()) { 
			FileReader fr = new FileReader(tmp); 
			BufferedReader br = new BufferedReader(fr); 

			String line = ""; 
			for(int i=1;(line=br.readLine())!=null;i++) { 
				System.out.println(line); 
			} 
		} else { 
			System.out.println(fileName + " 존재하지 않는 파일입니다."); 
		} 

		return; 
	} 

	public static void find() throws IOException { 
		if(argArr.length!=3) { 
			System.out.println("USAGE : find KEYWORD FILE_NAME"); 
			return; 
		} 

		String keyword = argArr[1]; 
		String fileName = argArr[2]; 

		File tmp = new File(curDir, fileName); 

		if(tmp.exists()) { 
			FileReader fr = new FileReader(tmp); 
			BufferedReader br = new BufferedReader(fr); 

			String line = ""; 

			for(int i=1;(line = br.readLine())!=null;i++) { 
				// keyword를 포함한 라인을 출력한다. 
				if(line.indexOf(keyword) != -1) 
					System.out.println(i + ":" + line); 
			} 
		} else { 
			System.out.println(fileName + " 존재하지 않는 파일입니다."); 
		} 

		return; 
	} 

	public static void find2() throws IOException { 
		if(argArr.length != 3) { 
			System.out.println("USAGE : find2 KEYWORD FILE_NAME"); 
			return; 
		} 

		String keyword = argArr[1]; 
		String pattern = argArr[2]; 

		pattern = pattern.toUpperCase(); 
		pattern = pattern.replace(".","\\."); 
		pattern = pattern.replace("*",".*"); 
		pattern = pattern.replace("?",".{1}"); 
		
		Pattern p = Pattern.compile(pattern); 
		
		for(File f : curDir.listFiles()) { 
			String tmp = f.getName().toUpperCase(); 
			Matcher m = p.matcher(tmp); 
 
			if(m.matches()) { 
				if(f.isDirectory()) continue; 

				FileReader fr = new FileReader(f); 
				BufferedReader br = new BufferedReader(fr); 

				String line = ""; 

				System.out.println("----------------"+f.getName()); 
				for(int i=1;(line = br.readLine())!=null;i++) { 
					// keyword를 포함한 라인을 출력한다. 
					if(line.indexOf(keyword)!=-1) 
						System.out.println(i+":"+line);
					} 
			} 
		} // for 

		return; 
	} 
	public static void cd() { 
		if(argArr.length==1) { 
			System.out.println(curDir); 
			return; 
		} else if(argArr.length > 2) { 
			System.out.println("USAGE : cd directory"); 
			return; 
		} 

		String subDir = argArr[1]; 

		if("..".equals(subDir)) { // 부모 디렉토리 
			File tmp = curDir.getParentFile(); 

			if(tmp==null) { 
				System.out.println("유효하지 않은 디렉토리입니다."); 
			} else { 
				curDir = curDir.getParentFile(); 
			} 
		} else if(".".equals(subDir)) { // 현재 디렉토리 
			System.out.println(curDir); 
		} else { 
			// 현재 디렉토리의 하위 디렉토리(sub-directory)인지 확인한다. 
			File newDir = new File(curDir, subDir); 
			if(newDir.exists() && newDir.isDirectory()) { 
				curDir = newDir; 
			} else { 
				System.out.println("유효하지 않은 디렉토리입니다."); 
			} 
		} // if 
	} // cd() 
} // class 
