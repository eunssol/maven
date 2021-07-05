package chap07;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository //DAO는 DB에서 데이터에 엑세스하기 위해서 mybatis를 사용하고 있으니까 mybatis를 사용..ㅅㅂ.
public class MemberDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate; //@Autowired : 타입이 같은 것을 찾아서 알아서 대입해줌
	
	public List<MemberVo> selectList() {
		return sqlSessionTemplate.selectList("member.selectList"); // 서비스에서 호출해..
		//첫번째는 xml에 있는 sql이 있는 위치(네임스페이스.아이디==member.selectList), 두번째는 파라미터값
	}
	
}
