/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.bean.Employee_Bean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class Dao {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud", "root", "Gmail@91100");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static int Save(Employee_Bean emp) {
        int status = 0;
        try {
            Connection con = Dao.getConnection();
            String query = "insert into employee(name,email,pass) values(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getPass());
            status = ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static List<Employee_Bean> viewAllEmployee() {
        List<Employee_Bean> l = new ArrayList<>();
        try {
            Connection con = Dao.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from employee");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee_Bean e = new Employee_Bean();

                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setEmail(rs.getString(3));
                e.setPass(rs.getString(4));
                l.add(e);
            }

        } catch (Exception e) {
        }
        return l;
    }

    public static int DeleteDetails(int id) {
        int Status = 0;
        try {
            Connection con = Dao.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from employee where id=?");
            ps.setInt(1, id);
            Status = ps.executeUpdate();
        } catch (Exception e) {
        }
        return Status;
    }
    public static Employee_Bean GetDetailsbyId(int id){
        
        Employee_Bean emp=new Employee_Bean();
        try {
            Connection con = Dao.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from employee where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery(); 
            while(rs.next()){
                emp.setId(rs.getInt(1));
                emp.setName(rs.getString(2));
                emp.setEmail(rs.getString(3));
                emp.setPass(rs.getString(4));
            }
            con.close();
        } catch (Exception e) {
        }
        return emp;
    }
    
    public static int update(Employee_Bean emp){
        int status=0;
        try {
             Connection con = Dao.getConnection();
            PreparedStatement ps = con.prepareStatement("update employee set name=?,email=?,pass=? where id=?");
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getPass());
            ps.setInt(4, emp.getId());
            
            status=ps.executeUpdate();
            con.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

}
