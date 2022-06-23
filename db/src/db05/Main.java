package db05;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("1. 회원가입 2. 로그인 3.회원정보 수정 4. 회원정보 삭제 0. 종료 ");
			int s = Integer.parseInt(sc.nextLine());
			Member mem = new Member();
			jdbcUtil ju = new jdbcUtil();

			if (s == 1) {
				System.out.print("이름 입력");
				String name = sc.nextLine();

				System.out.print("아이디 입력");
				String id = sc.nextLine();

				System.out.print("비밀번호 입력");
				String pass = sc.nextLine();

				System.out.print("이메일 입력");
				String email = sc.nextLine();

				mem = new Member(name, id, pass, email);

				if (ju.Join(mem)) {
					System.out.println("회원가입이 완료되었습니다.");
				} else {
					System.out.println("회원가입이 되지않았습니다.");
				}

			} else if (s == 2) { // 일정
				System.out.println("아이디를 입력하세요");
				String id = sc.nextLine();
				System.out.println("비밀번호를 입력하세요");
				String pass = sc.nextLine();

				mem.setId(id);
				mem.setPass(pass);

				if (ju.login(mem)) {
					System.out.println("로그인 되었습니다. ");
					
					while (true) {

						System.out.println("1. 일정 등록 2. 일정 수정  3.일정 조회 4. 일정 삭제 5. 로그아웃");
						int n = Integer.parseInt(sc.nextLine());

						if (n == 1) {
							System.out.println("날짜를 입력하세요");
							System.out.println("(yyyymmdd 형태로 입력하세요)");
							String date = sc.nextLine();
							System.out.println("일정을 입력하세요");
							String plan = sc.nextLine();

							mem.setDate(date);
							mem.setPlan(plan);

							if (ju.dateInsert(mem)) {
								System.out.println("일정이 등록되었습니다.");
								ju.reset();
							} else {
								System.out.println("일정이 등록되지않았습니다.");
							}

						} else if (n == 2) {
							ju.View(mem); // 출력 먼저
							System.out.println("무엇을 수정하시겠습니까?");
							
							System.out.println("1.날짜 2.일정");
							int n2 = Integer.parseInt(sc.nextLine());

							if (n2 == 1) {
								System.out.println("본인 해당번호를 입력하세요");
								int num = Integer.parseInt(sc.nextLine());
								mem.setNum(num);

								System.out.println("변경하고 싶은 날짜를 입력하세요");
								String date = sc.nextLine();
								mem.setDate(date);

								if (ju.updateDate(mem)) {
									System.out.println("날짜가 변경되었습니다.");
								} else {
									System.out.println("날짜 변경 실패 ");
								}

							} else if (n2 == 2) {
								System.out.println("본인 해당번호를 입력하세요");
								int num = Integer.parseInt(sc.nextLine());
								mem.setNum(num);

								System.out.println("변경하고 싶은 일정을 입력하세요");
								String plan = sc.nextLine();
								mem.setPlan(plan);

								if (ju.updatePlan(mem)) {
									System.out.println("일정이 변경되었습니다.");

								} else {
									System.out.println("날짜 변경 실패");
								}
							}

						} else if (n == 3) {

							ju.View(mem);

						} else if (n == 4) {
							ju.View(mem);
							
							System.out.println("삭제하실 본인 해당번호를 입력하십시요");
							int num = Integer.parseInt(sc.nextLine());
							mem.setNum(num);

							if (ju.deleteDate(mem)) {
								System.out.println("삭제되었습니다.");
							} else {
								System.out.println("삭제 실패");
							}

						} else if (n == 5) {
							System.out.println("로그아웃");
							break;
							
						}

					}
				} else {
					System.out.println("로그인에 실패했습니다.");
				}

			} else if (s == 3) {
				System.out.println("본인의 아이디는?");
				String id = sc.nextLine();
				mem.setId(id);

				System.out.println("무엇을 수정하시겠습니까?");
				System.out.println("1.이름 2. 비밀번호 3.이메일");
				int s2 = Integer.parseInt(sc.nextLine());

				if (s2 == 1) {
					System.out.println("변경하고 싶은 이름은 무엇입니까?");
					String name = sc.nextLine();
					mem.setName(name);

					if (ju.UpdateName(mem)) {
						System.out.println("이름이 변경되었습니다.");
					} else {
						System.out.println("이름이 변경되지않았습니다.");
					}

				} else if (s2 == 2) {
					System.out.println("변경하고 싶은 비밀번호는 무엇입니까?");
					String pass = sc.nextLine();
					mem.setPass(pass);

					if (ju.UpdatePass(mem)) {
						System.out.println("비밀번호가 변경되었습니다.");

					} else {
						System.out.println("비밀번호가 변경되지않았습니다.");
					}

				} else if (s2 == 3) {
					System.out.println("변경하고 싶은 이메일은 무엇입니까?");
					String email = sc.nextLine();
					mem.setEmail(email);

					if (ju.UpdateEmail(mem)) {
						System.out.println("이메일이 변경되었습니다.");

					} else {
						System.out.println("이메일이 변경되지않았습니다.");
					}

				}

			} else if (s == 4) {
				System.out.println("삭제하고 싶은 아이디는 무엇입니까?");
				String id = sc.nextLine();

				mem.setId(id);
				
				if (ju.deleteStatament(mem)) {
					System.out.println("삭제가 되었습니다.");
					ju.reset();
				} else {
					System.out.println("삭제가 되지 않았습니다.");
				}

			}

			else if (s == 0) {
				System.out.println("시스템이 종료되었습니다.");
				break;
			} else {
				System.out.println("번호를 잘못 입력하셨습니다");
			}

		}

	}

}
