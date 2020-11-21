//https://stackoverflow.com/questions/12908412/print-hello-world-every-x-seconds
//https://stackoverflow.com/questions/1409116/how-to-stop-the-task-scheduled-in-java-util-timer-class
//https://stackoverflow.com/questions/12465127/simple-java-countdown
import java.util.*;
import java.io.*;

class Player{ //класс игрока
   String name; //имя
   int score;   //последний выстрел
   int total;   //сумма всех очков
   int hrt;     //оцки за сердце, голову, живот
   int hed;   
   int stm;   
   public void setName(String name){this.name = name;}   //конструктор
   public String getName(){return this.name;}
   public void writeScore(long hits, long diff){   //метода записи результатов
      if (hits>=15000-diff & hits<=15000+diff)
         {hrt+=15;score=15;}
      else if (hits>=8000-diff & hits<=8000+diff)
         {hed+=8;score=8;}
      else if (hits>=5000-diff & hits<=5000+diff)
         {stm+=5;score=5;}
      else
         score=0;
      total = hrt+hed+stm;
   }
   public String toString(){
      return name+": Сердце: "+hrt+" Голова: "+hed+" Живот: "+stm+" Посл. выстрел "+score+" Итого: "+total; 
   }
}

class lab35{
   public static void main(String[] args){
      Scanner keyboard = new Scanner(System.in);
      String command;
      long start;  //время "к барьеру"
      long finish; //время выстрела
      long hits;   //от барьера до выстрела
      long diff;   //окрестность  
      Player p1 = new Player();
      Player p2 = new Player();
      Player tmp = new Player();
      boolean playerSw = true; //смена игрока после круга
      boolean exit = false;    //для выхода и программы
      System.out.println("Игрок 1, введите имя");
      command = keyboard.nextLine();
      p1.setName(command);
      System.out.println("Игрок 2, введите имя");
      command = keyboard.nextLine();
      p2.setName(command);
      System.out.println("Выберите уровень сложности:\n1 - легкий\n2 - средний\n3 - сложный");
      while(true){   //цикл выбора сложности, чтобы было лишь 3 варианта
         command = keyboard.nextLine();
         if (command.equals("1")) {diff=1000;break;}
         if (command.equals("2")) {diff=200;break;}
         if (command.equals("3")) {diff=100;break;}
      }
      while(true){
         if(playerSw) //очередь игрока
            tmp = p1;
         else
            tmp = p2;
         //таймер отсчета перед выстрелом
         try{
            for (int i=3;i>0;i--){
               System.out.println(i);
               Thread.sleep(1000);
            }
         }
         catch(InterruptedException e) {System.out.println("CD interrupted");}

         System.out.println(tmp.getName()+" к барьеру!");
         start = System.currentTimeMillis();
         command = keyboard.nextLine();
         finish = System.currentTimeMillis();
         hits = finish - start; //подсчет мс от вызова к выстрелу
         System.out.println(hits);
         tmp.writeScore(hits,diff); //запись очков
         playerSw = !playerSw; //передача очереди
         if (playerSw){
            System.out.println(p1); //вывод статистики 1
            System.out.println(p2); //вывод статистики 2
            System.out.println("Еще раунд? [y/n]"); 
            while(true){ //цикл, чтобы ввести лишь да и нет
               command = keyboard.nextLine();
               if (command.equals("y")) break;
               if (command.equals("n")) {exit=!exit; break;}
            }
         }
         if (exit) break;
      }
   }
}
