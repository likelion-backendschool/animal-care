package com.codelion.animalcare.domain.appointment.interceptor;

import com.codelion.animalcare.domain.animal.entity.Animal;
import com.codelion.animalcare.domain.animal.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HasAnimalsInterceptor implements HandlerInterceptor {
    private final AnimalService animalService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        List<Animal> animalList = animalService.findByMemberEmail(email);

        // 동물이 없으면 false and redirect.
        if(animalList.size() == 0){
            response.sendRedirect("/error/not-animals");
            return false;
        }

        return true;
    }
}
