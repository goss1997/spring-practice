package vo;

import java.io.Serializable;

// 객체 직렬화 : 네트워크를 통해서 데이터를 전달할 목적
public class DeptVo implements Serializable {

	int    deptno;
	String dname;
	String loc;
	
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
	
}
