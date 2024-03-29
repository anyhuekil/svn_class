-- 0315 과제
-- sal를 매월 이달 급여분(1/13)을 처리하시오, 단 이달은 보너스가 있는 달
-- 출력내용 : 사번, 연봉, 이달급여, 보너스, 총급여액(급여와 보너스는 10단위로 반올림처리)
select empno, sal, round(sal/13,-1), round(nvl(comm,0),-1), round(sal/13+nvl(comm,0),-1) from emp;

select *from emp;
/*
숙제 sal를 기준으로
1000미만				-10% 보너스
1000~2000미만		-20% 보너스
2000~3000미만		-30% 보너스
3000~4000미만		-40% 보너스
4000~5000미만		-50% 보너스
5000이상				-60% 보너스
함수를 활용해서 아래 내용을 처리하시오
사번 이름 연봉 보너스(%) 보너스금액
*/
select empno, ename, sal, (trunc(sal,-3)+1000)/100||'%', sal*(trunc(sal,-3)+1000)/10000 from emp;



/*
숙제
사원을 부서기준으로 1팀과 2팀으로 나누어서 체육대회를 하기로 했다
부서번호가 10, 30 ==> 1팀
부서번호가 20, 40 ==> 2팀
으로 나누고 다음과 같이 출력
부서번호 사원번호 이름 팀명
*/
select deptno, empno, ename, mod(deptno/10+1,2)+1||'팀' from emp;



-- 과제) 함수를 이용하여 다음 내용을 출력하세요
-- @@@님 입사일 @@년 @@월 @@일 이며, 현재 연봉은 @@@만원 받고 있습니다
select ename||'님 입사일 '||substr(hiredate,1,2)||'년 '||substr(hiredate,4,2)||'월 '||substr(hiredate,7,2)||'일 이며, 현재 연봉은 '||
sal||'만원 받고 있습니다' from emp;




-- 과제) substr을 활용 하여 job이 man으로 끝나는 데이터를 출력하세요
select * from emp where substr(job,length(job)-2,length(job))='MAN';



/* 과제) 입사일이 12월인 데이터의 이름과 입사일을 출력하세요 instr활용*/
select ename, hiredate from emp where instr(hiredate,12,4,1)=4;





