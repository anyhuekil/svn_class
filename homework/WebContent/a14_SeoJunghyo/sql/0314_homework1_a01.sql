/*
 ����
 �Ʒ��� �������� �����͸� ��Ÿ������.
 1. ename�� ���� ������ @@@�����ε�, �ֱٿ� 10%�λ�� �ݾ��̸� �۳� ������ @@@�����̾����ϴ�.
 2. ename�� �Ի����� hiredate�Դϴ�.
 3. ��å�� @@@�� @@@�� �μ���ȣ�� @@@�Դϴ�.
 4. @@@���� ���� ������ ���ʽ�(comm)�� �ջ���� @@@�Դϴ�. 
 */
-- 1. ename�� ���� ������ @@@�����ε�, �ֱٿ� 10%�λ�� �ݾ��̸� �۳� ������ @@@�����̾����ϴ�.
 	select ename||'�� ���� ������ '||(sal+sal*0.1)||'�����ε�, �ֱٿ� 10%�λ�� �ݾ��̸� �۳� ������ '||sal||'�����̾����ϴ�.' from emp;
-- 2. ename�� �Ի����� hiredate�Դϴ�.
	select ename||'�� �Ի����� '||hiredate||'�Դϴ�.' from emp;
-- 3. ��å�� @@@�� @@@�� �μ���ȣ�� @@@�Դϴ�.
	select '��å�� '||job||'�� '||ename||'�� �μ���ȣ�� '||deptno||'�Դϴ�.' from emp;	
-- 4. @@@���� ���� ������ ���ʽ�(comm)�� �ջ���� @@@�Դϴ�.
	select ename||'���� ���� ������ ���ʽ�('||nvl(comm,0)||')�� �ջ���� '||(sal+nvl(comm,0))||'�Դϴ�.' from emp;	