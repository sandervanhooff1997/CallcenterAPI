package domain.services;

import domain.models.Auth.Employee;
import domain.models.Auth.Role;
import domain.models.Call;
import domain.repositories.AuthorisationRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.List;
import java.util.logging.Logger;

@Local
@Stateless
@Default
public class AuthorizationService {
    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @EJB
    EmployeeService employeeService;

    @EJB
    RoleService roleService;

    @EJB
    AuthorisationRepository repository;

    public boolean create (Role role) {
        if (role.getName() == null)
            return false;

        repository.create(role);

        return true;
    }

    public Role getById(Long id) {
        return repository.getById(id);
    }

    public List<Role> getAll() {
        return repository.getAll();
    }


    public boolean update(Role role) {
        if (role == null)
            return false;

        repository.update(role);
        return true;
    }

    public boolean delete(Long id) {
        Role role = repository.getById(id);

        if (role == null)
            return false;

        repository.delete(role);
        return true;
    }

    public boolean assignRole(Long roleId, Long employeeId) {
        Role r = roleService.getById(roleId);
        if (r == null)
            return false;

        Employee e = employeeService.getById(employeeId);
        if (e == null)
            return false;

        if (!e.getRoles().contains(r))
            e.getRoles().add(r);

        return true;
    }
}
