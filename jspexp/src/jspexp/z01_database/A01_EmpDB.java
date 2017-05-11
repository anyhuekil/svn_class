package jspexp.z01_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jspexp.z02_vo.Emp;
// jspexp.z01_database.A01_EmpDB
public class A01_EmpDB {
	// field 선언
	// 1. Connection con : 연결 객체
	// 2. Statement stmt : 대화
//	      PreparedStatement pstmt
//	   3. ResultSet rs;  : 결과
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 연결처리한 메서드 : 예외사항 위임처리
	private void setConn() throws ClassNotFoundException, SQLException{
//		1. driver메모리에 올리기
//		2. DriverManger.getConnection(url, id,pass)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// db드라이버종류(thin)@ip:port:sid
			String conInfo="jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(conInfo, "scott", "tiger");
			System.out.println("정상 접속 성공!!");
	}
	// emp 테이블에 select * from emp통해서 가져오는 데이터를
	// ArrayList<Emp>로 가져옮
	public ArrayList<Emp> empList(){
		ArrayList<Emp> list = new ArrayList<Emp>();
//		1. 연결
		try {
			setConn();
//		2. 대화
			stmt=con.createStatement();
//			sql처리
			String sql="SELECT * FROM EMP ";
//			대화를 하고, 결과값(ResultSet)
			rs = stmt.executeQuery(sql);
//		3. 결과(select==> ResultSet, insert/update/delete ==> DB내 처리)
//			rs.next() : loop 블럭{}을 처리한 후에, 다음 row에 데이터가
//			            있는지 여부를 return true/false
//			rs.getXXXX("컬럼명") 컬럼명:sql문에 나타나는 title 컬럼명
//			                     database에  컬럼명X
//			1)  ArrayList<Emp>에 데이터를 담은 단위객체(Emp)를 추가
//			    Emp(생성, 데이터할당)  --> ArrayList<Emp> add
			Emp emp=null; // 단위 객체 선언
			// Emp emp 를 loop문 안에 선언하면 참조객체가 반복만큼 생겨 메모리
			// 부하발생
		    // Emp emp = new Emp(); loop문 밖에서 생성하고, loop문 안에 생성
			// 하지 않으면 같은 객체를 참조하기, 마지막 데이터를 데이터 건수만큼
			// 할당
			while(rs.next()){
				emp = new Emp();
				// emp.setEmpno()에  가져온 데이터(rs.getInt("empno"))
//				  할당함으로 empno 필드에 데이터를 저장
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSal(rs.getDouble("sal"));
				emp.setComm(rs.getDouble("comm"));
				emp.setDeptno(rs.getInt("deptno"));
				list.add(emp);		
			}
			// 수정, 삭제, 등록  시 ==> commit()
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// 수정,삭제, 등록  rollback()
			e.printStackTrace();
		}finally{
			
			// 자원 해제
			try {
				if(rs!=null){
					// ResultSet가 현재 메모리에 할당 되어 있다면.
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}	
				if(con!=null){
					con.close();
				}				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	// 검색 메서드
	public ArrayList<Emp> search(Emp sch){
		ArrayList<Emp> list = new ArrayList<Emp>();
		try {
			setConn();
			stmt=con.createStatement();
			String sql="SELECT * \n"
					+ "FROM EMP  \n"
					+ "WHERE ENAME LIKE '%"+sch.getEname()+"%'  \n"
					+ "AND JOB LIKE '%"+sch.getJob()+"%' ";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			Emp emp=null; // 단위 객체 선언
			while(rs.next()){
				emp = new Emp();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSal(rs.getDouble("sal"));
				emp.setComm(rs.getDouble("comm"));
				emp.setDeptno(rs.getInt("deptno"));
				list.add(emp);		
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// 수정,삭제, 등록  rollback()
			e.printStackTrace();
		}finally{
			// 자원 해제
			try {
				if(rs!=null){
					// ResultSet가 현재 메모리에 할당 되어 있다면.
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}	
				if(con!=null){
					con.close();
				}				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	// 검색 메서드
	public ArrayList<Emp> searchPre(Emp sch){
		ArrayList<Emp> list = new ArrayList<Emp>();
		try {
			setConn();

			String sql="SELECT * \n"
					+ "FROM EMP  \n"
					+ "WHERE ENAME LIKE '%' || ? || '%'  \n"
					+ "AND JOB LIKE '%'||?||'%' ";
			System.out.println(sql);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, sch.getEname()); //첫번째
			pstmt.setString(2, sch.getJob());//두번째
			
			rs = pstmt.executeQuery();
			Emp emp=null; // 단위 객체 선언
			while(rs.next()){
				emp = new Emp();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSal(rs.getDouble("sal"));
				emp.setComm(rs.getDouble("comm"));
				emp.setDeptno(rs.getInt("deptno"));
				list.add(emp);		
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// 수정,삭제, 등록  rollback()
			e.printStackTrace();
		}finally{
			// 자원 해제
			try {
				if(rs!=null){
					// ResultSet가 현재 메모리에 할당 되어 있다면.
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}	
				if(con!=null){
					con.close();
				}				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A01_EmpDB db = new A01_EmpDB();
		System.out.println(db.empList().size());
		for(Emp emp:db.empList()){
			System.out.print(emp.getEmpno()+"\t");
			System.out.print(emp.getEname()+"\t");
			System.out.print(emp.getMgr()+"\t");
			System.out.print(emp.getJob()+"\t");
			System.out.print(emp.getHiredate()+"\t");
			System.out.print(emp.getSal()+"\t");
			System.out.print(emp.getComm()+"\t");
			System.out.print(emp.getDeptno()+"\n");
		}
		Emp sch = new Emp();
		sch.setEname("S");
		System.out.println("검색 처리 메서드 호출~~~~");
		for(Emp emp:db.search(sch)){
			System.out.print(emp.getEmpno()+"\t");
			System.out.print(emp.getEname()+"\t");
			System.out.print(emp.getMgr()+"\t");
			System.out.print(emp.getJob()+"\t");
			System.out.print(emp.getHiredate()+"\t");
			System.out.print(emp.getSal()+"\t");
			System.out.print(emp.getComm()+"\t");
			System.out.print(emp.getDeptno()+"\n");
		}		
		
	}

}
