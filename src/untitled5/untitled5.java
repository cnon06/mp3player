package untitled5;



import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;



import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class untitled5 extends JFrame

{
    static float divide2=0,vlm3=50;
    static long durration;
    static boolean start_stop=false, dont_play_again=false, stop_start_stop=false,dindong=false,exit_enter=false, change_value_stop=false, jb6_font_color=false,jb7_font_color=false, dont_repeat=false;
    static int count =0,start=0,jb4x=0,X,Y,dragX,locationX=20,XVolume,YVolume, GetXvolume, timeline,timeX,ctrl1=-5, ik=0;
    static String outcome="40", jk [], list5[], path;
    FileInputStream fis;
   JButton jb1;
    JLabel jl3, jl1,jl6,jl7,jl4,jl9,jl2,jl8,jl5;
   //JTextField tf1,tf2;
    JList jli1;
    Object selected;
    JScrollPane jp1;
    JPanel panel1;
    int listlength;
    HashMap<String, String> directories_and_files = new HashMap<>();
    File src1;

    MyFileVisitor visitor;

    untitled5()
    {


        jb4x=locationX;

        setTitle("MP3 Player with JAVA by Sinan");
        setSize(680,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);



        setLocationRelativeTo(this);
        setLayout(null);


          panel1 = new JPanel();

        panel1.setSize(getSize());
        panel1.setLayout(null);

        panel1.setBackground(Color.WHITE);
        panel1.setLocation(0,0);
        //panel1.update();


        list_read();
       // list_add();
        path = "mp3\\";
        File folder = new File(path);
        File list[] = folder.listFiles();
       list5 = new String [directories_and_files.size()];

        //list5 = new String [directories_and_files.size()];

      //listlength = list.length;
        listlength = directories_and_files.size();

//System.out.println("4522: "+directories_and_files.size());



       // if(!source.exists()) source=new File("friyingpan.mp3");
ik=0;
        directories_and_files.forEach((k, v) -> {
            //  System.out.println("File: "+k+" Path: "+v);

            try
            {

                src1 = new File(v+k);

                if(src1.exists())
                {
                    list5[ik] = k;
                    ik++;
                }

              //System.out.println("1143: "+k);
               // ((DefaultListModel)jli1.getModel()).addElement(k);
                //  fileWriter.write(k+"d45a689t90a"+v+"\n");
            }
            catch (Exception er)
            {
                System.out.println("7233: "+er);
            }


            //((DefaultListModel)jli1.getModel()).addElement(k);
        });
        if(ik==0)
        {
            list5= new String[1];
            list5 [0] = "friyingpan.mp3";
            directories_and_files.clear();
            directories_and_files.put("friyingpan.mp3","");
            dont_repeat=true;
            Go(0);

           dont_repeat=true;


        }

        path=directories_and_files.get(list5[0]);



/*
 for(int i=0;i<list.length;i++)
      {
          list5 [i] = list[i].toString().substring(list[i].toString().lastIndexOf("\\")+1);

          String get_directory = list[i].toString().substring(0, list[i].toString().lastIndexOf("\\")+1);


          directories_and_files.put(list5[i],get_directory);



      }
 */



   //     System.out.println("2389 directories_and_files: "+directories_and_files.size());
    //    System.out.println("2389 listlength: "+listlength);





     //jli1 = new JList(list5);
       //jli1 = new JList();



        jli1 = new JList<String>(new DefaultListModel<String>());

        list_add();

        jli1.setBackground(Color.GRAY);
        jli1.setSelectedIndex(0);
        jli1.setSelectionMode(0);
      //  jli1.setValueIsAdjusting(true);
       // DefaultListModel model = (DefaultListModel) jli1.getModel();
       // model.removeElement(0);
       // jli1.set




        jli1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                dindong=false;
                //  super.mousePressed(e);
            }
        });

        jli1.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {


                jli1.setModel(new DefaultListModel());
                change_value_stop=true;
                dont_repeat=false;

                String get_app="",get_app2="";

                directories_and_files.clear();




                try {

                    jb1.setText("Play");
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    java.util.List<File> droppedFiles = (List<File>) evt
                            .getTransferable().getTransferData(
                                    DataFlavor.javaFileListFlavor);

                    ((DefaultListModel)jli1.getModel()).removeAllElements();



                    for (File file : droppedFiles) {




                      // System.out.println("exempt 653434:"+file.getCanonicalPath());

                    String get_em =file.toString();


                        path=get_em+"\\";
                        //path=get_em;
//System.out.println("Exempt 5643: "+path);





                        get_app2=file.getAbsolutePath();



                        if(file.isFile())
                        {

                            String get_em2=file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".")+1);

                            if(get_em2.equals("mp3"))
                            {
                                //System.out.println("exempt 2331:"+get_em2);
                                String get_app3=file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\")+1);

                                ((DefaultListModel)jli1.getModel()).addElement(get_app3);

                                String get_directory = file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf("\\")+1);


                                directories_and_files.put(get_app3,get_directory);
                            }




                        }
 




                        if(file.isDirectory() && file.canRead())
                        {

                            //$RECYCLE.BIN
                         //  if(path.contains("$RECYCLE.BIN")) System.out.println("75231: "+path.contains("$RECYCLE.BIN"));

                         //File file_can_read = new File(path);

                        list_directory(path);






                        }

