import java.util.*;
import java.util.stream.*; //для суммы массива
import java.lang.Math;
import java.util.ArrayList;

class Circle implements Shape
{
   double p = 0;
   String name = "Circle";
   int r = 0;

   Circle(int r) //в зависимости от кол-ва параметров строится фигура
   {
      this.r = r;
   }

   public double getP() {return this.p;}
   public void getPir() 
   {
         p=2*Math.PI*Math.abs(r);
   }
}

class Triangle implements Shape
{
   double p = 0;
   String name = "Triangle";
   int[] shapeArr = {0,0,0,};

   Triangle(int...num) 
   {
      if (num.length==3)
         this.shapeArr=num;
   }

   public double getP() {return this.p;}
   public void getPir() 
   {
      p=IntStream.of(shapeArr).map(Math::abs).sum();
   }
}

class Rectangle implements Shape
{
   double p = 0;
   String name = "Rectangle";
   int[] shapeArr = {0,0,0,0};

   Rectangle(int...num) 
   {
      if (num.length==4)
         this.shapeArr=num;
   }

   public double getP() {return this.p;}
   public void getPir() 
   {
      p=IntStream.of(shapeArr).map(Math::abs).sum();
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
      double min=0, max=0, tmp=0;
      Object[] figures = new Object[10];
      for (int i=0;i<10;i++)
      {
         int n = (int)((Math.random() * (4-1)) + 1); //число сторон (1 - окружность)

         int a = (int)((Math.random() * (11-1)) + 1); 
         int b = (int)((Math.random() * (11-1)) + 1); 
         int c = (int)((Math.random() * (11-1)) + 1); 
         int d = (int)((Math.random() * (11-1)) + 1); 
         switch (n){
            case 1:
               figures[i] = new Circle(a);
               ((Circle)figures[i]).getPir();   
               tmp = ((Circle)figures[i]).getP();
               break;
            case 2:
               figures[i] = new Triangle(a,b,c);
               ((Triangle)figures[i]).getPir();   
               tmp = ((Triangle)figures[i]).getP();
               break;
            case 3:
               figures[i] =new Rectangle(a,b,c,d);
               ((Rectangle)figures[i]).getPir();   
               tmp = ((Rectangle)figures[i]).getP();
               break;
         }
         //Почему нельзя (вернее я не понял как) после свича сделать что-то типа:
         //(figures[i].getClass())figures[i].getPir();  
         //tmp = (figures[i].getClass())figures[i].getP();  
         //??? вместо того, чтобы в кейсах кастовать 
         //конкретную фигуру
         if (i==0){min=tmp;max=tmp;}
         if (tmp > max)
            max=tmp;
         if (tmp < min)
            min=tmp;
      }

      System.out.format("Min Pir = %4.3f%n", min); 
      System.out.format("Max Pir = %4.3f%n", max); 
   }
}
