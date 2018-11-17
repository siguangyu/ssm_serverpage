package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: sgy
 * @CreateDate: 2018/11/17$ 19:15$
 */
@Component
@Aspect
public class LogAop {
@Autowired
    private  HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;
    //访问时间
    private Date visitTime;
    //访问的类
    private Class clazz;
    //访问的方法
    private Method method;

    //前置通知，主要获取访问时间，访问的类，访问的方法
    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp){
        visitTime=new Date();
        System.out.println(visitTime);
        //获取访问的类
        clazz=jp.getTarget().getClass();
        //获取访问的方法的名称
      String   methodName=jp.getSignature().getName();
      //获取访问的方法的参数
        Object[] args = jp.getArgs();

        //获取具体执行的方法对象
        if (args==null||args.length==0){
            //只能获取无参数的方法
            try {
                method = clazz.getMethod(methodName);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }else{
            Class[] classArgs=new Class[args.length];
            for (int i=0;i<classArgs.length;i++){
                classArgs[i]=args[i].getClass();
            }
            try {
               method= clazz.getMethod(methodName,classArgs);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

    }
    //后置通知
    @After("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp){
        //获取访问的时长
            long time=new Date().getTime()-visitTime.getTime();

            String url="";
            //获取url  url=类上的路径+方法上的路径
        if (clazz!=null&&method!=null&&clazz!=LogAop.class){
            //获取类上的路径
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(clazzAnnotation!=null){
                //获取方法上的路径
                String[] clazzValue = clazzAnnotation.value();
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    url=clazzValue[0]+methodValue[0];

                    //获取访问的ip
                    String  ip = request.getRemoteAddr();
                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获了当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将日志相关信息封装到SysLog对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time); //执行时长
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                    System.out.println(sysLog);
                    //调用Service完成操作
                    sysLogService.save(sysLog);

                }
            }
        }
    }

}
