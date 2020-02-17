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
import java.awt.event.*;
import java.io.*;


public class untitled5 extends JFrame

{
    static float divide2=0,vlm3=50;
    static long durration;
    static boolean start_stop=false, dont_play_again=false, stop_start_stop=false;
    static int count =0,start=0,jb4x=0,X,Y,dragX,locationX=20,XVolume,YVolume, GetXvolume, timeline,timeX;
    static String outcome="40", jk [];

   JButton jb1;
    JLabel jl3, jl1,jl6,jl7,jl4,jl9,jl2,jl8,jl5;
    JTextField tf1,tf2;
    JList jli1;
    Object selected;
    int listlength;
    
    untitled5()
    {
        jb4x=locationX;

        setTitle("MP3 Player with JAVA by Sinan");
        setSize(680,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);



        setLocationRelativeTo(this);
        setLayout(null);


         JPanel panel1 = new JPanel();

        panel1.setSize(getSize());
        panel1.setLayout(null);

        panel1.setBackground(Color.WHITE);
        panel1.setLocation(0,0);


        String path = "mp3";
        File folder = new File(path);
        File list[] = folder.listFiles();
        String list5 [] = new String [list.length];
      listlength = list.length;





      for(int i=0;i<list.length;i++)
      {
          list5 [i] = list[i].toString().substring(list[i].toString().lastIndexOf("\\")+1);
      }

       jli1 = new JList(list5);


        jli1.setBackground(Color.GRAY);
        jli1.setSelectedIndex(0);
        jli1.setSelectionMode(0);

        jli1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                boolean adjust = e.getValueIsAdjusting();
                if(!adjust)
                {
                    JList list2 = (JList) e.getSource();
                    selected = list2.getSelectedValue();
                    selected();
                }

            }
        });
                JScrollPane jp1 = new JScrollPane();
        jp1.setViewportView(jli1);
        jli1.setLayoutOrientation(JList.VERTICAL);
        jp1.setSize(225,140);
        jp1.setLocation(400+locationX,10);
        jp1.setVisible(true);

        panel1.add(jp1);


        jl1 = new JLabel("Count");
        jl1.setVisible(true);
        jl1.setSize(80,20);
        jl1.setLocation(200+locationX,10);

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
        
        
        tf1 = new JTextField();
        tf1.setVisible(true);
        tf1.setSize(80,20);
        tf1.setLocation(100+locationX,10);

        tf1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode()==10)
                {
                    Go();
                    tf1.setSelectionStart(0);
                    tf1.setSelectionEnd(tf1.getText().length());
                }
            }
        });
        
        panel1.add(tf1);
        tf1.setText("");

        
        
        JButton jb5 = new JButton("Go");
        jb5.setVisible(true);
        jb5.setSize(80,20);
        jb5.setLocation(+locationX,10);

        panel1.add(jb5);

        jb5.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                Go();
            }
        });


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
                   if(stop_start_stop) {

                       start_stop = true;
                       start = (int) (count*1000 / 26);
                       jb1.setText("Play");

                   }
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

                  X= e.getX();
                Y=e.getY();

                 stop();

            }

            @Override
            public void mouseReleased(MouseEvent e) {


               count = (int)(dragX*durration/1000)/(370)-(int)(durration/1000*0.05813);
                // 0.05813 should be calculated when locationX is changed, it depends on the float difference that is between two songs and the location of processing bar.

                start = (int) (count*1000 / 26);


                Thread th = new Thread();

                try
                {

                    th.sleep(1000);

                    start();
                }
                catch (Exception ed)
                {}


            }

            public void mouseExited(MouseEvent e) {

                jl9.setVisible(false);

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

            }


            public void mouseMoved(MouseEvent e) {

                timeline(jl3.getX()-2*jl3.getWidth()+e.getX());
               //timeline(e.getX()+jb4x);

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

                    jl9.setVisible(false);

            }
            public void mouseEntered(MouseEvent e) {

                jl9.setVisible(true);

            }

            public void mousePressed(MouseEvent e) {







                    X= e.getX();
                    Y=e.getY();
                    stop();
                    //go_volume_bar();


                dragX =timeX+15;


                // if(dragX>390) dragX =3890;

                //  System.out .println(dragX);
                count = (int)(dragX*durration/1000)/(370)-(int)(durration/1000*0.05813);
                if(count<0) count=0;

                // 0.05813 should be calculated when locationX is changed, it depends on the float difference that is between two songs and the location of processing bar.

                start = (int) (count*1000 / 26);

                new Thread(){

                    @Override
                    public void run() {

                        try
                        {

                            sleep(1000);

                            go_volume_bar();

                        }
                        catch (Exception ed)
                        {}

                    }
                }.start();

















            }

            @Override
            public void mouseReleased(MouseEvent e) {


              // go_volume_bar();

            }

            public void mouseClicked(MouseEvent e) {


           //go_volume_bar();

            }

        });

        jl2.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {

              timeline(e.getX());
            //System.out.println(e.getX());

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
                volume_write((int)converse);
               // jl8.setVisible(false);

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
                //System.out.println(jk[1]);

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
                int hm = Integer.parseInt(jk[1]);
                //System.out.println(hm);

                volume_up_down(hm*(0.01F));
                volume_write(hm);

            }

            public void mousePressed(MouseEvent e) {
                int hm = Integer.parseInt(jk[1]);
                //System.out.println(hm);

                volume_up_down(hm*(0.01F));
                volume_write(hm);

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
                //System.out.println(jk[1]);
            }
        });


        jl5.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                int jl4_locx =e.getX()+locationX;
                if(jl4_locx+jl4.getWidth()>jl5.getWidth()+locationX)
                    jl4_locx=jl5.getWidth()+locationX-jl4.getWidth();

                jl4.setLocation(jl4_locx,jl4.getY());
                //  super.mousePressed(e);
            }
        });

        panel1.add(jl5);




        
        volume_read();

        try
        {
            vlm3=Integer.parseInt(outcome);
        }
        catch (Exception e)
        {
            vlm3=50; System.out.println(e);
        }
       // System.out.println(vlm3);
        volume_up_down(vlm3*(0.01F));

        int xstart =locationX+(int)((double)vlm3*0.86);
        jl4.setLocation(xstart,105);




         info();
        setVisible(true);
        add(panel1);


    }


    public void go_volume_bar()
    {






       //if(click_cont)
        //click_cont=false;
        start();



            /*
             Thread th = new Thread();
               try
            {

                th.sleep(1000);

                start();

            }
            catch (Exception ed)
            {}
             */







        }




        //start();



    public void timeline(int getx)
    {


       timeX=getx;



        if(timeX>379) timeX=379;
       if(timeX<0) timeX=0;

        jl9.setLocation(timeX,120);

        timeline =(int) ((timeX*(durration/1000))/371);
        //jl9.setText(""+timeX);

        if(timeline>(int)(durration/1000)) timeline=(int)(durration/1000);

        jl9.setText(""+timeline);

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
            System.out.println(etr);
        }



    }


    public void volume_write(Integer vlm)
    {
        try(FileWriter fileWriter = new FileWriter( "volume_record.txt")) {

           fileWriter.write(vlm.toString());
        } catch (IOException ej) {

        }
    }


    public void Go()
    {
        stop();

        try
        {
            count = Integer.parseInt(tf1.getText());
        }
        catch (Exception ej)
        {
            count=1;
            tf1.setText("1");
        }

        if(count>durration/1000 || count<0)
        {
            count=1;
            tf1.setText("1");
        }

        start = (int) (count*1000 / 26);
        if(count==1) start();


        new Thread(){

            @Override
            public void run() {

                try
                {

                    sleep(1000);

                    go_volume_bar();

                }
                catch (Exception ed)
                {}

            }
        }.start();

        /*
        Thread t2 = new Thread();

        try
        {
            t2.sleep(1000);
            start();



        }
        catch (Exception et)
        {
        }
         */


    }

    public void previous()
    {
        jb1.setText("Play");


        if(jli1.getSelectedIndex()==0)

            jli1.setSelectedIndex(listlength-1);
        else
        {
            jli1.setSelectedIndex(jli1.getSelectedIndex()-1);
            selected=jli1.getSelectedValue();
        }
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
            JOptionPane.showMessageDialog(null,"Erro\n"+e);
        }


    }





    public void start()
    {
        if(!dont_play_again)
        {

            stop_start_stop=true;
            dont_play_again=true;
            info();
            try{

                FileInputStream fis = new FileInputStream("mp3\\"+jli1.getSelectedValue().toString());
                AdvancedPlayer playMP3 = new AdvancedPlayer(fis);
                jb1.setText("Pause");


                playMP3.setPlayBackListener(new PlaybackListener() {
                    @Override
                    public void playbackFinished(PlaybackEvent evt) {

                     next();

                    }
                });


                new Thread()
                {

                    @Override
                    public void run() {

                        while (!start_stop)
                        {
                            count++;

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

                            }

                            jl1.setText(""+count);

                        }


                        playMP3.close();

                        start_stop=false;
                        stop_start_stop=false;

                        jl1.setText(""+count);
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

                        }

                    }
                }.start();

            }catch(Exception ey){System.out.println(ey);}

        }


    }

    public void info()
    {
        try {

            File source = new File("mp3\\"+jli1.getSelectedValue());

            Encoder encoder = new Encoder();

            MultimediaInfo mi = encoder.getInfo(source);
            long ls = mi.getDuration();

            durration=ls;

            jl6.setText(""+(int)durration/1000);
            jl7.setText(""+jli1.getSelectedValue());

        }
        catch (Exception er)
        {
            System.out.println(er);
        }
    }


    public void selected()
    {


            stop();

            Thread th = new Thread();

            try
            {

                th.sleep(1250);

                start();
            }
            catch (Exception ed)
            {}



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




    public static void main(String [] args)
    {


new untitled5();




    }


}
