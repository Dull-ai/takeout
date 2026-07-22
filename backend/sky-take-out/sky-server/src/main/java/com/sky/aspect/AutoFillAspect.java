package com.sky.aspect;
import com.sky.annoation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Aspect
@Slf4j
@Component
public class AutoFillAspect {
@Pointcut("execution(* com.sky.mapper.*.*(..))&& @annotation(com.sky.annoation.AutoFill)")
    public void autoFillAspectPointCut(){}

    @Before("autoFillAspectPointCut()")
    public void autofill(JoinPoint joinPoint){

    //获取当前被拦截的方法上的数据库类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType value = annotation.value();
        //获取当前被拦截的方法上的参数--实体对象
        Object[] args = joinPoint.getArgs();
        if(args==null || args.length==0){
            return;
        }
        Object entity = args[0];

        //准备插入的数据

        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();
        //根据当前不同的类型通过反射插入数据
            if(value==OperationType.INSERT){
                //4个参数
                try {
                    Method setCreateTime = entity.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
                    Method setCreateUser = entity.getClass().getDeclaredMethod("setCreateUser", Long.class);
                    Method setUpdateTime = entity.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                    Method setUpdateUser = entity.getClass().getDeclaredMethod("setUpdateUser", Long.class);
                    setCreateTime.invoke(entity, now);
                    setCreateUser.invoke(entity,currentId);
                    setUpdateTime.invoke(entity,now);
                    setUpdateUser.invoke(entity,currentId);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


            }else if(value==OperationType.UPDATE){
                //4个参数
                try {
                    Method setUpdateTime = entity.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                    Method setUpdateUser = entity.getClass().getDeclaredMethod("setUpdateUser", Long.class);
                    setUpdateTime.invoke(entity,now);
                    setUpdateUser.invoke(entity,currentId);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
    }



}
