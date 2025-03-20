
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTasksMatchingQuery() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить молоко");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] result = todos.search("молоко");

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTasks() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить молоко");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};

        Todos todos = new Todos();

        todos.add(simpleTask);

        Task[] result = todos.search("Кисель");

        Task[] expected = {simpleTask};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSimpleTask() {
        SimpleTask task = new SimpleTask(22, "Купить мозги");
        boolean actual = task.matches("Купить");
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldNotSimpleTask() {
        SimpleTask task = new SimpleTask(22, "Купить мозги");
        boolean actual = task.matches("Проверить наличие");
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldFindEpic() {
        Epic task = new Epic(666, new String[]{"Молоко", "Яйца", "Хлеб", "Молоток"});
        boolean actual = task.matches("Молоток");
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldNotFindEpic() {
        Epic task = new Epic(999, new String[]{"Молоко", "Яйца", "Хлеб", "Молоток"});
        boolean actual = task.matches("Пиво");
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldFindMeeting() {
        Meeting task = new Meeting(55, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");
        boolean actual = task.matches("Выкатка");
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldFindMeeting2() {
        Meeting task = new Meeting(54, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");
        boolean actual = task.matches("Приложение");
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldNotFindMeeting() {
        Meeting task = new Meeting(53, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");
        boolean actual = task.matches("Сделать");
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouTask() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить Хлеб");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic};
        Task[] actual = todos.search("Хлеб");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouTaskZero() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить Хлеб");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("Сьесть");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouTaskMeeting() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить Хлеб");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("Выкатка");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testConstructorAndGetId() {
        Task task = new Task(5);
        int expected = 5;
        int actual = task.getId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMatches() {
        Task task = new Task(5);
        int expected = 5;
        boolean actual = task.matches("query");
        Assertions.assertFalse(actual);

        boolean actual2 = task.matches("");
        Assertions.assertFalse(actual2);

        boolean actual3 = task.matches(null);
        Assertions.assertFalse(actual3);
    }

    @Test
    public void testEqualsTask() {
        Task task1 = new Task(1);
        Task task2 = new Task(1);
        Task task3 = new Task(2);
        Task task4 = null;
        String notATask = "not Task";

        boolean actual = task1.equals(task2);
        Assertions.assertTrue(actual);

        boolean actual2 = task1.equals(task3);
        Assertions.assertFalse(actual2);

        boolean actual3 = task1.equals(null);
        Assertions.assertFalse(actual3);

        boolean actual4 = task1.equals(task1);
        Assertions.assertTrue(actual4);

        boolean actual5 = task1.equals(task4);
        Assertions.assertFalse(actual5);

        boolean actual6 = task1.equals(notATask);
        Assertions.assertFalse(actual6);
    }

    @Test
    public void testHashCode() {
        Task task1 = new Task(1);
        Task task2 = new Task(1);
        Task task3 = new Task(2);

        int expected = task2.hashCode();
        int actual = task1.hashCode();
        Assertions.assertEquals(expected, actual);

        int expected2 = task2.hashCode();
        int actual2 = task3.hashCode();
        Assertions.assertNotEquals(expected2, actual2);
    }

}
