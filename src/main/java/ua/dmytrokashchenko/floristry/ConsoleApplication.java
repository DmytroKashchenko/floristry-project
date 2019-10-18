package ua.dmytrokashchenko.floristry;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.dmytrokashchenko.floristry.view.Menu;

public class ConsoleApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("app-context-annotation.xml");
        Menu menu = context.getBean("menu", Menu.class);
        menu.run();
    }
}
