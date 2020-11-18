//https://stackoverflow.com/questions/692569/how-can-i-count-the-time-it-takes-a-function-to-complete-in-java
import java.util.*;
import java.io.*;

class User implements Serializable{ //пользователь с возможности серилизации
   String name;
   int rop_kcal;
   int psh_kcal;
   int sqt_kcal;
   public User(String name){this.name=name;}
   public void addKcal(int id, int kcal){ //добавляем калории после каждой т.
      if (id==1) rop_kcal+=kcal;
      if (id==2) psh_kcal+=kcal;
      if (id==3) sqt_kcal+=kcal;
   }
   public String toString(){
      return name + " - rope: " +rop_kcal+" push-ups: "+psh_kcal+ " squats: "+sqt_kcal;
   }
}

class Workout {   
   ArrayList<User> users = new ArrayList<User>(); 
   File file = new File("users.bin"); //файл с результатами
   Scanner keyboard = new Scanner(System.in);
   String command;
   int userId = -1; 
   int trainId = 0;
   long start;
   long finish;
   int trainKcal;

   public Workout(){ //в конструкторе добавим несколько юзеров
      users.add(new User("Cat"));
      users.add(new User("Dog"));
      users.add(new User("Pig"));
   }

   public void readUsers(){   //читаем результаты из файла
      if (file.length()!=0){
         try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (ArrayList<User>)ois.readObject();
            ois.close();
         }
         catch (IOException e){e.printStackTrace();}
         catch (ClassNotFoundException e){System.out.println("class err");}
      }
   }

   public void writeUsers(){  //записываем результаты в файл
      try{
         FileOutputStream fos = new FileOutputStream(file);
         ObjectOutputStream oos = new ObjectOutputStream(fos);
         oos.writeObject(users);
         oos.close();
      }
      catch (IOException e){e.printStackTrace();}
   }


   public void addUser(){  //добавить юзера
      int tmp = users.size();
      boolean flag;
      System.out.println("Add user:");
      while(true){  
         flag = true;
         command = keyboard.nextLine();
         for (User user : users){
            if (command.equals(user.name)){
               System.out.println("Such user exists");
               flag = false;
               break;
            }
         }
         if (flag==false) continue;
         users.add(new User(command));
         System.out.println("User \"" +command+ "\" is added");
         userId = users.size()-1;
         break;
      }
   }  

   public void setUser(){  //выбрать юзера
         System.out.println("Choose your fighter!:");
      while(true){  
         command = keyboard.nextLine();
         for (int i=0;i<users.size();i++){
            if (command.equals(users.get(i).name)){
               System.out.println("You've choosen "+users.get(i).name);
               userId = i;
               break;
            }
         }
         if (userId!=-1) break;
      }
   }

   public void setTrain(){ //выбор тренировку
      System.out.println("Choose activity:\n1 - push-ups\n2 - skipping-rope\n3 - squats");
      while(true){  
         int command = keyboard.nextInt();
         if (command==1){
            trainId = 1;
            trainKcal = 450;
            System.out.println("You've choosen push-ups");
            break;
         }
         if (command==2){
            trainId = 2;
            trainKcal = 700;
            System.out.println("You've choosen skipping-rope");
            break;
         }
         if (command==3){
            trainId = 3;
            trainKcal = 550;
            System.out.println("You've choosen squats");
            break;
         }
      }
   }
   public void runTrain(){ //запускаем т., считаем калории, добавляем юзеру
      System.out.println("type \"start\" to begin");
      while(true){
      command = keyboard.nextLine();
         if (command.equals("start")){
            start = System.currentTimeMillis();
            System.out.println("type \"stop\" to finish");
            while (true){
               command = keyboard.nextLine();
               if (command.equals("stop")){
                  finish = System.currentTimeMillis();
                  break;
               }
            }
         break;
         }
      }
      int kcal = (int)(finish-start)*trainKcal/3600000;
      System.out.println(users.get(userId).name +" wasted "+kcal +" KCal\n");
      (users.get(userId)).addKcal(trainId, kcal);
   }
   public void printRes(){ //печать результатов
      System.out.println(users.get(userId)+"\n");
   }
   
   public void printAll(){ //печать всех
      for (User user : users)
         System.out.println(user);
   }

   public void showUsers(){   //показать имена юзеров
      for (User user : users)
         System.out.println(user.name);
   }
}

class lab37_1{
   public static void main(String[] args){
      Scanner keyboard = new Scanner(System.in);
      String command;
      Workout wo = new Workout();
      wo.readUsers(); //читаем пользователей
      wo.showUsers(); //выводим
      //выбрать или добавить
      System.out.println("Choose(1) or add(2) user");
      while(true){
         command = keyboard.nextLine();
         if (command.equals("1")){
            wo.setUser();
            break;
         }
         if (command.equals("2")){
            wo.addUser();
            break;
         }
      }
      wo.setTrain(); //выбор тренировки
      wo.runTrain(); //начать трнировку
      wo.writeUsers(); //записываем результаты
      wo.printRes(); //выведем результаты текущей тренировки
      wo.printAll(); //покажем достижения всех пользователей
   }
}
