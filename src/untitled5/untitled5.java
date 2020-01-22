package untitled5;


import it.sauronsoftware.jave.AudioInfo;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import org.jaudiotagger.audio.asf.io.AsfExtHeaderReader;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;



public class untitled5 extends JFrame

{
    static float divide2=0;
    static long durration;
    static boolean start_stop=false, dont_play_again=false, stop_start_stop=false, go_control=false;
    static int count =0,start=0,jb4x=50,X,Y,dragX;
   JButton jb1;
    JLabel jl3, jl1,jl6;
    JTextField tf1;
    JList jli1;
    Object selected;
    int listlength;
    
    untitled5()
    {


        setTitle("MP3 Player with JAVA by Sinan");
        setSize(680,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



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

        System.out.println("list length: "+list.length);



      for(int i=0;i<list.length;i++)
      {
          list5 [i] = list[i].toString().substring(list[i].toString().lastIndexOf("\\")+1);
      }

       jli1 = new JList(list5);


        jli1.setBackground(Color.GRAY);
        jli1.setSelectedIndex(0);
        jli1.setSelectionMode(0);
        System.out.println(jli1.getSelectedValue());
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
        jp1.setSize(200,140);
        jp1.setLocation(450,10);
        jp1.setVisible(true);

        panel1.add(jp1);


        jl1 = new JLabel("0");
        jl1.setVisible(true);
        jl1.setSize(80,20);
        jl1.setLocation(250,10);

        panel1.add(jl1);

        jl6 = new JLabel("0");
        jl6.setVisible(true);
        jl6.setSize(80,20);
        jl6.setLocation(350,10);

        panel1.add(jl6);
        

        tf1 = new JTextField();
        tf1.setVisible(true);
        tf1.setSize(80,20);
        tf1.setLocation(150,10);

        tf1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode()==10)Go();
            }
        });

        panel1.add(tf1);
        tf1.setText("0");

        JButton jb5 = new JButton("Go");
        jb5.setVisible(true);
        jb5.setSize(80,20);
        jb5.setLocation(50,10);

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
        jb3.setLocation(350,50);

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
        jb4.setLocation(50,50);

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
        jb1.setLocation(150,50);

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
        jb2.setLocation(250,50);

        jb2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            stop();
                jb1.setText("Play");



            }
        });

        panel1.add(jb2);






        jl3 = new JLabel("Sinan");
        jl3.setVisible(true);
        jl3.setSize(50,20);
        jl3.setLocation(50,120);
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

                count = (int)(dragX*durration/1000)/300-(int)(durration/1000*0.158);

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

        });

        jl3.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {


                int x2=(e.getX()+e.getComponent().getX())-X;
                if(x2<50) x2=50;
                if(x2>350) x2=350;

                e.getComponent().setLocation(x2,120);

                dragX=e.getComponent().getX();



            }
        });
        
        
        JLabel jl2 = new JLabel("Sinan");
        jl2.setVisible(true);
        jl2.setSize(350,20);
        jl2.setLocation(50,119);
        ImageIcon img2 = new ImageIcon("line.jpg");
        jl2.setIcon(img2);
        panel1.add(jl2);

       // jli1.setSelectedIndex(0);


         info();
        setVisible(true);
        add(panel1);


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

        Thread t2 = new Thread();

        try
        {
            t2.sleep(1000);
            start();



        }
        catch (Exception et)
        {
        }

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


                                jb4x=50+(int)((300*count)/(durration/1000));

                                jl3.setLocation(jb4x,120);

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

            /*
            FileInputStream fis = new FileInputStream("mp3\\"+selected);
            Bitstream bs = new Bitstream(fis);
            Header h = bs.readFrame();
            float f_size = h.ms_per_frame();
             */


            File source = new File("mp3\\"+jli1.getSelectedValue());
            //File source = new File("mp3\\"+selected);
            Encoder encoder = new Encoder();

            MultimediaInfo mi = encoder.getInfo(source);
            long ls = mi.getDuration();
            //AudioInfo ss = mi.getAudio();
            //String gh = mi.getFormat();

            //System.out.println("info");
            durration=ls;

            jl6.setText(""+(int)durration/1000);
            //System.out.println(selected+" Frame size: "+f_size+" Duration: "+durration);


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


         // info();
    }

    public void stop()
    {
        if(stop_start_stop)
        {
            start_stop=true;
            count=0;
            start=0;
            jb4x=50;
            divide2=0;


        }
    }



   static  void  framesize(long durrr)
    {

        try {

            FileInputStream fis = new FileInputStream("Yeke.mp3");
            Bitstream bs = new Bitstream(fis);
            Header h = bs.readFrame();
            float f_size = h.ms_per_frame();
            // System.out.println("Frame size: "+f_size);
            //System.out.println("Total Frame: "+durrr/f_size);
        }
        catch (Exception e)
        {

        }

    }


   static void durr()
    {

        String path = "mp3";
        File folder = new File(path);
        File list[] = folder.listFiles();
       // System.out.println("First value: "+list[0]);


        File source = new File(""+list[0]);
        Encoder encoder = new Encoder();
        try {
            MultimediaInfo mi = encoder.getInfo(source);
            long ls = mi.getDuration();
            AudioInfo ss = mi.getAudio();
            String gh = mi.getFormat();
            int sec =(int)( ls/1000);
           // System.out.println("duration(ms) = "+ls);
            durration=ls;
           // System.out.println("duration(sec) = "+  (sec/60)+":"+(sec%60));
           // System.out.println("İnfo: "+ss);
           // System.out.println("Format: "+gh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public static void main(String [] args)
    {


new untitled5();


        durr();
       framesize(durration);


    }


}
