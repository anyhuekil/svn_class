/*
1)sal�� �����̶�� �� ��, �� �� �޿���(1/13)�� ó���Ͻÿ�, �� �� ���� ���ʽ��� �ִ� ���Դϴ�.
-- ��³��� : ���, ����, �̴ޱ޿�, ���ʽ�, �� �޿���(�޿���, �ѱ޿����� 10������ �ݿø�ó���Ѵ�.)

2) sal�� �������� 
1000�̸�		-10% ���ʽ�
1000-2000�̸�	-20% ���ʽ� 1000 1999
2000-3000�̸� 	-30% ���ʽ�
3000-4000�̸� 	-40% ���ʽ� 
4000-5000�̸� 	-50% ���ʽ�
5000-�̻�	 	-60% ���ʽ���
�����ϱ�� �ߴ�. �Լ��� Ȱ���ؼ� �Ʒ� ������ ó���Ͻÿ�.
��� �̸� ���� ���ʽ�(%) ���ʽ�

3)
����� �μ��������� 1���� 2������ ����� ü����ȸ�� �ϱ�� �ߴ�.
�μ���ȣ�� 10, 30 ==> 1�� ���� �ڸ� Ȧ��
�μ���ȣ�� 20, 40 ==> 2�� ���� �ڸ� ¦��
���� ������ ������ ���� ����ϼ���.
�μ���ȣ �����ȣ �̸� ����.

4) �Լ��� �̿��Ͽ� ���� ������ ����ϼ���
@@@�� �Ի��� @@�� @@�� @@���̸�, ���� ������ @@@ ���� �ް� �ֽ��ϴ�.
column�� empinfo

5)substr�� Ȱ���Ͽ� JOB�� MAN���� ������ �����͸� ����ϼ���!

6) �Ի����� 12���� �����͸� �̸��� �Ի����� ����ϼ��� instr()�� Ȱ��
*/

-- 1)
select empno "���", sal "����", sal/13 "�� �� �޿�", comm "���ʽ�", round((sal/13+nvl(comm,0)),-1) "�� �޿���" from emp;

-- 2)
select empno "���", ename "�̸�", sal "����", trunc(sal,-3)*0.01+10 "���ʽ�(%)", comm "���ʽ�" from emp;

-- 3)
select deptno "�μ���ȣ", empno "�����ȣ", ename "�̸�",  2-mod(deptno/10,2) "����" from emp;

-- 4)
select ename||'���� �Ի��� '||substr(hiredate,1,2)||'��'||substr(hiredate,4,2)||'��'
||substr(hiredate,7,2)||'���̸�, ���� ������ '||sal||' �� �� �ް� �ֽ��ϴ�.' "empinfo" from emp;

-- 5)
select * from emp where substr(job,-3,3)='MAN';

-- 6)
select ename, hiredate from emp where instr(hiredate,'12')=4;

select * from emp;