Go(0);
                    }










                    listlength= jli1.getModel().getSize();

                    jli1.setSelectedIndex(0);




                    //System.out.println("Exempt 5648: "+get_app);
                    get_app=get_app2.substring(get_app2.lastIndexOf("\\")+1);



                } catch (Exception ex) {
                    System.out.println("Error code 21:"+ex);
                    //System.out.println(ex);
                    ex.printStackTrace();



                }




            }
        });


        jli1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {




                boolean adjust = e.getValueIsAdjusting();

              if(!change_value_stop)
              {
                  if(!adjust )
                  {

                      JList list2 = (JList) e.getSource();
                      selected = list2.getSelectedValue();
                      if(!dindong)
                      {
                          count=0;
                          start = (int) (count*1000 / 26);
                          selected();
                          //stop();
                          //  info();
                      }

                      //   System.out.println("exmp8: "+path+selected);

                  }
              }



                else
                {
                    stop();
                   // change_value_stop=false;
                    new Thread(){

                        @Override
                        public void run() {

                            try
                            {

                                sleep(100);

                                change_value_stop=false;

                                //System.out.println("selected index: "+jli1.getSelectedIndex());
                                //System.out.println("first visible index: "+jli1.getFirstVisibleIndex());
                                //System.out.println("last visible index: "+jli1.getLastVisibleIndex());



                            }
                            catch (Exception ed)
                            {
                                System.out.println(ed);
                            }

                        }
                    }.start();


                }

            }
        });
                jp1 = new JScrollPane();
        jp1.setViewportView(jli1);
        jli1.setLayoutOrientation(JList.VERTICAL);
       // jp1.getVerticalScrollBar().setValue(500);
        //jp1.getVerticalScrollBar().setValue(jp1.getVerticalScrollBar().getMaximum());


        jp1.setSize(225,140);
        jp1.setLocation(400+locationX,10);
        jp1.setVisible(true);


        panel1.add(jp1);

        //jp1.paintComponents();

        JLabel jl11 = new JLabel("Duration:");
        jl11.setVisible(true);
        jl11.setSize(80,20);
        jl11.setLocation(locationX+200,10);

        panel1.add(jl11);

       JLabel jl10 = new JLabel("Counter:");
        jl10.setVisible(true);
        jl10.setSize(80,20);
        jl10.setLocation(locationX,10);

        panel1.add(jl10);


        jl1 = new JLabel("00:00");
        jl1.setVisible(true);
        jl1.setSize(80,20);
        jl1.setLocation(100+locationX,10);

        panel1.add(jl1);

        jl6 = new JLabel("Duration");
        jl6.setVisible(true);
        jl6.setSize(80,20);
        jl6.setLocation(300+locationX,10);

        panel1.add(jl6);


        jl7 = new JLabel("Title");
        jl7.setVisible(true);
        jl7.setSize(400,20);
        jl7.setLocation(locationX,80);

        panel1.add(jl7);
        



        JButton jb3 = new JButton("Next");
        jb3.setVisible(true);
        jb3.setSize(80,20);
        jb3.setLocation(300+locationX,50);

        panel1.add(jb3);

        jb3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

               next();
            }
        });



        JButton jb4 = new JButton("Previous");
        jb4.setVisible(true);
        jb4.setSize(80,20);
        jb4.setLocation(+locationX,50);

        panel1.add(jb4);

        jb4.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

               previous();
            }
        });





        
       jb1 = new JButton("Play");
        jb1.setVisible(true);
        jb1.setSize(80,20);
        jb1.setLocation(100+locationX,50);

       panel1.add(jb1);
       jb1.addMouseListener(new MouseAdapter() {
           @Override
           public void mousePressed(MouseEvent e) {


               if(jb1.getText()=="Play" && !dont_play_again)
               {
                   start();

               }
                   else
               {


                   pause();

               }

               }

       });

        JButton jb2 = new JButton("Stop");
        jb2.setVisible(true);
        jb2.setSize(80,20);
        jb2.setLocation(200+locationX,50);

        jb2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            stop();
                jb1.setText("Play");

            }
        });

        panel1.add(jb2);


         jl9 = new JLabel("Timeline");
        jl9.setVisible(false);
        jl9.setSize(30,15);
        jl9.setLocation(locationX,120);
        jl9.setOpaque(true);
        //jl9.set
        jl9.setBackground(Color.WHITE);
        jl9.setFont(new Font("Serif", Font.PLAIN, 9));
        Border border2 = BorderFactory.createLineBorder(Color.BLACK, 1);
        jl9.setBorder(border2);

        panel1.add(jl9);


        jl3 = new JLabel("Sinan");
        jl3.setVisible(true);
        jl3.setSize(10,20);
        jl3.setLocation(locationX,130);
       ImageIcon img1 = new ImageIcon("redline.png");
        jl3.setIcon(img1);
        panel1.add(jl3);

        jl3.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {


exit_enter=true;
                    X= e.getX();
                Y=e.getY();

                stop4();


                 //stop();
                //Go(timeline);
               // timeline=Integer.pa
                jl9.setVisible(true);

            }

            @Override
            public void mouseReleased(MouseEvent e) {


                Go(timeline);
                //Go(jl9.getText());
                jl9.setVisible(false);
                exit_enter=false;

            }

            public void mouseExited(MouseEvent e) {

             if(!exit_enter)  jl9.setVisible(false);

            }
            public void mouseEntered(MouseEvent e) {

                 jl9.setVisible(true);

            }

        });

        jl3.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {


                int x2=(e.getX()+e.getComponent().getX())-X;
                if(x2<locationX) x2=+locationX;
                if(x2>370+locationX) x2=370+locationX;

                e.getComponent().setLocation(x2,130);

                dragX=e.getComponent().getX();



               timeline(jl3.getX()-2*jl3.getWidth()+e.getX());
                count=timeline;

            }


            public void mouseMoved(MouseEvent e) {

                timeline(jl3.getX()-2*jl3.getWidth()+e.getX());


            }


        });



        
        jl2 = new JLabel("Sinan");
        jl2.setVisible(true);
        jl2.setSize(380,20);
        jl2.setLocation(locationX,129);
        ImageIcon img2 = new ImageIcon("line.jpg");
        jl2.setIcon(img2);
        panel1.add(jl2);


        jl2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {

                if(!exit_enter)     jl9.setVisible(false);

            }
            public void mouseEntered(MouseEvent e) {

                jl9.setVisible(true);

            }

            public void mousePressed(MouseEvent e) {




                X= e.getX();
                Y=e.getY();
                stop4();


               dragX =timeX+15;
                Go(timeline);





                // 0.05813 should be calculated when locationX is changed, it depends on the float difference that is between two songs and the location of processing bar.




            }

            @Override
            public void mouseReleased(MouseEvent e) {



            }

            public void mouseClicked(MouseEvent e) {




            }

        });

        jl2.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {

              timeline(e.getX());


            }



        });



        jl8 = new JLabel("Volume Percent");
        jl8.setVisible(false);
        jl8.setSize(30,15);
        jl8.setLocation(locationX,104);
        jl8.setOpaque(true);
        jl8.setBackground(Color.WHITE);
        jl8.setFont(new Font("Serif", Font.PLAIN, 9));
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        jl8.setBorder(border);

        panel1.add(jl8);





        jl4 = new JLabel("");
        jl4.setVisible(true);
        jl4.setSize(7,21);
        jl4.setLocation(90,105);
        ImageIcon img3 = new ImageIcon("slider.png");
        jl4.setIcon(img3);
        panel1.add(jl4);

        jl4.addMouseListener(new MouseAdapter() {


            public void mouseEntered(MouseEvent e) {
                jl8.setVisible(true);
            }

            public void mouseExited(MouseEvent e) {
                jl8.setVisible(false);
            }

            public void mousePressed(MouseEvent e) {

                XVolume= e.getX();
                YVolume=e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {



                double converse = (GetXvolume-locationX)*1.1627;
                converse = Math.ceil(converse);


                volume_up_down((int)converse*(0.01F));
               // volume_write((int)converse);
            }



        });

        jl4.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {



                   jl8.setVisible(true);
                int x2=(e.getX()+e.getComponent().getX())-XVolume;
                if(x2<locationX) x2=+locationX;
                if(x2>86+locationX) x2=86+locationX;

                e.getComponent().setLocation(x2,105);

               GetXvolume=e.getComponent().getX();



                int volume_percent=(int)Math.ceil((GetXvolume-locationX)*1.1627);
                jl8.setText("% "+volume_percent);

                jl8.setLocation(jl4.getX()+10,jl4.getY()+3);



            }

            public void mouseMoved(MouseEvent e) {

                jl8.setLocation(e.getX()+jl4.getX()+5,jl4.getY()+3);

                int vol_per=(100*(e.getX()+(jl4.getX()-locationX))/(jl5.getWidth()-2));
                if(vol_per>100) vol_per=100;
                if(vol_per<0) vol_per=0;
                jl8.setText("%"+vol_per);

                jk  = jl8.getText().split("%");


            }

        });


        jl5 = new JLabel();
        jl5.setVisible(true);
        jl5.setSize(93,21);
        jl5.setLocation(locationX,104);
        ImageIcon img4 = new ImageIcon("volume_bar.png");
        jl5.setIcon(img4);


        jl5.addMouseListener(new MouseAdapter() {
            @Override

            public void mouseEntered(MouseEvent e) {
            jl8.setVisible(true);
            }

            public void mouseExited(MouseEvent e) {
                jl8.setVisible(false);
            }

            public void mouseClicked(MouseEvent e) {
                vlm3 = Integer.parseInt(jk[1]);


                volume_up_down(vlm3*(0.01F));

                //  volume_write((int)vlm3);

            }

            public void mousePressed(MouseEvent e) {
                vlm3 = Integer.parseInt(jk[1]);


                volume_up_down(vlm3*(0.01F));


            }

            public void mouseReleased(MouseEvent e) {

            }

        });

        jl5.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {

                jl8.setLocation(e.getX()+25,jl4.getY()+3);
                int vol_per=(100*e.getX()/(jl5.getWidth()-2));
                if(vol_per>100) vol_per=100;
                jl8.setText("%"+vol_per);

                jk  = jl8.getText().split("%");

            }
        });


        jl5.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int jl4_locx =e.getX()+locationX;
                if(jl4_locx+jl4.getWidth()>jl5.getWidth()+locationX)
                    jl4_locx=jl5.getWidth()+locationX-jl4.getWidth();

                jl4.setLocation(jl4_locx,jl4.getY());

            }
        });

        panel1.add(jl5);


        JButton jb5 = new JButton("Ascending");
        jb5.setVisible(true);
        jb5.setSize(80,20);
        jb5.setLocation(300+locationX,105);

        panel1.add(jb5);

        jb5.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {


                //JList jl = new JList(new Object[]{4.5,1,"Hi!"});
                // ListModel model = jli1.getModel();
                //String[] strings = new String[model.getSize()];
                //((DefaultListModel)jli1.getModel()).addElement(get_app);


                String[] strings = new String[jli1.getModel().getSize()];
                //  jli1.getModel().getSize();
                for(int i=0;i<strings.length;i++){
                    strings[i]=jli1.getModel().getElementAt(i).toString();
                }

                if(jb5.getText().equals("Ascending"))
                {
                    Arrays.sort(strings);
                    jb5.setText("Descending");
                }

                else
                {
                    Arrays.sort(strings, Collections.reverseOrder());
                    jb5.setText("Ascending");
                }

                // Arrays.
                jli1.setModel(new DefaultListModel());
                jli1.setListData(strings);
                //  next();


                // jli1.getModel().
                change_value_stop=true;
                jli1.setSelectedIndex(0);
               // jb1.setText("Play");
                Go(0);





            }
        });

        JButton jb6 = new JButton("Shuffle");
        jb6.setVisible(true);
        jb6.setSize(80,20);
        jb6.setLocation(200+locationX,105);

        panel1.add(jb6);

        jb6.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {


                //Font font = new Font("Verdana", Font.BOLD, 12);
                Font font = new Font(jb6.getFont().getName(), Font.BOLD, jb6.getFont().getSize());


                if(!jb6_font_color)
                {
                    jb6.setFont(font);
                    jb6.setForeground(Color.RED);
                    jb6_font_color=true;
                }
             else
                {
                    jb6.setFont(font);
                    jb6.setForeground(Color.BLACK);
                    jb6_font_color=false;
                }




            }
        });


        JButton jb7 = new JButton("Repeat");
        jb7.setVisible(true);
        jb7.setSize(80,20);
        jb7.setLocation(100+locationX,105);

        panel1.add(jb7);

        jb7.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {


                //Font font = new Font("Verdana", Font.BOLD, 12);
                Font font = new Font(jb7.getFont().getName(), Font.BOLD, jb7.getFont().getSize());


                if(!jb7_font_color)
                {
                    jb7.setFont(font);
                    jb7.setForeground(Color.RED);
                    jb7_font_color=true;
                }
                else
                {
                    jb7.setFont(font);
                    jb7.setForeground(Color.BLACK);
                    jb7_font_color=false;
                }




            }
        });
        
      volume_read();

        try
        {
            vlm3=Integer.parseInt(outcome);
        }
        catch (Exception e)
        {
            vlm3=50; //System.out.println(e);
            System.out.println("Error code 22:"+e);
        }

        volume_up_down(vlm3*(0.01F));

        int xstart =locationX+(int)((double)vlm3*0.86);
        jl4.setLocation(xstart,105);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

            volume_write();



               /*
                try(FileWriter fileWriter = new FileWriter( "volume_record.txt")) {

                    fileWriter.write(vlm.toString());
                } catch (IOException ej) {

                }
                */



            //    System.out.println("WindowClosingDemo.windowClosing");
                System.exit(0);
            }
        });

        info();





        /*
            if (jli1.getSelectedIndex() - 1 < jli1.getFirstVisibleIndex()) {
            // jp1.getViewport().setViewPosition(new Point(0,(int)jp1.getViewport().getViewPosition().getY()+100));

            jp1.getVerticalScrollBar().setValue(jp1.getVerticalScrollBar().getValue() - 1);

            panel1.repaint();
            //jli1.set;
        }

         */




        volume_read();
        start = (int) (count*1000 / 26);
        jb4x=locationX+(int)(((370)*count)/(durration/1000));
        jl3.setLocation(jb4x,130);

        try
        {
            vlm3=Integer.parseInt(outcome);
        }
        catch (Exception e)
        {
            vlm3=60;

        }


        volume_up_down(vlm3*(0.01F));
        jl1.setText(convert_to_minute(count));
        //start();

        //pause();



        add(panel1);


        panel1.repaint();

