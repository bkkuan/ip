public class Tasks {
    private String information;
    private boolean isDone;

    public Tasks(String information) {
        this.information = information;
        this.isDone = false;
    }
    //tasks marked done
    public void taskDone() {
        isDone = true;
    }
    //tasks unmarked or not done
    public void taskNotDone() {
        isDone = false;
    }
   /* public String status() {
        if (isDone) {
            return "[X]";
        }
        else {
            return "[ ]";
        }
    }*/
    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ")  + information;
    }
}
