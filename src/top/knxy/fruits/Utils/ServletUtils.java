package top.knxy.fruits.Utils;


import java.io.*;
import java.lang.reflect.Field;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import top.knxy.fruits.Config.V;

public class ServletUtils {
    public static <T> T requestParamToModel(HttpServletRequest request, Class<T> beanClass) {
        try {
            T bean = beanClass.newInstance();
            Field[] fs = beanClass.getDeclaredFields();

            for (Field field : fs) {
                String value = request.getParameter(field.getName());
                if (value != null) {
                    field.set(bean, value);
                }
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void putAttribute(HttpServletRequest request, Object obj) {
        Gson gson = new Gson();
        String data = gson.toJson(obj);
        request.setAttribute(V.data, data);
    }

    public static void putAttributeWithDateFormat(HttpServletRequest request, Object obj) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
        String data = gson.toJson(obj);
        request.setAttribute(V.data, data);
    }

    /*public static void toErrorPage(HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException {
        request.setAttribute(V.msg, msg);
        request.getRequestDispatcher(J.error).forward(request, response);
    }*/
}