/*
 new Thread(){

            @Override
            public void run() {

                try
                {

                    sleep(100);
                    System.out.println("selected index: "+jli1.getSelectedIndex());
                    System.out.println("first visible index: "+jli1.getFirstVisibleIndex());
                    System.out.println("last visible index: "+jli1.getLastVisibleIndex());



                }
                catch (Exception ed)
                {
                    System.out.println(ed);
                }

            }
        }.start();
 */


        setVisible(true);

        Thread tgh3 = new Thread();

        try
        {
            tgh3.sleep(100);
           // System.out.println("selected index: "+jli1.getSelectedIndex());
           // System.out.println("first visible index: "+jli1.getFirstVisibleIndex());
          //  System.out.println("last visible index: "+jli1.getLastVisibleIndex());
        }
        catch (Exception hj5)
        {
            System.out.println("Error code 23:"+hj5);
           // System.out.println(hj5);
        }


        while (jli1.getSelectedIndex() -1 < jli1.getFirstVisibleIndex())
        {
           // System.out.println("First visible index:"+jli1.getFirstVisibleIndex()+" Selected index: "+jli1.getSelectedIndex());
            jp1.getVerticalScrollBar().setValue(jp1.getVerticalScrollBar().getValue() - 10);
          // if(ctrl1> jp1.getVerticalScrollBar().getValue()) break;
            ctrl1= jp1.getVerticalScrollBar().getValue();


            if(jli1.getFirstVisibleIndex()==0)
            {
                jp1.getVerticalScrollBar().setValue(jp1.getVerticalScrollBar().getValue() - 10);
              // fg3=true;
                break;
                //jp1.getVerticalScrollBar().setValue(jp1.getVerticalScrollBar().getValue() - 10);
            }


          //  if(jli1.getSelectedIndex() +1== jli1.getFirstVisibleIndex()) break;
        }

          // System.out.println("loop is over");


        while (jli1.getSelectedIndex() +1 > jli1.getLastVisibleIndex())
        {
            //System.out.println("First visible index:"+jli1.getLastVisibleIndex()+" Selected index: "+jli1.getSelectedIndex());
            jp1.getVerticalScrollBar().setValue(jp1.getVerticalScrollBar().getValue() + 10);
            // if(ctrl1> jp1.getVerticalScrollBar().getValue()) break;
            ctrl1= jp1.getVerticalScrollBar().getValue();


            if(jli1.getLastVisibleIndex()==list.length-1)
            {
                jp1.getVerticalScrollBar().setValue(jp1.getVerticalScrollBar().getValue() + 10);
                // fg3=true;
                break;
                //jp1.getVerticalScrollBar().setValue(jp1.getVerticalScrollBar().getValue() - 10);
            }


            //  if(jli1.getSelectedIndex() +1== jli1.getFirstVisibleIndex()) break;
        }

        //System.out.println("loop is over");

           panel1.repaint();
           //jp1.getVerticalScrollBar().setValue(jp1.getVerticalScrollBar().getValue() - 10);




    }


    public void shuffle()
    {


        if(!dont_repeat)
        {

            if( !jb7_font_color)
            {
                if( !jb6_font_color)
                {
                    next();
                }

                else
                {
                    Random rand = new Random();





                    int number_of_list_items=jli1.getModel().getSize();
                    int rand_int1 = rand.nextInt(number_of_list_items);
                    //jli1.setSelectedIndex(0);
                    //stop();
                    jli1.setSelectedIndex(rand_int1);
                    //jli1.setSelectedIndex(800);






                    //System.out.println("765665: "+jli.i);



                    // System.out.println("r54356r: "+(rand_int1)+" list length: "+number_of_list_items );
                    Go(0);


                    boolean tg=false;
                    while (!tg)
                    {
                        if (jli1.getSelectedIndex() - 1 < jli1.getFirstVisibleIndex()) {
                            // jp1.getViewport().setViewPosition(new Point(0,(int)jp1.getViewport().getViewPosition().getY()+100));

                            jp1.getVerticalScrollBar().setValue(jp1.getVerticalScrollBar().getValue() - 1);

                            panel1.repaint();
                            //jli1.set;
                        }
                        else break;
                    }


                    //tg=false;
                    while (!tg)
                    {
                        if (jli1.getSelectedIndex() + 1 > jli1.getLastVisibleIndex()) {
                            // jp1.getViewport().setViewPosition(new Point(0,(int)jp1.getViewport().getViewPosition().getY()+100));

                            jp1.getVerticalScrollBar().setValue(jp1.getVerticalScrollBar().getValue() + 1);

                            panel1.repaint();
                            //jli1.set;
                        }
                        else break;
                    }



                    // next();
                }
            }
            else
            {
                Go(0);
            }
        }
       else
        {
           stop(); //dont_repeat=false;
        }



    }

    public void go_volume_bar()
    {


        start();



        }



    public void list_directory(String file3)
    {

        





         // try (Stream<Path> walk = Files.walk(Paths.get(file3)))

       /*

                DirectoryStream<Path> directoryStream = Files.newDirectoryStream(file6);

                for (Path p : directoryStream) {
                    System.out.println(p.getFileName());
                }
        */






        try
        {

           Path fileDir = Paths.get("d:\\");
           fileDir = Paths.get(file3);
           visitor = new MyFileVisitor();


            Files.walkFileTree(fileDir,visitor);
            directories_and_files = visitor.directories_and_files;
            directories_and_files.forEach((k, v) -> {
                //System.out.println("File: "+k+" Path: "+v);
                ((DefaultListModel)jli1.getModel()).addElement(k);
            });


            /*

             Stream<Path> walk = Files.walk(Paths.get(file3));




                 List<String> result = walk.map(x -> x.toString())
                        .filter(f -> f.endsWith(".mp3")).collect(Collectors.toList());

                for (String files : result)

                {


                    String get_app=files.substring(files.lastIndexOf("\\")+1);

                    ((DefaultListModel)jli1.getModel()).addElement(get_app);

                    String get_directory = files.substring(0,files.lastIndexOf("\\")+1);


                    directories_and_files.put(get_app,get_directory);



                }

             */




                listlength= jli1.getModel().getSize();
                jli1.setSelectedIndex(0);

            } catch (IOException e) {
                System.out.println("Error code 1:"+e);
                e.printStackTrace();
            }




    }




    public void timeline(int getx)
    {


       timeX=getx;



        if(timeX>379) timeX=379;
       if(timeX<0) timeX=0;

        jl9.setLocation(timeX,120);

        timeline =(int) ((timeX*(durration/1000))/371);


        if(timeline>(int)(durration/1000)) timeline=(int)(durration/1000);


        jl9.setText(convert_to_minute(timeline));


    }


    public void list_read()
    {
        BufferedReader reader;
        try
        {

            reader = new BufferedReader(new FileReader("list_record.txt"));
            String line = reader.readLine();
            //  System.out.println(line);
            String[] parts;
            //  String part1;
            // String part2;


           do {
               // System.out.println("8675: "+line);
                parts = line.split("d45a689t90a");
                //    part1 = parts[0]; // 004
                //
                //  part2 = parts[1]; // 034556

             // System.out.println("753: "+parts[0]);
                directories_and_files.put(parts[0],parts[1]);
                //directories_and_files.put(get_app3,get_directory);

                // line = reader.readLine();



            }while ( (line = reader.readLine()) != null);


        }
        catch (Exception hg)
        {
            System.out.println("Error 432: "+hg);
        }
      //  listlength  =directories_and_files.size();

    }


    public void volume_read()
    {
        outcome="";
        try(FileReader fileReader = new FileReader( "volume_record.txt")) {
            int ch = fileReader.read();
            while(ch != -1) {


                outcome+=(char)ch;

                ch = fileReader.read();
            }
        } catch (Exception etr) {

            System.out.println("Error code 26:"+etr);
            //System.out.println(etr);
        }


     String outcome2="";

        try(FileReader fileReader = new FileReader( "counter_record.txt")) {
            int ch = fileReader.read();
            while(ch != -1) {


                outcome2+=(char)ch;

                ch = fileReader.read();
            }
        } catch (Exception etr) {
            System.out.println("Error code 2:"+etr);
        }

        try
        {

             count=Integer.parseInt(outcome2);

        }
        catch (Exception tye)
        {
            System.out.println("Error code 3:"+tye);
        }


        dindong=true;


        String outcome3="";

        try(FileReader fileReader = new FileReader( "selected_record.txt")) {
            int ch = fileReader.read();
            while(ch != -1) {


                outcome3+=(char)ch;

                ch = fileReader.read();
            }
        } catch (Exception etr) {
            System.out.println("Error code 4:"+etr);
        }

        try
        {
            for (int i=0;i<list5.length;i++)
            {
                if(outcome3.equals(list5[i]))  jli1.setSelectedIndex(i);
            }
          //  jli1.setSelectedIndex(Integer.parseInt(outcome3));
            //count=Integer.parseInt(outcome2);

        }
        catch (Exception tye)
        {
            jli1.setSelectedIndex(0);
            System.out.println("Error code 24:"+tye);
        }

        String outcome4="";

        try(FileReader fileReader = new FileReader( "scroll_record.txt")) {
            int ch = fileReader.read();
            while(ch != -1) {


                outcome4+=(char)ch;

                ch = fileReader.read();
            }
        } catch (Exception etr) {
            System.out.println("Error code 5:"+etr);
        }

        try
        {
            jp1.getVerticalScrollBar().setValue(Integer.parseInt(outcome4));
            //jli1.setSelectedIndex(Integer.parseInt(outcome4));
            //count=Integer.parseInt(outcome2);

        }
        catch (Exception tye)
        {
            jp1.getVerticalScrollBar().setValue(0);
            System.out.println("Error code 6:"+tye);
        }


/*
 String outcome5="";

        try(FileReader fileReader = new FileReader( "list_record.txt")) {
            int ch = fileReader.read();
            while(ch != -1) {


                outcome5+=(char)ch;

                ch = fileReader.read();
            }
        } catch (Exception etr) {
            System.out.println("Error code 567:"+etr);
        }

        System.out.println("3557: "+outcome5);
 */






        //System.out.println("2389 directories_and_files: "+directories_and_files.size());
        //System.out.println("2389 listlength: "+listlength);
      //  jp1.getVerticalScrollBar().setValue(jli1.getSelectedIndex()*11);


    }


    public void volume_write()
    {
        try(FileWriter fileWriter = new FileWriter( "volume_record.txt")) {

           fileWriter.write(""+(int)vlm3);
        } catch (IOException ej) {
            System.out.println("Error code 7:"+ej);
        }



           try(FileWriter fileWriter = new FileWriter( "counter_record.txt")) {

            fileWriter.write(""+count);
        } catch (IOException ej) {
               System.out.println("Error code 8:"+ej);
        }

        try(FileWriter fileWriter = new FileWriter( "selected_record.txt")) {


            fileWriter.write(""+jli1.getSelectedValue());
            //fileWriter.write(""+jli1.getSelectedIndex());
        } catch (IOException ej) {
            System.out.println("Error code 26:"+ej);
        }



        try(FileWriter fileWriter = new FileWriter( "scroll_record.txt")) {

            fileWriter.write(""+ jp1.getVerticalScrollBar().getValue());
        } catch (IOException ej) {
            System.out.println("Error code 9:"+ej);
        }

        try(FileWriter fileWriter = new FileWriter( "list_record.txt")) {

           // fileWriter.write(""+ jp1.getVerticalScrollBar().getValue());

            directories_and_files.forEach((k, v) -> {
              //  System.out.println("File: "+k+" Path: "+v);

                try
                {
                    fileWriter.write(k+"d45a689t90a"+v+"\n");
                }
                catch (Exception er)
                {
                    System.out.println("7233: "+er);
                }


                //((DefaultListModel)jli1.getModel()).addElement(k);
            });

        } catch (IOException ej) {
            System.out.println("Error code 964:"+ej);
        }


    }


    public void Go(int gogo)
    {
        stop();

        count = gogo;



        if(count>durration/1000 || count<0)
        {
            count=1;
            //tf1.setText("1");
        }

        start = (int) (count*1000 / 26);
        if(count==1) start();


        new Thread(){

            @Override
            public void run() {

                try
                {

                    sleep(500);

                    go_volume_bar();



                    new Thread(){

                        @Override
                        public void run() {

                            try
                            {

                                sleep(500);

                                go_volume_bar();


                                new Thread(){

                                    @Override
                                    public void run() {

                                        try
                                        {

                                            sleep(500);

                                            go_volume_bar();

                                            new Thread(){

                                                @Override
                                                public void run() {

                                                    try
                                                    {

                                                        sleep(500);

                                                        go_volume_bar();

                                                    }
                                                    catch (Exception ed)
                                                    {
                                                        System.out.println("Error code 10:"+ed);
                                                    }

                                                }
                                            }.start();

                                        }
                                        catch (Exception ed)
                                        {
                                            System.out.println("Error code 11:"+ed);
                                        }

                                    }
                                }.start();

                            }
                            catch (Exception ed)
                            {
                                System.out.println("Error code 12:"+ed);
                            }

                        }
                    }.start();

                }
                catch (Exception ed)
                {
                    System.out.println("Error code 13:"+ed);
                }

            }
        }.start();




    }


    public  void  list_add()
    {

/*
        directories_and_files.forEach((k, v) -> {
            //  System.out.println("File: "+k+" Path: "+v);


              try
            {

                ((DefaultListModel)jli1.getModel()).addElement(k);

            }
            catch (Exception er)
            {
                System.out.println("7233: "+er);
            }




        });
        listlength=directories_and_files.size();
             */


         try
        {
            for ( String list8:list5)
            {

               // System.out.println(list8);
               ((DefaultListModel)jli1.getModel()).addElement(list8);
            }
        }
        catch (Exception es)
        {
            System.out.println("Error code 14:"+es);
        }







    }

