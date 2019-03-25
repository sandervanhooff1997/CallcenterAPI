package domain.controllers;

import domain.interceptors.CallcenterInterceptor;
import domain.services.IService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.MappedSuperclass;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Local
@Stateless
@Interceptors(CallcenterInterceptor.class)
public abstract class CRUDController<T> implements IController<T> {
    protected Logger logger = Logger.getLogger(this.getClass().getName());

    protected IService<T> service;

    @EJB
    public void setService(IService<T> service) {
        this.service = service;
    }

    public Response getAll() {
        try {
            return Response.ok(service.getAll()).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response getById(Long id) {
        try {
            T e = service.getById(id);

            if (e == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            return Response.ok(e).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    public Response save(T entity) {
        try {
            service.save(entity);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response update(Long id, T entity) {
        try {
            T e = service.getById(id);

            if (e == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            service.update(entity);

            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            logger.severe(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    public Response delete(Long id) {
        try {
            service.delete(id);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
