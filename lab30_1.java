//Реализуйте класс робота, имеющего 2 ноги. Каждая нога осуществляет работу (шаг) в отдельном потоке. Необходимо выполнить одно условие: шаги выполняются поочередно разными ногами. При этом неважно с какой ноги начинает ходить робот.
//NB! поток можно делать через наследование от Тред, либо через имплимент Ранебл. Во первом случае нельзя будет сделать какое-то иное наследование.
class Leg extends Thread
{
   Robot robot;
   Leg(Robot robot) {this.robot = robot;}

   public void run()
   {
      while (robot.steps > 0)
         robot.step();
   }
}

class Robot
{
   int steps;
   Leg leg_1 = new Leg(this); 
   Leg leg_2 = new Leg(this); 
   Robot(int steps) {this.steps = steps;}

   //интересно, что синхронизация по методу не сработала.
   //пришлось сделать оп объекту this
   //upd потом переделал еще раз и заработало. магия.
   public synchronized void step()
   {
      //synchronized (this)
      //{
         notify();
         System.out.println(steps+" "+Thread.currentThread().getName());
         steps--;
         try {wait();} catch (InterruptedException e)
         {
            e.printStackTrace();
         }
         notify();
      //}
   }

   public void walk(){
      leg_1.start();
      leg_2.start();
   }
}

public class lab30_1
{
   public static void main(String[] args) throws Exception 
   {
      //робот с количеством шагов из ком.стр
      Robot R1 = new Robot(Integer.parseInt(args[0]));      
      R1.walk();
   }
}
