public class Meeting extends Task {
    protected int id;
    protected String topic;
    protected String project;
    protected String start;


    public Meeting(int id, String topic, String project, String start) {
        super(id); // вызов родительского конструктора
        this.topic = topic;
        this.project = project;
        this.start = start;// заполнение своих полей
    }

    public String getTopic() {
        return topic;
    }

    @Override
    public boolean matches(String query) {
        if (topic.contains(query)) {
            return true;
        }
        if (project.contains(query)) {
            return true;
        }
        return false;
    }


}
