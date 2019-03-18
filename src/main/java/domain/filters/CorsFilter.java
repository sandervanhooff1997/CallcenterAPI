package domain.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

@Provider
@PreMatching
public class CorsFilter implements ContainerResponseFilter {

//    @Override
//    public void filter(ContainerRequestContext requestContext,
//                       ContainerResponseContext responseContext) throws IOException {
//        responseContext.getHeaders().add(
//                "Access-Control-Allow-Origin", "*");
//        responseContext.getHeaders().add(
//                "Access-Control-Allow-Credentials", "true");
//        responseContext.getHeaders().add(
//                "Access-Control-Allow-Headers",
//                "origin, content-type, accept, authorization");
//        responseContext.getHeaders().add(
//                "Access-Control-Allow-Methods",
//                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//    }

    private final static Logger log = Logger.getLogger( CorsFilter.class.getName() );

    @Override
    public void filter( ContainerRequestContext requestCtx, ContainerResponseContext responseCtx ) throws IOException {
        log.info( "Executing REST response filter" );

        responseCtx.getHeaders().add( "Access-Control-Allow-Origin", "*" );
        responseCtx.getHeaders().add( "Access-Control-Allow-Credentials", "true" );
        responseCtx.getHeaders().add( "Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
    }
}