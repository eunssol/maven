package chap02;

import java.util.HashMap;
import java.util.Map;

public class MemberDao { //db를 안 쓰고 map으로 쓴대
	private static long nextId = 0; //공유해야해서 static으로 지정
	private Map<String, Member> map = new HashMap<String, Member>(); //맵에 member가 들어있음
	//<이메일,멤버객체>
	//<이메일,멤버객체>
	//<이메일,멤버객체>
	//이렇게 map에 담겨져있어
	
	public Member selectByEmail(String email) {//이메일로 조회할 거임 이메일로 꺼내옴
		return map.get(email); //Member타입이 리턴되니까 오류 안 남
		
	}
	
	public void insert(Member member){//디비로 따지면 insert해주는 거임 <맵에 등록>
		member.setId(++nextId); //1을 먼저 더하는 거임 전위연산자 더하고나서 실행돼 ! 아이디 겹치면 안 되니까 ++ 해줌
		map.put(member.getEmail(), member); // 
	}
	
	public void update(Member member) {
		map.put(member.getEmail(), member); //멤버객체 안에 있는 아이디는 그대로 두고 값을 업데이트 할 거야 그래서 nextId 뭐 안 했음
		
	}
	
	//목록
	public Map<String, Member> selectList() { //데이터를 리턴. 매개변수 필요 없음
		return map;
		
		
	}
	
}
