import domain.Auth.AuthController;
import domain.controllers.*;

import javax.ws.rs.core.Application;
import java.util.Set;

@javax.ws.rs.ApplicationPath("")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> controllers = new java.util.HashSet<>();

        controllers.add(CallController.class);
        controllers.add(CompanyController.class);
        controllers.add(CustomerController.class);
        controllers.add(EmployeeController.class);
        controllers.add(ProductController.class);
        controllers.add(SubscriptionController.class);
        controllers.add(AuthController.class);
//        controllers.add(CORSFilter.class);

        return controllers;
    }
}