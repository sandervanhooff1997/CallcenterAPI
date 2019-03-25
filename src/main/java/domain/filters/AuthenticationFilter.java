package domain.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import domain.services.AuthService;
import domain.services.JWTService;

import javax.ejb.EJB;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Provider
@PreMatching
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String REALM = "example";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @EJB
    private JWTService service;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // ignore /auth requests
        UriInfo info = requestContext.getUriInfo();
        if (info.getPath().contains("auth"))
            return;
<<<<<<< HEAD
//
//        // Get the Authorization header from the request
//        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//
//        // Validate the Authorization header
//        if (!isTokenBasedAuthentication(authorizationHeader)) {
//            abortWithUnauthorized(requestContext);
//            return;
//        }
//
//        // Extract the token from the Authorization header
//        String token = authorizationHeader
//                .substring(AUTHENTICATION_SCHEME.length()).trim();
//
//        try {
//            // Validate the token
//            service.verifyJWT(token);
//        } catch (Exception e) {
//            abortWithUnauthorized(requestContext);
//        }
=======
        
        // Get the Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        // Extract the token from the Authorization header
        String token = authorizationHeader
                .substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            // Validate the token
            service.verifyJWT(token);
        } catch (Exception e) {
            abortWithUnauthorized(requestContext);
        }
>>>>>>> parent of e5769dc... Interfacing
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {
        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
//                        .header(HttpHeaders.WWW_AUTHENTICATE,
//                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
    }

//    private void validateToken(String token) throws Exception {
//        // Check if the token was issued by the server and if it's not expired
//        // Throw an Exception if the token is invalid
//    }
}
