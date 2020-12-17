package com.dimemtl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.ForeignCollectionField;
import io.javalin.http.Context;

import java.sql.SQLException;
public class DepartmentController {
    private final Dao<Department, Integer> dao;




    public DepartmentController(Dao<Department, Integer> dao) {
        this.dao = dao;
    }

    public void list(Context ctx) throws SQLException{
        ctx.result(dao.queryForAll().toString());
    }

    public void readOne(Context ctx, int id) throws SQLException{
        Department department = dao.queryForId(id);
        if(department == null){
            ctx.status(404);
        } else{
            ctx.result(department.toString());
        }
    }

    public void create(Context ctx) throws SQLException{
        int id = ctx.pathParam("department_id", Integer.class).get();
        String name = ctx.pathParam("name");
        Department department = new Department(id, name);
       dao.create(department);
    }



}
