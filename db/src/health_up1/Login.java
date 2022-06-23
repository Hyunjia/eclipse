package health_up1;

import java.util.Scanner;

public class Login {
	
	Scanner sc = new Scanner(System.in);
	
	public Login() {
		while (true) {
			DB db = new DB();
			System.out.print("1.ȸ������ 2.�α��� 3.����");
			int s = Integer.parseInt(sc.nextLine());
			if (s == 1) {
				System.out.print("�й�");
				int num = Integer.parseInt(sc.nextLine());
				System.out.print("�̸�:");
				String name = sc.nextLine();
				System.out.print("���̵�:");
				String id = sc.nextLine();
				System.out.print("��й�ȣ:");
				String pwd = sc.nextLine();
				System.out.print("��ȭ��ȣ:");
				String tel = sc.nextLine();
				Member member = new Member(num,name, id, pwd, tel);
				if (db.registerMember(member)) {
					System.out.println("ȸ������ ����.");
				}
				
			} else if (s == 2) {
				System.out.print("���̵�:");
				String id = sc.nextLine();
				System.out.print("��й�ȣ:");
				String pwd = sc.nextLine();
				Member member = new Member();
				member.setId(id);
				member.setPass(pwd);
				if (db.loginMember(member)==0) {
					System.out.println("�α��� ����.");
					
				} else {
					System.out.println("�α��� ����.");
					new Menu(db.loginMember(member));
				}
			}else if (s == 3) {
				System.out.println("���α׷� ����.");
				System.exit(0);
			}
		
		
		JdbcUtil.close(db.getConn());
	}
}
}
