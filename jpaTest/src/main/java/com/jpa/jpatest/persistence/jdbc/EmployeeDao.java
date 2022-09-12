package com.jpa.jpatest.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDao {

    private Connection conn = null;
    private PreparedStatement stmt =  null;
    private ResultSet rs = null;

    //S_EMP 테이블 관련 SQL 구문
     private String INSERT_EMP =
            "inset into s_emp(id, name, start_date, title, dept_name, salary) " +
                    "values((select nvl(max(id), 0)+1 from s_emp), ?, ?, ?, ?, ?)";
     private String LIST_EMP =
             "select id, name, start_date, title, dept_name, salary "+
                     "from s_emp order by name";

     public void insertEmployee(EmployeeVO vo){
         System.out.println("jdbc 기반 직원 등록 처리");
         try{
             conn
         }
     }
}
