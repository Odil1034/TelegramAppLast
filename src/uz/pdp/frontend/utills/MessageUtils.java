package uz.pdp.frontend.utills;

public interface MessageUtils {

    static void notFound(String hint) {
        System.out.printf("%s is not found ❌❌❌", hint);
    }

}
