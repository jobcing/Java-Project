import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class TextToolEx02 extends JFrame{
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	private Container con;
	
	private JTextArea jta;
	private JTextField jtf, jtf1;
	private JPanel pNorth, pSouth;
	private JLabel lb, lb1;
	
	private String[] btnName = {"Undo", 
								"짝수 줄 삭제",
								};
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
	} // registerEventHandler()
	
	public TextToolEx02(String title){
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
		new TextToolEx02("Text Tool");
	}
}
