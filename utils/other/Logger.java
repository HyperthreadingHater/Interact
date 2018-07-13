package us.interact.utils.other;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public static void log(String msg) {
        SimpleDateFormat sd = new SimpleDateFormat("HH:m:s");
        System.out.print("[" + sd.format(new Date()) + "] [Interact/INFO]: " + msg + "\n");
    }

    public static void warn(String msg) {
        SimpleDateFormat sd = new SimpleDateFormat("HH:m:s");
        System.out.print("[" + sd.format(new Date()) + "] [Interact/WARN]: " + msg + "\n");
    }

    public static void err(String msg) {
        SimpleDateFormat sd = new SimpleDateFormat("HH:m:s");
        System.out.print("[" + sd.format(new Date()) + "] [Interact/ERROR]: " + msg + "\n");
    }

}
