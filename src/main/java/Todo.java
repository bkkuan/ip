public class Todo extends Tasks {
    public Todo(String information) {
        super(information);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
