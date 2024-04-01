package wise.wisewomenhackathon.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());

        //@Override
        //    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authenticationException) throws IOException {
        //
        //        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //
        //        final Map<String, Object> body = new HashMap<>();
        //        body.put("code", HttpServletResponse.SC_UNAUTHORIZED);
        //        body.put("payload", "You need to login first in order to perform this action.");
        //
        //        final ObjectMapper mapper = new ObjectMapper();
        //        mapper.writeValue(httpServletResponse.getOutputStream(), body);

    }

}
