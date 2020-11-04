//https://coderanch.com/t/337920/java/close-window-open
//https://www.tutorialspoint.com/jdbc/jdbc-select-records.htm
//https://vertex-academy.com/tutorials/ru/kak-podklyuchitsya-k-baze-dannyx-postgresql-s-pom/
//http://java-online.ru/swing-layout.xhtml
//http://java-online.ru/swing-jtable.xhtml
//https://www.javatpoint.com/java-jtable
//https://alvinalexander.com/java/edu/pj/jdbc/jdbc0003/
//https://javaswing.wordpress.com/2010/05/05/jtabletablemodel/
//https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
//https://stackoverflow.com/questions/4507440/must-jdbc-resultsets-and-statements-be-closed-separately-although-the-connection
//http://java-online.ru/jdbc-resultset.xhtml
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.AbstractTableModel;
import java.util.*;

//Окно регистрации
class Reg extends JFrame implements ActionListener
{
    private JButton log = new JButton("Login");
    private JButton ext = new JButton("Exit");
    private JLabel wor = new JLabel("Login error");
    private JLabel name = new JLabel("Name");
    private JLabel pass = new JLabel("Password");
    private JTextField nameF = new JTextField();
    private JTextField passF = new JTextField();
    private JPanel jp = new JPanel();
  public Reg()
  {
    //центрирование элементов
    nameF.setPreferredSize(new Dimension(150, 20));
    passF.setPreferredSize(new Dimension(150, 20));
    name.setAlignmentX(Component.CENTER_ALIGNMENT);
    pass.setAlignmentX(Component.CENTER_ALIGNMENT);
    nameF.setAlignmentX(Component.CENTER_ALIGNMENT);
    passF.setAlignmentX(Component.CENTER_ALIGNMENT);
    log.setAlignmentX(Component.CENTER_ALIGNMENT);
    wor.setAlignmentX(Component.CENTER_ALIGNMENT);
    setLayout(new FlowLayout());
    jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
    jp.add(name);
    jp.add(nameF);
    jp.add(pass);
    jp.add(passF);
    jp.add(log);
    jp.add(Box.createVerticalStrut(5));
    wor.setVisible(false);
    jp.add(wor);
    add(jp);
    setTitle("Registration Window");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(350,170);
    setLocationRelativeTo(null);//по центру экрана
    log.addActionListener(this);
    setVisible(true);
  }
  //логин. если ок убрать рег окно, запустить Мэйн.
  public void actionPerformed(ActionEvent ae)
  {
    try
    {
       //Main m = new Main("postgres","6202");
       Main m = new Main(nameF.getText(),passF.getText());
       if (m.conn==null)
         wor.setVisible(true);
       else
         this.dispose();
    }
    catch (Exception e){e.printStackTrace();}
  }
}

//Вкладка с полями данных
class Tab2 extends JPanel 
{
   private Main frame; //будет ссылка Мэйн
   private Tab1 tab1; //будет ссылка на 1ю вкладку
   protected JPanel grid; //для компановки

   Tab2()
   {
      grid = new JPanel(); //панель для компановки элементов
      grid.setLayout(new GridLayout(0,2,100,10));
      setLayout(new FlowLayout());
   }

