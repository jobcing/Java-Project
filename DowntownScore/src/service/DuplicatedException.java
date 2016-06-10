// 회원가입을 처리할 때 동일한 아이디를 갖는 데이터가 이미 존재하면 익셉셥을 발생할 것이다.
// 이때 사용할 클래스

package service;

public class DuplicatedException extends RuntimeException {

}