public void pause()
{
    if(stop_start_stop) {

        start_stop = true;
        start = (int) (count*1000 / 26);
        jb1.setText("Play");

    }
}






        public void previous() {
            jb1.setText("Play");


            if (jli1.getSelectedIndex() == 0)

                jli1.setSelectedIndex(listlength - 1);

            else {
                jli1.setSelectedIndex(jli1.getSelectedIndex() - 1);
                selected = jli1.getSelectedValue();


            }

            if (jli1.getSelectedIndex() - 1 < jli1.getFirstVisibleIndex()) {
                // jp1.getViewport().setViewPosition(new Point(0,(int)jp1.getViewport().getViewPosition().getY()+100));

                jp1.getVerticalScrollBar().setValue(jp1.getVerticalScrollBar().getValue() - 100);

                panel1.repaint();
                //jli1.set;
            }

       if (listlength - 1 == jli1.getSelectedIndex())
       {
           jp1.getVerticalScrollBar().setValue(jp1.getVerticalScrollBar().getMaximum());
       }

        //jli1.getSelectedIndex()
            Go(0);

    }


    public void stop2()
    {
        stop();
    }

    public void next()
    {
        jb1.setText("Play");

        if(listlength-1==jli1.getSelectedIndex())

            jli1.setSelectedIndex(0);
        else
        {
            jli1.setSelectedIndex(jli1.getSelectedIndex()+1);
            selected=jli1.getSelectedValue();
        }


        //System.out.println(jli1.getFirstVisibleIndex());

  if(jli1.getSelectedIndex()+1>jli1.getLastVisibleIndex())
        {


            jp1.getVerticalScrollBar().setValue(jp1.getVerticalScrollBar().getValue()+100);

            panel1.repaint();

        }



        if(0==jli1.getSelectedIndex())
        {
            jp1.getVerticalScrollBar().setValue(0);
            panel1.repaint();
        }

//stop();
Go(0);

    }



    public String convert_to_minute(int drr)
    {

        int minute = drr/60;
        int second = drr%60;

        String minute_space="";
        String second_space="";
        if (minute<10) minute_space="0"; else minute_space="";
        if (second<10) second_space="0"; else second_space="";
        return minute_space+minute+":"+second_space+second;
    }


    public void volume_up_down(float ctrl)
    {


         try {
            Mixer.Info[] infos = AudioSystem.getMixerInfo();
            for (Mixer.Info info: infos)
            {
                Mixer mixer = AudioSystem.getMixer(info);
                if (mixer.isLineSupported(Port.Info.SPEAKER))
                {
                    Port port = (Port)mixer.getLine(Port.Info.SPEAKER);
                    port.open();
                    if (port.isControlSupported(FloatControl.Type.VOLUME))
                    {
                        FloatControl volume =  (FloatControl)port.getControl(FloatControl.Type.VOLUME);
                        volume.setValue(ctrl);
                    }
                    port.close();
                }
            }
        } catch (Exception e) {

         System.out.println("Error code 15:"+e);
            JOptionPane.showMessageDialog(null,"Erro\n"+e);
        }

    }


    public void start2() {
    start();
    }

    public void start()
    {


        if(!dont_play_again)
        {

            stop_start_stop=true;
            dont_play_again=true;
            try{

                //System.out.println("exmpt 9: "+path+jli1.getSelectedValue().toString());
               path=directories_and_files.get(jli1.getSelectedValue());
                if(jli1.getSelectedValue()!=null) fis = new FileInputStream(path+jli1.getSelectedValue().toString());

                info();
                AdvancedPlayer playMP3 = new AdvancedPlayer(fis);
                jb1.setText("Pause");


                playMP3.setPlayBackListener(new PlaybackListener() {
                    @Override
                    public void playbackFinished(PlaybackEvent evt) {


                        shuffle();

                        //stop2();
                        //Go(0);
                     //next();

                    }
                });


                new Thread()
                {

                    @Override
                    public void run() {

                        while (!start_stop)
                        {
                            count++;


                                if(count>(durration/1000))
                            {

                                shuffle();
                                // next();




                               /*

                                stop2();
                                Go(0);

                                */


                            }


                            try
                            {
                                Thread.sleep(1000);


                                if(count<0) count=0;
                                jb4x=locationX+(int)(((370)*count)/(durration/1000));


                                if(jb4x>390)  jb4x = 390;
                                    //jl3.setLocation(369,130);

                                if(jb4x<locationX) jb4x = locationX;


                                    jl3.setLocation(jb4x,130);


                            }
                            catch (Exception hj)
                            {
System.out.println("Error code 16:hj");
                            }




                           jl1.setText(convert_to_minute(count));
                        }


                        playMP3.close();

                        start_stop=false;
                        stop_start_stop=false;


                        jl1.setText(convert_to_minute(count));
                        dont_play_again=false;


                    }
                }.start();


                new Thread(){

                    @Override
                    public void run() {


                        try
                        {
                            playMP3.play(start,(int)durration);
                        }
                        catch (Exception yu)
                        {
                            //stop2();
                            jb1.setText("Play");
                            System.out.println("Error code 17:"+yu);


                        }

                    }
                }.start();

            }catch(Exception ey){
                //System.out.println(ey);
                System.out.println("Error code 18:"+ey+" Duration"+durration);
             stop_start_stop=false;
             //   stop();
            }

        }


    }

    public void info()
    {

        //System.out.println("Exmp 2: "+path+jli1.getSelectedValue());
        try {

            path=directories_and_files.get(jli1.getSelectedValue());

            File source = new File(path+jli1.getSelectedValue());
           //System.out.println("Exmp 1: "+source);



            Encoder encoder = new Encoder();

            MultimediaInfo mi = encoder.getInfo(source);
            long ls = mi.getDuration();

            durration=ls;



            jl6.setText(convert_to_minute((int)durration/1000));
            jl7.setText(""+jli1.getSelectedValue());

        }
        catch (Exception er)
        {
            System.out.println("Error code 19:"+er);
           // stop_start_stop=true;
            //stop();
            jb1.setText("Play");


            //info();
            //System.out.println(er);
        }
    }


    public void selected()
    {



            stop();


        new Thread(){

            @Override
            public void run() {

                try
                {

                    sleep(1250);



                    start2();



                }
                catch (Exception ed)
                {
                    System.out.println("Error code 20:"+ed);
                }

            }
        }.start();


            /*
            Thread th = new Thread();

            try
            {


            }
            catch (Exception ed)
            {
                System.out.println("Error code 20:"+ed);
                // System.out.println(ed);
            }
             */








    }

    public void stop()
    {
        if(stop_start_stop)
        {
            start_stop=true;
            count=0;
            start=0;
            jb4x=locationX;
            divide2=0;


        }
    }

    public void stop4()
    {
        if(stop_start_stop)
        {
            start_stop=true;
            //count=0;
            //start=0;
            //jb4x=locationX;
            //divide2=0;


        }
    }


    public static void main(String [] args)
    {


new untitled5();




    }


}