   //метод вставки данных в таблицу
   public void insert()
   {
      Component[] comp = grid.getComponents();//все элементы
      //имя таблицы
      String table = frame.tableCombo.getSelectedItem().toString();//имя таблицы из комбо
      String column = ""; //колонки для вставки
      String value  = ""; //значения колонок
      // отбираем названия столбцов и значения полей для вставки
      for(int i=0; i<comp.length; i++)
      {
         if (comp[i] instanceof JLabel) //если лэйбл, то колонка+
         {
            if (comp[i+1] instanceof JTextField)
               if (((JTextField)comp[i+1]).getText().equals(""))
                  continue;
            if (comp[i+1] instanceof JComboBox)
               if (((JComboBox)comp[i+1]).getSelectedItem().toString().equals(""))
                  continue;
            column += ((JLabel)comp[i]).getName()+",";
         }
         else if (comp[i] instanceof JTextField) //если текст, или комбо, то зн-я из них в зн-я вставки
         {                                               
            if (((JTextField)comp[i]).getText().equals(""))//если зн-е мб налл
               continue;
            value += "\'"+((JTextField)comp[i]).getText()+"\',";
         }
         else if (comp[i] instanceof JComboBox)
         {
            if (((JComboBox)comp[i]).getSelectedItem().toString().equals(""))
               continue;
            //метка, откуда обрезать строку из комбо
            int cut = ((JComboBox)comp[i]).getSelectedItem().toString().indexOf(")");
            if(cut != -1) 
               value += "\'"+((JComboBox)comp[i]).getSelectedItem().toString().substring(0,cut)+"\',";
         }
      }
      if (column!="")
         column = column.substring(0, column.length() - 1);//обрезка посл запятых
      if (value!="")
         value = value.substring(0, value.length() - 1);
      try//запрос на вставку
      {
         if (value!="")
            frame.stmt.executeUpdate("INSERT INTO " +table+" ("+column+") VALUES ("+value+")");
      }
      catch (SQLException ex){ex.printStackTrace();}
      tab1.initTable();
   }
   public void setSource(Main frame){this.frame=frame;}//ссылка на Мэйн
   public void setTab1(Tab1 tab1){this.tab1=tab1;} //ссылка на таб1
   //редактирование по введенным полям. похоже на добавление.
   public void edit() 
   {
      if (tab1.table.getSelectedRow()!=-1)
      {
         //метка, откуда обрезать строку из комбо
         String id = tab1.table.getModel().getValueAt(tab1.table.getSelectedRow(), 0).toString();
         Component[] comp = grid.getComponents();
         String table = frame.tableCombo.getSelectedItem().toString();
         String column = "";//колонки
         String value  = "";//значения
         String query  = "";//итоговый запрос
         //алгоритм примерно такой же, как в insert()
         for(int i=0; i<comp.length; i++)
         {
            value  = "";
            column  = "";
            if (comp[i] instanceof JLabel)
            {
               if (comp[i+1] instanceof JTextField)
                  if (((JTextField)comp[i+1]).getText().equals(""))
                     continue;
               if (comp[i+1] instanceof JComboBox)
                  if (((JComboBox)comp[i+1]).getSelectedItem().toString().equals(""))
                     continue;
               column += ((JLabel)comp[i]).getName()+" = ";
            }
            if (comp[i] instanceof JTextField)
            {
               if (((JTextField)comp[i]).getText().equals(""))//если зн-е мб налл
                  continue;
               value += "\'"+((JTextField)comp[i]).getText()+"\',";
            }
            if (comp[i] instanceof JComboBox)
            {
               if (((JComboBox)comp[i]).getSelectedItem().toString().equals(""))
                  continue;
               int cut = ((JComboBox)comp[i]).getSelectedItem().toString().indexOf(")");
               if(cut != -1) 
                  value += "\'"+((JComboBox)comp[i]).getSelectedItem().toString().substring(0,cut)+"\',";
            }
            query += column+value;
         }
         if (query!="")
            query = query.substring(0, query.length() - 1);//обрезка посл запятых
         try
         {
            if (query!="")
               frame.stmt.executeUpdate("UPDATE " +table+" SET "+query+" WHERE id=" +id);
         }
         catch (SQLException ex){ex.printStackTrace();}
         tab1.initTable();
      }
   }
}

class Tab1 extends JPanel
{
   private Tab2 tab2;//ссылка на 2ю вкладку
   private JButton add; 
   private Main frame; //ссылка на Мэйн
   private JButton del; 
   private JButton edit; 
   private JTextField field; 
   protected JTable table;
   private JTabbedPane tp;
   private DefaultTableModel tableModel;
   private JComboBox tableCombo;
   private String[] columns;

