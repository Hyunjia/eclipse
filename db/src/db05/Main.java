package db05;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("1. ȸ������ 2. �α��� 3.ȸ������ ���� 4. ȸ������ ���� 0. ���� ");
			int s = Integer.parseInt(sc.nextLine());
			Member mem = new Member();
			jdbcUtil ju = new jdbcUtil();

			if (s == 1) {
				System.out.print("�̸� �Է�");
				String name = sc.nextLine();

				System.out.print("���̵� �Է�");
				String id = sc.nextLine();

				System.out.print("��й�ȣ �Է�");
				String pass = sc.nextLine();

				System.out.print("�̸��� �Է�");
				String email = sc.nextLine();

				mem = new Member(name, id, pass, email);

				if (ju.Join(mem)) {
					System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
				} else {
					System.out.println("ȸ�������� �����ʾҽ��ϴ�.");
				}

			} else if (s == 2) { // ����
				System.out.println("���̵� �Է��ϼ���");
				String id = sc.nextLine();
				System.out.println("��й�ȣ�� �Է��ϼ���");
				String pass = sc.nextLine();

				mem.setId(id);
				mem.setPass(pass);

				if (ju.login(mem)) {
					System.out.println("�α��� �Ǿ����ϴ�. ");
					
					while (true) {

						System.out.println("1. ���� ��� 2. ���� ����  3.���� ��ȸ 4. ���� ���� 5. �α׾ƿ�");
						int n = Integer.parseInt(sc.nextLine());

						if (n == 1) {
							System.out.println("��¥�� �Է��ϼ���");
							System.out.println("(yyyymmdd ���·� �Է��ϼ���)");
							String date = sc.nextLine();
							System.out.println("������ �Է��ϼ���");
							String plan = sc.nextLine();

							mem.setDate(date);
							mem.setPlan(plan);

							if (ju.dateInsert(mem)) {
								System.out.println("������ ��ϵǾ����ϴ�.");
								ju.reset();
							} else {
								System.out.println("������ ��ϵ����ʾҽ��ϴ�.");
							}

						} else if (n == 2) {
							ju.View(mem); // ��� ����
							System.out.println("������ �����Ͻðڽ��ϱ�?");
							
							System.out.println("1.��¥ 2.����");
							int n2 = Integer.parseInt(sc.nextLine());

							if (n2 == 1) {
								System.out.println("���� �ش��ȣ�� �Է��ϼ���");
								int num = Integer.parseInt(sc.nextLine());
								mem.setNum(num);

								System.out.println("�����ϰ� ���� ��¥�� �Է��ϼ���");
								String date = sc.nextLine();
								mem.setDate(date);

								if (ju.updateDate(mem)) {
									System.out.println("��¥�� ����Ǿ����ϴ�.");
								} else {
									System.out.println("��¥ ���� ���� ");
								}

							} else if (n2 == 2) {
								System.out.println("���� �ش��ȣ�� �Է��ϼ���");
								int num = Integer.parseInt(sc.nextLine());
								mem.setNum(num);

								System.out.println("�����ϰ� ���� ������ �Է��ϼ���");
								String plan = sc.nextLine();
								mem.setPlan(plan);

								if (ju.updatePlan(mem)) {
									System.out.println("������ ����Ǿ����ϴ�.");

								} else {
									System.out.println("��¥ ���� ����");
								}
							}

						} else if (n == 3) {

							ju.View(mem);

						} else if (n == 4) {
							ju.View(mem);
							
							System.out.println("�����Ͻ� ���� �ش��ȣ�� �Է��Ͻʽÿ�");
							int num = Integer.parseInt(sc.nextLine());
							mem.setNum(num);

							if (ju.deleteDate(mem)) {
								System.out.println("�����Ǿ����ϴ�.");
							} else {
								System.out.println("���� ����");
							}

						} else if (n == 5) {
							System.out.println("�α׾ƿ�");
							break;
							
						}

					}
				} else {
					System.out.println("�α��ο� �����߽��ϴ�.");
				}

			} else if (s == 3) {
				System.out.println("������ ���̵��?");
				String id = sc.nextLine();
				mem.setId(id);

				System.out.println("������ �����Ͻðڽ��ϱ�?");
				System.out.println("1.�̸� 2. ��й�ȣ 3.�̸���");
				int s2 = Integer.parseInt(sc.nextLine());

				if (s2 == 1) {
					System.out.println("�����ϰ� ���� �̸��� �����Դϱ�?");
					String name = sc.nextLine();
					mem.setName(name);

					if (ju.UpdateName(mem)) {
						System.out.println("�̸��� ����Ǿ����ϴ�.");
					} else {
						System.out.println("�̸��� ��������ʾҽ��ϴ�.");
					}

				} else if (s2 == 2) {
					System.out.println("�����ϰ� ���� ��й�ȣ�� �����Դϱ�?");
					String pass = sc.nextLine();
					mem.setPass(pass);

					if (ju.UpdatePass(mem)) {
						System.out.println("��й�ȣ�� ����Ǿ����ϴ�.");

					} else {
						System.out.println("��й�ȣ�� ��������ʾҽ��ϴ�.");
					}

				} else if (s2 == 3) {
					System.out.println("�����ϰ� ���� �̸����� �����Դϱ�?");
					String email = sc.nextLine();
					mem.setEmail(email);

					if (ju.UpdateEmail(mem)) {
						System.out.println("�̸����� ����Ǿ����ϴ�.");

					} else {
						System.out.println("�̸����� ��������ʾҽ��ϴ�.");
					}

				}

			} else if (s == 4) {
				System.out.println("�����ϰ� ���� ���̵�� �����Դϱ�?");
				String id = sc.nextLine();

				mem.setId(id);
				
				if (ju.deleteStatament(mem)) {
					System.out.println("������ �Ǿ����ϴ�.");
					ju.reset();
				} else {
					System.out.println("������ ���� �ʾҽ��ϴ�.");
				}

			}

			else if (s == 0) {
				System.out.println("�ý����� ����Ǿ����ϴ�.");
				break;
			} else {
				System.out.println("��ȣ�� �߸� �Է��ϼ̽��ϴ�");
			}

		}

	}

}
