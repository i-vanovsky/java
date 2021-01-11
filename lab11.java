//"Создать класс Person с полями: name, surname, age.
//Имплементировать интерфейс Comparable, используя поля surname и name.
//Добавить несколько объектов Person в List. Использовать Comparator для различных типов сортировки.
//Вывести: 
//1) неотсортированный список, 
//2) список, отсортированный по name,
//3) список, отсортированный по surname, 
//4) список, отсортированный по age"
//https://stackoverflow.com/questions/45550934/sort-a-list-of-objects-based-on-a-parameterized-attribute-of-the-object
//https://stackoverflow.com/questions/5805602/how-to-sort-list-of-objects-by-some-property
//https://stackoverflow.com/questions/11901072/comparing-different-type-of-objects-with-comparable
//https://howtodoinjava.com/java/collections/arraylist/arraylist-sort-objects-by-field/
//https://stackoverflow.com/questions/369512/how-to-compare-objects-by-multiple-fields
//https://stackoverflow.com/questions/4066538/sort-an-arraylist-based-on-an-object-field
import java.util.*;
class Person implements Comparable<Person>{
   String name;
   String surname;
   int age;
   int flag = 1;
   public Person(String name, String surname, int age){
      this.name=name; this.surname=surname; this.age=age;
   }
   public void setFlag(int flag){this.flag=flag;}
   @Override
   public int compareTo(Person p){
      int i=0;
      if (flag==1)
         i = name.compareTo(p.name);
      if (flag==2)
         i = surname.compareTo(p.surname);
      if (flag==3)
         i = age - p.age;
      return i;
   }
   @Override
   public String toString() {
      return "[" +name + ", " + surname + ", " + Integer.toString(age) + "]\n";
   }
}
class lab11{
   public static void main(String[] args){
      ArrayList<Person> person = new ArrayList<>();
      person.add(new Person("Наталья", "Ростова", 30));
      person.add(new Person("Элен", "Курагина", 21));
      person.add(new Person("Мария", "Болконская", 27));
      person.add(new Person("Федор", "Долохов", 25));
      person.add(new Person("Иполит", "Курагин", 20));
      person.add(new Person("Анатоль", "Курагин", 26));
      person.add(new Person("Борис", "Друбецкой", 23));
      System.out.println(person);
      Collections.sort(person);
      System.out.println(person);
      for (Person p : person)
         p.setFlag(2);
      Collections.sort(person);
      System.out.println(person);
      for (Person p : person)
         p.setFlag(3);
      Collections.sort(person);
      System.out.println(person);
   }
}
