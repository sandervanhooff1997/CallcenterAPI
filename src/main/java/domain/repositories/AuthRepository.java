package domain.repositories;

import domain.models.Auth.Code;
import domain.models.Auth.Role;
import org.hibernate.HibernateException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Local
@Stateless
public class AuthRepository {
    @PersistenceContext(unitName = "callcenterPU")
    private EntityManager em;

    public List<Role> getAllRoles() {
        try {
            return em.createQuery("SELECT g FROM Role g", Role.class).getResultList();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Role getRoleById(Long id) {
        return em.createQuery("SELECT e FROM Role e where e.id = :id", Role.class).setParameter("id", id).getSingleResult();
    }

    public void createRole(Role role) {
        em.persist(role);
    }

    public void updateRole(Role role) {
        em.merge(role);
    }

    public void deleteRole(Role role) {
        em.remove(role);
    }

    public void createCode(Code code) {em.persist(code);}

    public Code getCodeUnverified(Code code) {
        try {
            return em.createQuery(
                    "SELECT e FROM Code e " +
                            "where " +
                            "e.code = :code " +
                            "AND e.employee.email = :email " +
                            "AND e.verified = false ", Code.class)
                    .setParameter("code", code.getCode())
                    .setParameter("email", code.getEmployee().getEmail())
                    .getSingleResult();
        } catch (Exception e){
            return null;
        }

    }
    public Code getCodeVerified(Code code) {
        try {
            return em.createQuery(
                    "SELECT e FROM Code e " +
                            "where " +
                            "e.employee.email = :email " +
                            "AND e.verified = true " +
                            "AND e.fingerPrint = :fingerPrint", Code.class)
                    .setParameter("email", code.getEmployee().getEmail())
                    .setParameter("fingerPrint", code.getFingerPrint())
                    .getSingleResult();
        } catch (Exception e){
            return null;
        }

    }
}
