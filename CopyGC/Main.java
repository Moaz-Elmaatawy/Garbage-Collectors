import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

    public static HashMap<Integer,Integer>heap=new HashMap<>();
    public static HashMap<Integer,ArrayList<Integer>>pointers=new HashMap<>();
    public static ArrayList<Integer>roots= new ArrayList<>();

    public void read (String path){
        try{
            Scanner sc = new Scanner(new File(path));
            while (sc.hasNext())  //returns a boolean value
            {
                String[] line=sc.next().split(",");
                ArrayList<Integer> arr=new ArrayList<Integer>();
                for(int i=0;i<line.length;++i){
                    line[i]=line[i].replaceFirst("ï»¿", "");
                    if(line[i].length()>6){line[i]=line[i].substring(1);}
                    arr.add(Integer.parseInt(line[i]));
                }
                if(line.length==3){
                    heap.put(arr.get(0),arr.get(2)-arr.get(1));
                }
                else if (line.length==2){
                    if(!pointers.containsKey(arr.get(0)))
                        pointers.put(arr.get(0),new ArrayList<Integer>());
                    pointers.get(arr.get(0)).add(arr.get(1));
                }
                else{
                    roots.add(arr.get(0));
                }
            }
            sc.close();  //closes the scanner
        }
        catch (Exception e) {
            System.out.println("reading file problem");
        }
    }

    public static void main(String[] args) {
        Main m =new Main();
        //ArrayList<Integer>roots= new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<3;++i){
            String path=sc.nextLine();
            m.read(path);
        }
        /*
        m.read(System.getProperty("user.dir")+"\\sample4\\heap.csv");
        m.read(System.getProperty("user.dir")+"\\sample4\\pointers.csv");
        m.read(System.getProperty("user.dir")+"\\sample4\\roots.txt");
        */

        CopyGC GC=new CopyGC();
        GC.Copy();
        String path=sc.nextLine();
        //GC.write(System.getProperty("user.dir")+"\\sample4");
        GC.write(path);
    }
}
