package com.dimemtl;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.query.In;
import io.javalin.Javalin;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Javalin app = Javalin.create();
        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration("jdbc:sqlite:C:\\Users\\nurzh\\OneDrive\\Рабочий стол\\Employce.db");
        databaseConfiguration.createTables();
        EmployeeController employeeController = new EmployeeController(DaoManager.createDao(databaseConfiguration.connectionSource(), Employee.class));

        DepartmentController departmentController = new DepartmentController(DaoManager.createDao(databaseConfiguration.connectionSource(), Department.class));

        app.get("/employee", employeeController::list);
        app.get("/employee/:id", ctx -> employeeController.readOne(ctx, Integer.parseInt(ctx.pathParam("id"))));
        app.post("/employee/:id/:firstName/:lastName/:phone/:title/:birthday/:department", employeeController::create);
        app.patch("/employee/:id/:firstName/:lastName/:phone/:title/:birthday/:department", employeeController::update);

        app.delete("/employee/:id", employeeController::delete);

        app.start(8080);






    }
}
