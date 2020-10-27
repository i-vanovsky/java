//https://metanit.com/java/tutorial/5.6.php
//https://stackoverflow.com/questions/4242023/comparator-with-double-type
import java.util.*;
import java.util.stream.*; //для суммы массива
import java.lang.Math;
import java.util.ArrayList;

class Figure implements Shape, Comparable<Figure>
{
   double p = 0;
   String name = "";
   int[] shapeArr = {0,0,0,0};

   Figure(int... num) //в зависимости от кол-ва параметров строится фигура
   {
      for (int i=0;i<num.length;i++)
         shapeArr[i]=num[i];
      switch (num.length){ //имя для наглядности
         case 1:
            name = "Circle";
            break;
         case 3:
            name = "Triangle";
            break;
         case 4:
            name = "Rectangle";
            break;
      }
   }

   public double getP() {return this.p;}
   public void getPir() 
   {
      if (IntStream.of(shapeArr).sum()-shapeArr[0] == 0)
         p=2*Math.PI*shapeArr[0];
      else
         p=IntStream.of(shapeArr).sum();
   }
   public int compareTo(Figure f) 
   {
      //return (int)(p-f.getP()); //можно и так и этак
      if (p < f.getP()) return -1;
      if (p > f.getP()) return 1;
      return 0;
   }
}


interface Shape
{
   void getPir();
}

class lab29
{
   public static void main (String args[])
   {
      //список из 10 случайно сгенерированных фигур со сторонами не больше 10
      ArrayList<Figure> figures = new ArrayList<Figure>();
      for (int i=0;i<10;i++)
      {
         int n = (int)((Math.random() * (4-1)) + 1); //число сторон (1 - окружность)

         int a = (int)((Math.random() * (11-1)) + 1); 
         int b = (int)((Math.random() * (11-1)) + 1); 
         int c = (int)((Math.random() * (11-1)) + 1); 
         int d = (int)((Math.random() * (11-1)) + 1); 
         
         switch (n){
            case 1:
               figures.add(new Figure(a));
               break;
            case 2:
               figures.add(new Figure(a,b,c));
               break;
            case 3:
               figures.add(new Figure(a,b,c,d));
               break;
         }
         figures.get(i).getPir(); //тут же считаем периметр
      }

      Collections.sort(figures); //почему нельзя просто figurs.sort()?

      System.out.format("Min Pir = %4.3f%n", Collections.min(figures).getP()); 
      System.out.format("Max Pir = %4.3f%n", Collections.max(figures).getP()); 
   }
}
