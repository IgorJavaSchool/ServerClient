package yanevskyy;

/**
 * Created by MM on 06.06.2016.
 */

public abstract class CheckExit {
    protected boolean exit;
    protected boolean stopSend;

    public CheckExit() {
        this.exit = false;
        this.stopSend = false;
    }

    /**
     * Gets word or sentence.
     * If gets "stop", then continue sends the words to server but not returns.
     * If gets "continue", then continue again returns random sentence from file.
     * If gets "exit", then closes program.
     * @param message
     */
    protected void checkMessage(String message){
        if (message.equalsIgnoreCase("stop"))
            this.stopSend = true;
        else if (message.equalsIgnoreCase("continue"))
            this.stopSend = false;
        else if (message.equalsIgnoreCase("exit")) {
            this.exit = true;
            this.stopSend = true;
        }
    }
}
