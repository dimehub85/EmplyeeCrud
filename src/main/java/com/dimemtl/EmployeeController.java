package com.dimemtl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.stmt.query.In;
import com.sun.org.apache.xml.internal.security.keys.content.DEREncodedKeyValue;
import io.javalin.http.Context;

import java.sql.Blob;
import java.sql.SQLException;

public class EmployeeController {

    private final Dao<Employee, Integer> dao;
    private final Dao<Department, Integer> departmentDao;



    public EmployeeController(Dao<Employee, Integer> dao, Dao<Department, Integer> departmentDao) {
        this.dao = dao;
        this.departmentDao = departmentDao;
    }


    public void list(Context ctx) throws SQLException{
        ctx.result(dao.queryForAll().toString());
        ctx.result(departmentDao.queryForAll().toString());
    }

    public void readOne(Context ctx, int id) throws SQLException{
        Employee employee = dao.queryForId(id);
        Department department = departmentDao.queryForId(id);
        if(employee == null){
            ctx.status(404);
        }else{
            ctx.result(employee.toString());
        }
    }

    public void create(Context ctx) throws SQLException{
        int id = ctx.pathParam("id", Integer.class).get();
        String firstName = ctx.pathParam("firstName");
        String lastName = ctx.pathParam("lastName");
        String phone = ctx.pathParam("phone");
        String title = ctx.pathParam("title");
        String birthday = ctx.pathParam("birthday");
        int departmentId = ctx.pathParam("department", Integer.class).get();
        Department department = departmentDao.queryForId(departmentId);
        Employee employee = new Employee(id, firstName, lastName, phone, title, birthday,department);
        dao.create(employee);
    }

    public void update(Context ctx) throws SQLException {
        int id = ctx.pathParam("id", Integer.class).get();
        String firstName = ctx.pathParam("firstName");
        String lastName = ctx.pathParam("lastName");
        String phone = ctx.pathParam("phone");
        String title = ctx.pathParam("title");
        String birthday = ctx.pathParam("birthday");
        int departmentId = ctx.pathParam("department", Integer.class).get();
        Department department = departmentDao.queryForId(departmentId);
        Employee employee = new Employee(id, firstName, lastName, phone, title, birthday, department);
        dao.update(employee);
    }

    public void delete(Context ctx) throws SQLException{
        int id = ctx.pathParam("id", Integer.class).get();
        int departmentId = ctx.pathParam("department", Integer.class).get();
        Department department = departmentDao.queryForId(departmentId);
        dao.deleteById(id);
        departmentDao.deleteById(id);
    }
}
