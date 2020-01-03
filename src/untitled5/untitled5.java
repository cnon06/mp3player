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
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;

// libs are jaudiotagger, java, jlayer

public class untitled5 extends JFrame

{
    static float divide2=0;
    static long durration;
    static boolean start_stop=false, dont_play_again=false, stop_start_stop=false, go_control=false;
    static int count =0,start=0,jb4x=50,X,Y,dragX;
   // JButton jb4;
    JLabel jl3, jl1,jl4;
    JTextField tf1;
    
    
    untitled5()
    {


        setTitle("MP3 Player with JAVA by Sinan");
        setSize(500,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setResizable(false);

        setLocationRelativeTo(this);
        setLayout(null);


         JPanel panel1 = new JPanel();

        panel1.setSize(getSize());
        panel1.setLayout(null);

        panel1.setBackground(Color.WHITE);
        panel1.setLocation(0,0);




        jl1 = new JLabel("0");
        jl1.setVisible(true);
        jl1.setSize(80,20);
        jl1.setLocation(150,50);

        panel1.add(jl1);


        tf1 = new JTextField();
        tf1.setVisible(true);
        tf1.setSize(40,20);
        tf1.setLocation(150,10);
       
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


                if(dont_play_again)
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


                    // if(start_stop) start();

                    Thread t2 = new Thread();

                    try
                    {
                        t2.sleep(1000);
                        start();
                    }
                    catch (Exception et)
                    {

                    }


                    //go_control=true;

                }









            }





        });


        JButton jb1 = new JButton("Play");
        jb1.setVisible(true);
        jb1.setSize(80,20);
        jb1.setLocation(50,50);
        //jb1.setLayout(null);
       panel1.add(jb1);
       jb1.addMouseListener(new MouseAdapter() {
           @Override
           public void mousePressed(MouseEvent e) {


               if(jb1.getText()=="Play")
               {
                   start();
                   jb1.setText("Pause");
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
        jb2.setLocation(300,50);
        //jb1.setLayout(null);

        jb2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {




            stop();



            }
        });

        panel1.add(jb2);


        /*
        JButton jb3 = new JButton("Pause");
        jb3.setVisible(true);
        jb3.setSize(80,20);
        jb3.setLocation(200,50);
        //jb1.setLayout(null);

        jb3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {


                if(stop_start_stop) {

                    start_stop = true;
                    start = (int) (count*1000 / 26);

                }



            }
        });

        panel1.add(jb3);
         */





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
                //X= e.getX();
                //Y=e.getY();

                //count = (int)(e.getComponent().getX()*durration/1000)/300;
                count = (int)(dragX*durration/1000)/300-40;
                start = (int) (count*1000 / 26);
                //if(dont_play_again) dont_play_again=false;

                Thread th = new Thread();

                try
                {
                   // System.out.println("starting");
                    th.sleep(1000);
                   // System.out.println("started");
                    start();
                }
                catch (Exception ed)
                {}


                jb1.setText("Pause");

                //start();
            }

        });


        jl3.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {


                int x2=(e.getX()+e.getComponent().getX())-X;
                if(x2<50) x2=50;
                if(x2>350) x2=350;
                //super.mousePressed(e);
                e.getComponent().setLocation(x2,120);
               // System.out.println(e.getComponent().getX());
                dragX=e.getComponent().getX();


                // System.out.println("Mouse dragged");
            }
        });
        
        
        JLabel jl2 = new JLabel("Sinan");
        jl2.setVisible(true);
        jl2.setSize(350,20);
        jl2.setLocation(50,119);
        ImageIcon img2 = new ImageIcon("line.jpg");
        jl2.setIcon(img2);
        panel1.add(jl2);




        setVisible(true);
        add(panel1);


    }



    public  void gap()
    {


      if(go_control)
      {
          start();
          go_control=false;
      }
    }

    public void start()
    {
        if(!dont_play_again)
        {
            stop_start_stop=true;
            dont_play_again=true;

            try{

                FileInputStream fis = new FileInputStream("Yeke.mp3");
                AdvancedPlayer playMP3 = new AdvancedPlayer(fis);

                playMP3.setPlayBackListener(new PlaybackListener() {
                    @Override
                    public void playbackFinished(PlaybackEvent evt) {

                        stop();
                        /*
                         if(!start_stop) start_stop=true;
                        if(stop_start_stop) stop_start_stop=false;
                        count=0;
                        jb4x=50;
                         */


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
                                //jb4.setLocation(jb4x,100);

                                //jl3x=50+(int)((300*count)/(durration/1000));
                                jl3.setLocation(jb4x,120);

                            }
                            catch (Exception hj)
                            {

                            }

                            jl1.setText(""+count);
                            //System.out.println("errer");
                        }

                        playMP3.close();

                        start_stop=false;
                        stop_start_stop=false;

                        jl1.setText(""+count);
                        dont_play_again=false;
                        gap();
                        //this.start();
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


                        //super.run();
                    }
                }.start();

            }catch(Exception ey){System.out.println(ey);}

        }


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

    /*
    public void paint(Graphics g)
    {
        g.drawLine(50,120,250,120);
    }
     */


  

   static  void  framesize(long durrr)
    {

        try {

            FileInputStream fis = new FileInputStream("Yeke.mp3");
            Bitstream bs = new Bitstream(fis);
            Header h = bs.readFrame();
            float f_size = h.ms_per_frame();
            System.out.println("Frame size: "+f_size);
            System.out.println("Total Frame: "+durrr/f_size);



        }
        catch (Exception e)
        {

        }

    }


   static void durr()
    {
        File source = new File("Yeke.mp3");
        Encoder encoder = new Encoder();
        try {
            MultimediaInfo mi = encoder.getInfo(source);
            long ls = mi.getDuration();
            AudioInfo ss = mi.getAudio();
            String gh = mi.getFormat();
            int sec =(int)( ls/1000);
            System.out.println("duration(ms) = "+ls);
            durration=ls;
            System.out.println("duration(sec) = "+  (sec/60)+":"+(sec%60));
            System.out.println("İnfo: "+ss);
            System.out.println("Format: "+gh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void Simpleplayer()
    {
        try{

            FileInputStream fis = new FileInputStream("Yeke.mp3");
            AdvancedPlayer playMP3 = new AdvancedPlayer(fis);

            playMP3.play(8500,(int)durration);

        }catch(Exception e){System.out.println(e);}
    }


    public static void main(String [] args)
    {


new untitled5();

       //durr dr = new durr();
        durr();
       framesize(durration);
      //  Simpleplayer();

    }


}
