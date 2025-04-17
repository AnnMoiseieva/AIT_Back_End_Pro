import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Person> list = new ArrayList<>();
        list.add(new Person("Jack", 20));
        list.add(new Person("Anna", 22));
        list.add(new Person("Igor", 19));
        list.add(new Person("Mike", 24));
        list.add(null);
        list.add(new Person("Nick", 23));

        System.out.println(ifPersonWithNameExists(list, "Igor"));
        System.out.println(ifPersonWithNameExists(list, "Lena"));
        System.out.println();
        System.out.println(ifPersonWithNameExists2(list, "Igor"));
        System.out.println(ifPersonWithNameExists2(list, "Lena"));
        System.out.println();
        System.out.println(ifPersonWithNameExists3(list, "Igor"));
        System.out.println(ifPersonWithNameExists3(list, "Lena"));

    }

    public static boolean ifPersonWithNameExists(List<Person> list, String name) {
        for (Person person: list) {
            if(person.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean ifPersonWithNameExists2(List<Person> list, String name) {
       return list.stream()
                .filter(p->p!=null)
                .filter(p->p.getName().equalsIgnoreCase(name))
                .count()>0;
    }

    public static boolean ifPersonWithNameExists3(List<Person> list, String name) {
       return list.stream()
                .anyMatch(p->p.getName().equalsIgnoreCase(name));
    }
}

/*
Дано: список Person
Необходимо: написать метод, который определит, есть ли Person с заданным именем в списке*/