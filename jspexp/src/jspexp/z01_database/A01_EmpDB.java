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
	// field ����
	// 1. Connection con : ���� ��ü
	// 2. Statement stmt : ��ȭ
//	      PreparedStatement pstmt
//	   3. ResultSet rs;  : ���
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// ����ó���� �޼��� : ���ܻ��� ����ó��
	private void setConn() throws ClassNotFoundException, SQLException{
//		1. driver�޸𸮿� �ø���
//		2. DriverManger.getConnection(url, id,pass)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// db����̹�����(thin)@ip:port:sid
			String conInfo="jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(conInfo, "scott", "tiger");
			System.out.println("���� ���� ����!!");
	}
	// emp ���̺��� select * from emp���ؼ� �������� �����͸�
	// ArrayList<Emp>�� ������
	public ArrayList<Emp> empList(){
		ArrayList<Emp> list = new ArrayList<Emp>();
//		1. ����
		try {
			setConn();
//		2. ��ȭ
			stmt=con.createStatement();
//			sqló��
			String sql="SELECT * FROM EMP ";
//			��ȭ�� �ϰ�, �����(ResultSet)
			rs = stmt.executeQuery(sql);
//		3. ���(select==> ResultSet, insert/update/delete ==> DB�� ó��)
//			rs.next() : loop ����{}�� ó���� �Ŀ�, ���� row�� �����Ͱ�
//			            �ִ��� ���θ� return true/false
//			rs.getXXXX("�÷���") �÷���:sql���� ��Ÿ���� title �÷���
//			                     database��  �÷���X
//			1)  ArrayList<Emp>�� �����͸� ���� ������ü(Emp)�� �߰�
//			    Emp(����, �������Ҵ�)  --> ArrayList<Emp> add
			Emp emp=null; // ���� ��ü ����
			// Emp emp �� loop�� �ȿ� �����ϸ� ������ü�� �ݺ���ŭ ���� �޸�
			// ���Ϲ߻�
		    // Emp emp = new Emp(); loop�� �ۿ��� �����ϰ�, loop�� �ȿ� ����
			// ���� ������ ���� ��ü�� �����ϱ�, ������ �����͸� ������ �Ǽ���ŭ
			// �Ҵ�
			while(rs.next()){
				emp = new Emp();
				// emp.setEmpno()��  ������ ������(rs.getInt("empno"))
//				  �Ҵ������� empno �ʵ忡 �����͸� ����
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
			// ����, ����, ���  �� ==> commit()
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// ����,����, ���  rollback()
			e.printStackTrace();
		}finally{
			
			// �ڿ� ����
			try {
				if(rs!=null){
					// ResultSet�� ���� �޸𸮿� �Ҵ� �Ǿ� �ִٸ�.
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
	// �˻� �޼���
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
			Emp emp=null; // ���� ��ü ����
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
			// ����,����, ���  rollback()
			e.printStackTrace();
		}finally{
			// �ڿ� ����
			try {
				if(rs!=null){
					// ResultSet�� ���� �޸𸮿� �Ҵ� �Ǿ� �ִٸ�.
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
	// �˻� �޼���
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
			pstmt.setString(1, sch.getEname()); //ù��°
			pstmt.setString(2, sch.getJob());//�ι�°
			
			rs = pstmt.executeQuery();
			Emp emp=null; // ���� ��ü ����
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
			// ����,����, ���  rollback()
			e.printStackTrace();
		}finally{
			// �ڿ� ����
			try {
				if(rs!=null){
					// ResultSet�� ���� �޸𸮿� �Ҵ� �Ǿ� �ִٸ�.
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
		System.out.println("�˻� ó�� �޼��� ȣ��~~~~");
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