package com.company.schedulars;

import com.company.entity.Developer;
import com.company.helper.DeveloperIdGenrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimpleSchedular {

    @Scheduled(fixedDelay = 5000)
    public void printSomething(){
        System.out.println("rahul");
    }
    @Autowired
    private Developer developer ;

//    @Scheduled(cron = "0 */5 * * * *")
//    public void automaticGenerateDeveloperId(){
//       DeveloperIdGenrator.genrateDeveloperId(developer);
//        if(developer.getDeveloperId()==null){
//             DeveloperIdGenrator.genrateDeveloperId(developer);
//        }
//    }
}

