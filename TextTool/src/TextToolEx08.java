import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class TextToolEx08 extends JFrame{
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	private Container con;
	
	private JTextArea jta;
	private JTextField jtf, jtf1;
	private JPanel pNorth, pSouth;
	private JLabel lb, lb1;
	
	private String[] btnName = {"Undo", 
								"짝수 줄 삭제",
								"문자 삭제",
								"trim",
								"빈 줄 삭제",
								"접두사 추가",
								"SubString",
								"SubString2",
								"Distinct"};
	private JButton[] btn = new JButton[btnName.length];
	
	private final String CR_LF = System.getProperty("line.separator"); // 개행문자 (줄바꿈문자)
	private String prevText = ""; // JTextArea 내용을 저장하기 위한 변수
	
	private void registerEventHandler(){
		int n = 0; // 버튼 숫자
		
		btn[n++].addActionListener(new ActionListener(){ // 작업 이전 상태로 돌리는 기능
			public void actionPerformed(ActionEvent e){
				String curText = jta.getText();
				
				if(!prevText.equals(""))
					jta.setText(prevText);
				
				prevText = curText;
			}
		});
		
		btn[n++].addActionListener(new ActionListener(){ // 짝수 줄을 삭제하는 기능
			public void actionPerformed(ActionEvent e){
				String curText = jta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				Scanner scan = new Scanner(curText);
				
				for(int i = 0; scan.hasNextLine(); i++){
					String line = scan.nextLine();
					
					if(i % 2 == 0)
						sb.append(line + CR_LF);
				}	
				jta.setText(sb.toString());
			}
		});
		
		btn[n++].addActionListener(new ActionListener(){ // jtf 지정된 문자 삭제하는 기능
			public void actionPerformed(ActionEvent e){
				String curText = jta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				String delText = jtf.getText();
				
				if("".equals(delText)) return;
				
				for(int i = 0; i < curText.length(); i++){
					char ch = curText.charAt(i);
					
					if(delText.indexOf(ch) == -1)
						sb.append(ch);
				}
				
			}
		});
		
		btn[n++].addActionListener(new ActionListener(){ // 라인의 좌우 공백을 제거하는 기능
			public void actionPerformed(ActionEvent e){
				String curText = jta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				Scanner scan = new Scanner(curText);
				
				while(scan.hasNextLine()){
					String line = scan.nextLine(); // 한줄씩 읽어온다.
					sb.append(line.trim() + CR_LF); // 좌우 공백을 제거하고 StringBuffer에 추가
				}
				
				jta.setText(sb.toString());
			}
		});
		
		btn[n++].addActionListener(new ActionListener(){ // 빈 줄 삭제하는 기능
			public void actionPerformed(ActionEvent e){
				String curText = jta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				Scanner scan = new Scanner(curText);
				
				while(scan.hasNextLine()){
					String line = scan.nextLine();
					
					if(!line.isEmpty()) // 만약 공백이 아니라면
						sb.append(line + CR_LF); // 추가시킨다.
				}
				
				jta.setText(sb.toString());
			}
		});
		
		btn[n++].addActionListener(new ActionListener(){ // 각 라인에 접두사, 접미사 붙이는 기능
			public void actionPerformed(ActionEvent e){
				String curText = jta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				String headText = jtf.getText(); // jtf에서 접두사 가져오기
				String tailText = jtf1.getText(); // jtf1에서 접미사 가져오기
				
				Scanner scan = new Scanner(curText);
				
				while(scan.hasNextLine()){
					String line = scan.nextLine();
					sb.append(headText + line + tailText + CR_LF); // 각 줄마다 접두사 붙여서 StringBuffer에 추가
				}
				
				jta.setText(sb.toString());
			}
		});
		
		btn[n++].addActionListener(new ActionListener(){ // 각 줄 앞뒤에 붙어있는 지정된 문자열 자르는 기능
			public void actionPerformed(ActionEvent e){
				String curText = jta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				String delText = jtf.getText();
				String delText1 = jtf1.getText();
				
				Scanner scan = new Scanner(curText);
				
				while(scan.hasNextLine()){
					String line = scan.nextLine();
					sb.append(line.substring(delText.length(), line.length() - delText1.length()) + CR_LF);
				}
				
				jta.setText(sb.toString());
			}
		});
		
		btn[n++].addActionListener(new ActionListener(){ // 지정된 문자열을 찾아서 그 위치까지 자르는 기능
			public void actionPerformed(ActionEvent e){
				String curText = jta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				String delText = jtf.getText();
				String delText1 = jtf1.getText();
				
				Scanner scan = new Scanner(curText);
				
				while(scan.hasNextLine()){
					String line = scan.nextLine();
					
					int beginIndex = line.indexOf(delText); // 각 라인에서 삭제하려는 문자열 위치를 찾는다.
					int endIndex = line.indexOf(delText1);
					
					if(beginIndex == -1 || endIndex == -1 || beginIndex > endIndex) // 예외처리
						continue;
					
					sb.append(line.substring(beginIndex + 1, endIndex) + CR_LF);
				}
				
				jta.setText(sb.toString());
			}
		});

		btn[n++].addActionListener(new ActionListener(){ // 중복된 라인 제거하고 정렬해서 보여주는 기능
			public void actionPerformed(ActionEvent e){
				String curText = jta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				Scanner scan = new Scanner(curText);
				HashSet hashset = new HashSet(); // 중복된 요소 제거하기 위해 Collection Hash-Set 생성
				
				while(scan.hasNextLine()){
					String line = scan.nextLine();
					hashset.add(line); // 각 라인을 Hash-Set에 추가
				}
				
				ArrayList arraylist = new ArrayList(hashset); // 각 라인을 정렬하기 위해 Collection ArrayList 생성
				arraylist.sort(null); // 정렬
				
				Iterator it = arraylist.iterator(); // Collection 탐색하기위해 Iterator 생성
				
				while(it.hasNext()){
					sb.append(it.next() + CR_LF); // 각 줄을 StringBuffer에 추가
				}
				
				jta.setText(sb.toString());
			}
		});
	} // registerEventHandler()
	
	public TextToolEx08(String title){
		super(title);
		con = this.getContentPane();

		this.setSize(600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		xpos = (int)(dimen.getWidth() / 2 - dimen1.getWidth() / 2);
		ypos = (int)(dimen.getHeight() / 2 - dimen1.getHeight() / 2);
		
		this.setLocation(xpos, ypos);
		
		lb = new JLabel("param1 : ", JLabel.RIGHT);
		lb1 = new JLabel("param2 : ", JLabel.RIGHT);
		jtf = new JTextField(15);
		jtf1 = new JTextField(15);
		
		jta = new JTextArea();
		pNorth = new JPanel();
		pSouth = new JPanel();
		
		for(int i = 0; i < btn.length; i++){
			btn[i] = new JButton(btnName[i]);
		}
		
		pNorth.setLayout(new FlowLayout());
		pNorth.add(lb);
		pNorth.add(jtf);
		pNorth.add(lb1);
		pNorth.add(jtf1);
		
		pSouth.setLayout(new GridLayout(2, 10));
		
		for(int i = 0; i < btn.length; i++){
			pSouth.add(btn[i]);
		}
		
		con.add(pNorth, "North");
		con.add(jta, "Center");
		con.add(pSouth, "South");
		
		jta.requestFocus();
		registerEventHandler();
		this.setVisible(true);
	} // TextToolEx() Constructor
	
	public static void main(String[] ar){
		new TextToolEx08("Text Tool");
	}
}
