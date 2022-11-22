package com.teachmeskills.interceptor;

import com.teachmeskills.session.AuthContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final AuthContext authContext;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {
        if (authContext.isAuthorized()) {
            return true;
        }
        log.info("ошибка1");
        if (!request.getRequestURI().contains("accessDenied")) {
            response.sendRedirect("accessDenied");
            log.info("ошибка");
        }
        return false;
    }
}
