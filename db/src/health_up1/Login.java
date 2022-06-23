package health_up1;

import java.util.Scanner;

public class Login {
	
	Scanner sc = new Scanner(System.in);
	
	public Login() {
		while (true) {
			DB db = new DB();
			System.out.print("1.회원가입 2.로그인 3.종료");
			int s = Integer.parseInt(sc.nextLine());
			if (s == 1) {
				System.out.print("학번");
				int num = Integer.parseInt(sc.nextLine());
				System.out.print("이름:");
				String name = sc.nextLine();
				System.out.print("아이디:");
				String id = sc.nextLine();
				System.out.print("비밀번호:");
				String pwd = sc.nextLine();
				System.out.print("전화번호:");
				String tel = sc.nextLine();
				Member member = new Member(num,name, id, pwd, tel);
				if (db.registerMember(member)) {
					System.out.println("회원가입 성공.");
				}
				
			} else if (s == 2) {
				System.out.print("아이디:");
				String id = sc.nextLine();
				System.out.print("비밀번호:");
				String pwd = sc.nextLine();
				Member member = new Member();
				member.setId(id);
				member.setPass(pwd);
				if (db.loginMember(member)==0) {
					System.out.println("로그인 실패.");
					
				} else {
					System.out.println("로그인 성공.");
					new Menu(db.loginMember(member));
				}
			}else if (s == 3) {
				System.out.println("프로그램 종료.");
				System.exit(0);
			}
		
		
		JdbcUtil.close(db.getConn());
	}
}
}
