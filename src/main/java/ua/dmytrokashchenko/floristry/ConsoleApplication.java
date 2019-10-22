package ua.dmytrokashchenko.floristry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.dmytrokashchenko.floristry.view.Menu;

public class ConsoleApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(FloristConfig.class);
        Menu menu = context.getBean("menu", Menu.class);
        menu.run();
    }
}
