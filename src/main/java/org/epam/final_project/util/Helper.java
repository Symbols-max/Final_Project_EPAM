package org.epam.final_project.util;
import org.epam.final_project.service.Service;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    final private static int NUMBER_ELEMENTS_ON_PAGE=2;

    public static Object[] pagginationHelp(int page,List list){
        if (page < 0) page = 0;
        Object[] obj=list.toArray();
        Object[] objShort;
        int start=page * NUMBER_ELEMENTS_ON_PAGE;
        int end=start+NUMBER_ELEMENTS_ON_PAGE;


        if(end>obj.length){
            List list1=new ArrayList();
            for (int i=start;i<obj.length;i++){
                list1.add(obj[i]);
            }
            objShort= list1.toArray();
        }
        else {
            objShort = new Object[NUMBER_ELEMENTS_ON_PAGE];
            int n=0;
            for (int i=start;i<end;i++){
                objShort[n]=obj[i];
                n++;
            }
        }

        return objShort;
    }

    public static int numberPages(Service service){

        int numberElements=service.count();

        return (numberElements / NUMBER_ELEMENTS_ON_PAGE) +
                ((numberElements % NUMBER_ELEMENTS_ON_PAGE > 0) ? 1 : 0);

    }

//    public static String coder(String password){
//        String passwordEncode= BCrypt.hashpw(password,BCrypt.gensalt());
//        return passwordEncode;
//    }
//
//    public static boolean checkPassword(String password,String hash){
//        return BCrypt.checkpw(password,hash);
//    }
}
