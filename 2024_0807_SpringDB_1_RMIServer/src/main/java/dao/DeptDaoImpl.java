package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.DeptVo;

public class DeptDaoImpl implements DeptDao {

	//SqlSessionTemplate interface를 Constructor Injection으로 연결
	SqlSession sqlSession;

	// Constructor Injection
	public DeptDaoImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}
	
	
	public List<DeptVo> selectList(){
		
		//1.SqlSession sqlSession = factory.openSession();
		//2.list = sqlSession.selectList("dept.dept_list");
		//3.sqlSession.Close();
		
		return sqlSession.selectList("dept.dept_list");
	}
	
	
}
