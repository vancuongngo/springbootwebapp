package com.vancuongngo.springwebapp.service.mapper;

import com.vancuongngo.springwebapp.annotation.IsFlower;
import com.vancuongngo.springwebapp.annotation.IsFurniture;
import com.vancuongngo.springwebapp.dto.Flower;
import com.vancuongngo.springwebapp.dto.Furniture;
import com.vancuongngo.springwebapp.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class CustomWebArgumentResolver implements WebArgumentResolver {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest nativeWebRequest) throws Exception {
        log.debug("Product count {}", productRepository.count());
        if (methodParameter.hasParameterAnnotation(IsFurniture.class)) {
            HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
            return convertToFurnitureObject(request);
        }
        if (methodParameter.hasParameterAnnotation(IsFlower.class)) {
            HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
            return convertToFlowerObject(request);
        }
        return UNRESOLVED;
    }

    private Object convertToFlowerObject(HttpServletRequest request) {
        String name = request.getParameter("name");
        String usage = request.getParameter("usage");
        return new Furniture(name, usage);
    }

    private Object convertToFurnitureObject(HttpServletRequest request) {
        String name = request.getParameter("name");
        int numberOfPetal = Integer.parseInt(request.getParameter("numberOfPetal"));
        return new Flower(name, numberOfPetal);
    }
}
