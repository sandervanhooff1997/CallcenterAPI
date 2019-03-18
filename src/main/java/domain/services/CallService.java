package domain.services;

import domain.models.Call;
import domain.repositories.CallRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Local
@Stateless
public class CallService {

    @EJB
    private CallRepository repository;

    public List<Call> getAll() {
        return repository.getAll();
    }

    public Call getById(Long id) {
        return repository.getById(id);
    }

    public boolean create(Call call) {
        if (call == null)
            return false;

        repository.create(call);
        return true;
    }

    public boolean update(Call call) {
        if (call == null)
            return false;

        repository.update(call);
        return true;
    }

    public boolean delete(Long id) {
        Call call = repository.getById(id);

        if (call == null)
            return false;

        repository.delete(call);
        return true;
    }
}
