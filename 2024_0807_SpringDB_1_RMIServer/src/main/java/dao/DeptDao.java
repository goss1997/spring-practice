package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.DeptVo;

public interface DeptDao {
	
	List<DeptVo> selectList();
	
}
