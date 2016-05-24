/*
 * Copyright (c) 2015 Espark And ©Adarsh Development Services @copyright All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Espark nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.espark.adarsh.aop;

import com.espark.adarsh.bean.Employee;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
@Aspect
@Configuration
public class EmployeeServiceAspect {

    private static final Logger LOGGER= Logger.getLogger(EmployeeServiceAspect.class);

    @Pointcut("execution( public * com.espark.adarsh.service.EmployeeServiceImpl.saveEmployee(..))")
    public void employeeServiceSaveEmployeePointCut(){

    }

    @Before("employeeServiceSaveEmployeePointCut()")
    public void employeeServiceSaveEmployeeBeforeAdvice(JoinPoint joinPoint){
        Employee employee=(Employee)(joinPoint.getArgs())[0];
        LOGGER.info("Before executing employee service save method "+employee);
    }

    @Around("employeeServiceSaveEmployeePointCut()")
    public Object employeeServiceSaveEmployeeAroundAdvice(ProceedingJoinPoint joinPoint)throws Throwable{
        Employee employee=(Employee)(joinPoint.getArgs())[0];
        LOGGER.info("Before executing employee service save method "+employee);
        Object result=joinPoint.proceed();
        LOGGER.info("After executing employee service save method ");
        return result;
    }

    @After("employeeServiceSaveEmployeePointCut()")
    public void employeeServiceSaveEmployeeAfterAdvice(JoinPoint joinPoint){
        Employee employee=(Employee)(joinPoint.getArgs())[0];
        LOGGER.info("Before executing employee service save method "+employee);
    }

    @AfterReturning( pointcut = "employeeServiceSaveEmployeePointCut()",returning= "result")
    public void employeeServiceSaveEmployeeAfterReturningAdvice(JoinPoint joinPoint,Object result){
        LOGGER.info("Exception Generated During executing employee service save method "+result);
    }

    @AfterThrowing( pointcut = "employeeServiceSaveEmployeePointCut()",throwing= "error")
    public void employeeServiceSaveEmployeeAfterThrowingAdvice(JoinPoint joinPoint,Throwable  error){
        LOGGER.info("After Returning executing employee service save method "+error);
    }

}
