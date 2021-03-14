//описание внизу
//https://javarush.ru/groups/posts/2179-metodih-equals--hashcode-praktika-ispoljhzovanija
//https://metanit.com/java/tutorial/5.4.php
import java.util.*;
class Main {
	public static void main(String[] args) {
		HashSet<Student> stList = new HashSet<Student>(); //список студентов
		Scanner in = new Scanner(System.in); //сканер для ввода с клавы
		String[] stdDataArr;//сюда будем парсить строку ввода студента
		String stdData;
		/* проверим ручками, что все работает. Принт не выведет Диму и Мишу
		stList.add(new Student("Vasya","666","12345"));
		stList.add(new Student("Anya","664","12346"));
		stList.add(new Student("Vasya","666","12347"));
		stList.add(new Student("Vika","664","12348"));
		stList.add(new Student("Dima","664","12348"));
		stList.add(new Student("Misha","664","12348"));
		*/
		System.out.println("Enter student info: \"LnFnPn, group N, student ID\"\n"+
		"(To finish input enter \"end\")\n\n");
		while (true){
			System.out.println("Enter student info (To finish input enter \"end\")");
			stdData = in.nextLine(); //вводим строку
			if (stdData.equals("end")) //если энд - брейк
				break;
			stdDataArr = stdData.split("\\s*,\\s*"); //сплитим строку по запятой, удаляя пробелы рядом с ней
			//кладем в список нового студента
			stList.add(new Student(stdDataArr[0],stdDataArr[1],stdDataArr[2]));
		}
		for (Student std : stList) //выводим на печать
			System.out.println(std);
	}
}

class Student{
	String name;
	String group;
	String studentId;
	
	//конструктор
	public Student(String name,String group,String studentId){
		this.name = name;
		this.group = group;
		this.studentId = studentId;
	}
	
	@Override //переписываем иквэлс так, что они  равны по зачетке
	public boolean equals(Object o) {
		if (this == o) return true; //равенство самому себе
		if (o == null || this.getClass() != o.getClass()) return false; //нал или разные классы
		Student st = (Student) o;
		return this.studentId.equals(st.studentId); //сравнение по зачетке
	}
	
	@Override //равенство хэшей по зачетке
	public int hashCode() {
		return 31*studentId.hashCode();
	}
	
    @Override //метод для принта
    public String toString() { 
        return name+", "+group+", "+studentId;
    } 
}

/*
Описание

Вы разрабатываете систему учета студентов. Для каждого студента нужно хранить следующие данные – ФИО, номер группы, номер студенческого билета. Уникальным идентификатором является номер студенческого билета. Пользователь вводит данные студентов в бесконечном цикле до ввода команды "end". По окончании ввода программа должна вывести список студентов. Структура данных, куда сохраняются студенты, должна отбрасывать ввод одного и того же студента более одного раза. Учитывайте, что имена у людей могут быть одинаковыми, а номера документов – нет.
Функционал программы

    Ввод информации о студентах;
    Вывод списка студентов.

Пример

Введите информацию о студенте: "ФИО, номер группы, номер студенческого билета"
Иванов Петр Николаевич, 1243-Б, 31231343 <enter>
Введите информацию о студенте (для завершения работы программы введите "end")
Петрова Татьяна Михайловна, 1243-Б, 43221343 <enter>
Введите информацию о студенте (для завершения работы программы введите "end")
end <enter>
Список студентов:
  - Иванов Петр Николаевич, 1243-Б, 31231343
  - Петрова Татьяна Михайловна, 1243-Б, 43221343

Реализация

    Создайте класс Student с полями name, group, studentId. Тип каждого поля – String.
    Переопределите методы hashcode и equals для класса Student так, чтобы нельзя было сохранить двух студентов с одинаковым номером студенческого билета.
    Продемонстрируйте добавление объектов класса в HashSet, ошибку при добавлении студентов с одинаковым номером, возможность существования студентов с одинаковыми именами.
*/
