/*
���� sal�� ��������
 1000�̸�			-10% ���ʽ�
 1000~2000�̸�	-20% ���ʽ�
 2000~3000�̸�	-30% ���ʽ�
 3000~4000�̸� 	-40%���ʽ�
 4000~5000�̸�     -50%���ʽ�
 5000~�̻�		-60%���ʽ�
 �����ϱ�� �ߴ�. �Լ��� Ȱ���ؼ� �Ʒ� ������ ó���Ͻÿ�.
 ����̸� ���� ���ʽ�(%) ���ʽ�
 */
 
select empno, sal, round((sal+500)/100,-1),(sal+round((sal+500)/100,-1)) from emp;
select * from emp;
  /*
 ���� 
 ����� �μ��������� 0���� 1������ ����� ü����ȸ�� ����� �Ѵ�. 
 �μ���ȣ�� 10, 30 ==> 1��
 �μ���ȣ�� 20, 40 ==> 2��
 ���� ������ ������ ���� ����ϼ���. 
 �μ���ȣ  ����� ���  ����  
 */
select deptno, ename, empno, mod(-deptno/10,2)+2 from emp;

-- ����) �Լ��� �̿��Ͽ� ���� ������ ����ϼ���..
--@@@�� �Ի��� @@�� @@�� @@�� �̸�, ���� ������ @@@���� �ް� �ֽ��ϴ�. 
select ename||'���Ի���'||substr(hiredate,1,2)||'��'||substr(hiredate,4,2)||'��'||
substr(hiredate,7,2)||'�� �̸�, ���� ������'||sal||'���� �ް� �ֽ��ϴ�.' from emp;
--����) substr�� Ȱ���Ͽ� job�� man���� ������ �����͸� ����ϼ���
select * from emp
where substr(job,6,3)='MAN'; 
/*����
�Ի����� 12���� �����͸� �̸��� �Ի����� ����ϼ��� instr�� Ȱ��*/
select ename, hiredate from emp
where instr(hiredate,'12',1,1)=4;