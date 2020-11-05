/*
�ڹٿ��� �����ͺ��̽� �����ϴ� ����� ������ JDBC(Java DataBase Connectivity)
��� �ϸ� java.sql ��Ű������ api�� �����ȴ�..

�����ͺ��̽��� �����ϴ� ������ ����
1) DB������ �˸´� ����̹��� �ε� (oracle��, mysql��, mssql��.. ���� jar�� �ʿ�)
2) ����
3) ���ϴ� ���� ����
4) �������� (Ư��, ��Ʈ�� �� DB�� ��� �� �ݵ�� ��������!!!)
*/
package day1105.db;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class  OracleTest{
	public static void main(String[] args){
		Connection con=null; //�޼����� ���������̱� ������ �ʱ�ȭ�� �ʿ�
		PreparedStatement pstmt=null;

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");//����ϰ����ϴ� ����̹� Ŭ������ ���! (Ŭ���� �ε�)
			System.out.println("Ŭ���� �ε� ����~");
			//���� ��� ���� OS �÷����� Ŭ���� �н��� ��ϵǾ� �־�� �Ѵ�...

			//DB������ url, id, password
							//jdbc:����:thin���:ip�ּ�:��Ʈ��ȣ:����� DBMS��ġ��
			String url="jdbc:oracle:thin:@localhost:1521:XE"; //������ ��Ģ
			String user="user1104";
			String password="user1104";
			
			//���� �õ� ��, ���� ���� �� Connection �̶�� �������̽��� �ν��Ͻ��� ��ȯ����!
			//Connection���� ������ ���� ������ ����ֳ�!!
			//���� ���� ���δ� Connection ��ü�� �ο��η� �Ǵ��Ѵ�!!
			con=DriverManager.getConnection(url, user, password); //���ӽõ�!!
			if(con==null){
				System.out.println("���� ����");
			}else{
				System.out.println("���� ����");
				//���ӿ� ����������, �������� ������ �� �ִ�.
				//�������� �����ϴ� ��ü�� PreparedStatement �������̽��̴�.
				String sql = "insert into member(member_id, name, age, phone) ";
				sql += "values(seq_member.nextval, 'Lee', 25, '010')";

				//������ �����Ǿ��, �������� ������ �� �����Ƿ�, Connection��ü�κ��� �ν��Ͻ��� ���� �Ѵ�!!
				pstmt= con.prepareStatement(sql);//���������� �غ�!
				
				//���� ���� �� �������θ� �Ǵ��ϱ� ���ؼ��� executeUpdate()�޼����� ��ȯ���� �̿��Ѵ�.
				//��ȯ���� int���̸�, ������ ���࿡ ���� �ݿ��� ���ڵ��� ���� ��ȯ�Ѵ�..
				//���� insert�� ��� ��ȯ���� 1, update, delete�� ����� ����޾Ҵ��� �� ���� ����.. ���ǿ� ���� �ٸ�
				//�׷�����, insert, update, delete ��� 0�̶�� ���ж� ����
				int result = pstmt.executeUpdate();//DML(insert, update, delete) ���� ���� �� �� �޼���(executeUpdate())�� �����
				if(result!=1){
					System.out.println("insert ����");
				}else{
					System.out.println("insert ����");
				}
			}
		}catch(ClassNotFoundException e){
			System.out.println("������ ����̹��� ã�� �� �����ϴ�.");
			e.printStackTrace();
		}catch(SQLException e){
			System.out.println("���� ����");
		}finally{
			//db ������ ���Ǿ��� ��� ��ü�� �ݾƾ��Ѵ�...
			try{
				if(pstmt!=null){
					pstmt.close();
				}
				if(con!=null){
					con.close();
				}
				System.out.println("�������� ����");
			}catch(SQLException e){
				System.out.println("�������� ����");
				e.printStackTrace();
			}

		}
	}
}
