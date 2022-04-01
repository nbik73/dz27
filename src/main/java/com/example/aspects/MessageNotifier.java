package com.example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MessageNotifier {

    @Pointcut("@annotation (com.example.annotations.MessageSend)")

    public void premiereServiceNotifier() {
    }

    @After("premiereServiceNotifier()")
    public void after(JoinPoint point) {
        String method = point.getSignature().getName();
        Object[] premiere = point.getArgs();
        String premiereName = premiere[0].toString();

        switch (method) {
            case "addPremiere":
                sendMessage("Добавление премьеры -> " + premiereName);
                break;
            case "deletePremiere":
                sendMessage("Удаление премьеры -> " + premiereName);
                break;
            case "updatePremiere":
                sendMessage("Изменение премьеры -> " + premiereName);
                break;
            default:
                sendMessage("default");
        }
    }


    public void sendMessage(String msg) {
        System.out.println(msg);
    }
}
