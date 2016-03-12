package Utility

/**
 * Created by jitin on 12/3/16.
 */
class Util {

    public static String getRandomPassword(){

        List l = [0,1,2,3,4,5,6,7,8,9]
        Collections.shuffle(l)
        return l.subList(0,6).join('')

    }
}
