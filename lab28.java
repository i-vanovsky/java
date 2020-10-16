//"Длина слова - дискретная случайная величина X. На вход программе передается текст.
//Найти закон распределения X в форме таблицы, например:
//Xi  4         5         8
//Pi  0.23    0.5     0.27
//Вычислите мат. ожидание и дисперсию X."
//http://study-java.ru/uroki-java/formatirovanie-chisel-i-texta-v-java/
import java.util.*;
import java.util.stream.*; //почему так, если есть то, что выше?
import java.io.*; //для буферридера(читаем построчно)

class lab28
{
   public static void main (String args[])
   {
      double M=0; double D=0;
      int[] work = new int[40];  //рабочий массив 
      double[] prob = new double[40]; //массив верятностей. как вариант - можно и без него.
      //try-with-res - автоматически удалит ресурсы при отработке
      try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) 
      {
         String line;
         while ((line = br.readLine()) != null) //читаем в условии
         {
            String[] words = line.split("[^а-яА-яё]+"); //делим по разделителю
            for (String word : words) //для каждого слова из массива
               //если слово совпадает со всем
               //за исключением отдельно стоящих символов кроме ВвУуАаОоЯя
               //плюс нулевая длина, т.к. что-то просачивается (- из примера))
               if (!word.matches("[^ВвУуАаОоЯя]") & word.length()!=0)
                  work[word.length()-1]+=1;  //увеличиваем зн-е по индексу==длине слова
         }
      }
      catch (IOException ex){
         //System.out.println(ex.getMessage());
         System.out.println("Что-то со чтением");
      } 

      for (int i=0;i<work.length;i++) //считаем вероятности
         prob[i]=(double)work[i]/IntStream.of(work).sum(); //делим на общее число слов

      for (int i=0;i<work.length;i++) //находим М
         M+=i*prob[i];

      for (int i=0;i<work.length;i++) //находим Д
         D+=(i-M)*(i-M)*prob[i];

      System.out.format("%5s %6s%n", "Xi","Pi"); //вывод
      for (int i=0;i<work.length;i++)
         if (work[i]!=0)
            System.out.format("%4d %10f%n", i+1,prob[i]);
      System.out.format("M = %4.3f%n",M);      
      System.out.format("D = %4.3f%n",D);      
   }
}
      //вариант вывода 
      //System.out.println(Arrays.toString(prob));      