   public Tab1()  
   {
      setLayout(new BorderLayout());
      tableModel = new DefaultTableModel();//модель таблицы
      tableModel.setColumnIdentifiers(columns);//заголовки
      table = new JTable(tableModel);//таблица от модели
      add(new JScrollPane(table));//таблица с прокруткой
   }   
   public void setSource(Main frame){this.frame=frame;}
   public void setTab2(Tab2 tab2){this.tab2=tab2;}
   public void delRow() //метод удаления ряда
   {
      if (table.getSelectedRow()!=-1) //если выбран ряд
      {
         try //удали запись, если нужно - удали ФК
         {
            String value = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
            String tmp = frame.tableCombo.getSelectedItem().toString();
            if (tmp=="routes")
               frame.stmt.executeUpdate("DELETE FROM journal WHERE route_id="+value);
            if (tmp=="auto")
               frame.stmt.executeUpdate("DELETE FROM journal WHERE auto_id="+value);
            if (tmp=="auto_personnel")
               frame.stmt.executeUpdate("BEGIN; SAVEPOINT sp; DELETE FROM journal WHERE id IN (SELECT journal.id FROM journal JOIN auto ON journal.auto_id = auto.id JOIN auto_personnel on auto.personnel_id = auto_personnel.id WHERE auto_personnel.id = "+value+");DELETE FROM auto WHERE id IN (SELECT auto.id FROM auto JOIN auto_personnel ON auto.personnel_id = auto_personnel.id where auto_personnel.id = "+value+"); DELETE FROM auto_personnel WHERE id= "+value+";COMMIT;");
            frame.stmt.executeUpdate("DELETE FROM "+tmp+" WHERE id="+value);
            initTable();
         }
         catch (SQLException ex){ex.printStackTrace();;}
      }
   }
   //метод создания и отображения таблицы на 1ой вкладке, а также полей на 2ой
   public void initTable()
   {
      tableModel.setRowCount(0);
      String tmp = frame.tableCombo.getSelectedItem().toString();//имя таблицы из комбо
      try
      {
         ResultSet rs = frame.stmt.executeQuery("SELECT * from "+tmp+" ORDER BY id");
         //заголовки
         ResultSetMetaData rsmd = rs.getMetaData();
         String[] work= new String[rsmd.getColumnCount()]; 
         tab2.grid.removeAll();//очистим 2ю вкладку
         for (int i=0;i<rsmd.getColumnCount();i++) 
         {
            work[i]=rsmd.getColumnName(i+1);
            if (i!=0)
            {
               JLabel lab = new JLabel(rsmd.getColumnName(i+1));
               lab.setName(rsmd.getColumnName(i+1));
               tab2.grid.add(lab);   
               //это код для возможности выбора ФК не по ID, как в БД, а по имени, которое за ним стои
               //так сказать юзер-френдли. Допустим чтобы при заполнении таблицы с журналом
               //пользователь вводил не маршрут:1, а маршурт:1) Центр-Парк
               //
               //если лэйбл, один из указанных, то им должен быть назначет соотв. комбобокс
               if (lab.getText().equals("personnel_id")
                     |lab.getText().equals("auto_id")
                     |lab.getText().equals("route_id"))
               {
                  JComboBox txt = new JComboBox();
                  DefaultComboBoxModel frModel = new DefaultComboBoxModel();
                  frModel.addElement("");
                  if (lab.getText().equals("personnel_id"))
                  {
                     ResultSet rs1 = frame.stmt.executeQuery("SELECT * from auto_personnel");
                     while (rs1.next())
                        frModel.addElement(rs1.getString(1)+")"+
                              rs1.getString(2)+" "+
                              rs1.getString(3));
                  }
                  if (lab.getText().equals("auto_id"))
                  {
                     ResultSet rs1 = frame.stmt.executeQuery("SELECT * from auto");
                     while (rs1.next())
                        frModel.addElement(rs1.getString(1)+")"+
                              rs1.getString(2));
                  }
                  if (lab.getText().equals("route_id"))
                  {
                     ResultSet rs1 = frame.stmt.executeQuery("SELECT * from routes");
                     while (rs1.next())
                        frModel.addElement(rs1.getString(1)+")"+
                              rs1.getString(2));
                  }
                  
                  txt.setModel(frModel);
                  tab2.grid.add(txt);
               }
               else// и перерисуемe
               {
                  JTextField txt = new JTextField();
                  txt.setPreferredSize(new Dimension(200, 20));
                  tab2.grid.add(txt);
               }
            }
         }
         tab2.add(tab2.grid);//добавим новую форму
         tab2.revalidate();// и перерисуем
         tab2.repaint();
         columns = work;
         tableModel.setColumnIdentifiers(columns);//ставим новые заголовки
         //добовляем ряды в таблицу из запроса
         while ( rs.next() ) 
         {
            for (int i=0;i<rsmd.getColumnCount();i++) 
               work[i]=rs.getString(i+1);
            tableModel.addRow(work);
         }
         rs.close();
      }
      catch (SQLException ex){ex.printStackTrace();}
   }
}

