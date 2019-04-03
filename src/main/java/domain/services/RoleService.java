package domain.services;

import domain.models.Auth.Role;
import domain.repositories.RoleRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Local
@Stateless
public class RoleService {

    @EJB
    private RoleRepository repository;

    public Role getById(Long id) {
        return repository.getById(id);
    }
}
