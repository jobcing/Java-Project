import java.util.*;

public class QuizEx {
	public static void main(String[] ar){
		String[] data = {"다음 중 키워드가 아닌 것은?`2`final`True`if`public",
						 "다음 중 자바의 연산자가 아닌 것은?`6`&`|`++`!=`/`^",
						 "다음 중 메서드의 반환값이 없음을 의미하는 키워드는?`1`void`null`false`"
		};
		
		Scanner scan = new Scanner(System.in);
		int score = 0;
		
		shuffle(data); // 문제를 섞는다.
		
		for(int i = 0; i < data.length; i++){
			String[] tmp = data[i].split("`", 3); // '`' 기준으로 3번까지 나눈다.
			
			String question = tmp[0];
			String answer = tmp[1];
			String[] choices = tmp[2].split("`");
			
			answer = choices[Integer.parseInt(answer) - 1]; // 정답 문자열을 저장
			
			shuffle(choices); // 선택지를 섞는다.
			
			System.out.println("[" + (i + 1) + "]" + question);
			
			for(int j = 0; j < choices.length; j++){
				System.out.print((j + 1) + "." + choices[j] + " ");
			}
			
			System.out.println();
			System.out.println("[답]");
			String input = scan.nextLine();
			
			if(input.equals(answer))
				score++;
			
			System.out.println();
			System.out.println();
		}
		System.out.println("정답개수/전체문항수 : " + score + "/" + data.length);
	} // main
	
	public static void shuffle(String[] data){
		for(int i = 0; i < data.length * 2; i++){
			int idx = (int)(Math.random() * data.length);
			
			String tmp = data[0];
			data[0] = data[idx];
			data[idx] = tmp;
		}
	}
}