public class Main extends JFrame
{
   private Tab1 t1;
   private Tab2 t2;
   private JTabbedPane tp;
   private String user;
   private String password;
   protected Connection conn=null;
   protected Statement stmt;
   protected JPanel buttons;
   protected JButton add;  
   protected JButton del;  
   protected JButton edit; 
   protected JComboBox tableCombo;

   public Main(String user, String password)  
   {
      super("auto_park"); 
      this.user = user; //юзер, пароль передадутся при авторизации
      this.password = password;
      //пробуем соединиться, если нет, то завершаем конструктор
      try 
      {
         conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/auto_park",user,password);
         stmt = conn.createStatement();//(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	   }   
      catch (SQLException e) 
      {
         System.out.println("Connection faild!");
		   return;
	   }

      setLayout(new BorderLayout()); //раскладк

      //добавлем лисененры на элеенты и элеенты на панель
      buttons = new JPanel();
      add = new JButton("Add");
      add.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e) 
         {
            t2.insert();
         }
      });
      del = new JButton("Del");
      del.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e) 
         {
            t1.delRow();
         }
      });
      edit = new JButton("Edit");
      edit.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e) 
         {
            t2.edit();
         }
      });

      //выдвижной список (что-то небезопасное)
      tableCombo = new JComboBox();
      DefaultComboBoxModel frModel = new DefaultComboBoxModel();
      frModel.addElement("auto");
      frModel.addElement("auto_personnel");
      frModel.addElement("journal");
      frModel.addElement("routes");
      tableCombo.setModel(frModel);
      tableCombo.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e) 
         {
            t1.initTable();
         }
      });

      buttons.add(tableCombo);
      buttons.add(add);
      buttons.add(del);
      buttons.add(edit);
      add(buttons, "North");

      tp = new JTabbedPane();
      t1 = new Tab1();
      t2 = new Tab2();
      t1.setSource(this);
      t2.setSource(this);
      t2.setTab1(t1);
      t1.setTab2(t2);
      t1.initTable();
      tp.add("Get", t1);
      tp.add("Add", t2);

      //действия при закрытии
      this.addWindowListener(new WindowAdapter()
      {
         @Override
         public void windowClosing(WindowEvent e)
         {
            super.windowClosing(e);
            try
            {
               conn.close();
               stmt.close();
            }
            catch (SQLException ex) {ex.printStackTrace();}
         }
      });

      add(tp);
      setSize(600, 500);
      setLocationRelativeTo(null);//по центру экрана
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   }

   public static void main(String[] args) 
   {
      Reg reg = new Reg();
   }   
}
