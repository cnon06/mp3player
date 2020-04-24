package untitled5;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

public class MyFileVisitor extends SimpleFileVisitor<Path>
{

   // untitled5 unt5 = new untitled5();
    HashMap<String, String> directories_and_files = new HashMap<>();

    public FileVisitResult postVisitDirectory (Path dir, IOException exc) throws  IOException
    {
       // System.out.println("Just visited "+ dir);
        return FileVisitResult.CONTINUE;
    }


    public FileVisitResult preVisitDirectory (Path dir, BasicFileAttributes attrs) throws  IOException
    {
        //System.out.println("About to visit "+ dir);
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult visitFile (Path file, BasicFileAttributes attrs) throws  IOException
    {

        String get_em2=file.toString().substring(file.toString().lastIndexOf(".")+1);

       // if(get_em2.equals("mp3"))

        if(attrs.isRegularFile() && get_em2.equals("mp3") )
        {
          //  System.out.print("Regular File: ");

            String get_app=file.toString().substring(file.toString().lastIndexOf("\\")+1);

           //((DefaultListModel)unt5.jli1.getModel()).addElement(get_app);

            String get_directory = file.toString().substring(0,file.toString().lastIndexOf("\\")+1);




            directories_and_files.put(get_app,get_directory);

        }


        //System.out.println(file);
        return FileVisitResult.CONTINUE;
    }


    public FileVisitResult visitFileFailed (Path file, IOException exc) throws  IOException
    {
        System.err.println(exc.getMessage());
        return FileVisitResult.CONTINUE;
    }

}
