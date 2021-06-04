import java.io.*;
import java.util.*;

public class Main {

    static void takeHeap(Map<Integer,node> nodesList,String heapPath){

        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(heapPath));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] line = row.split(",");
                line[0]=line[0].replaceFirst("ï»¿", "");
                if(line[0].length()>6){line[0]=line[0].substring(1);}
                //create new node
                node n = new node(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]));
                nodesList.put(n.name,n);
            }
        }
        catch (Exception FileNotFoundException) {
            System.out.println("File not found");
        }
    }

    static void takePointers(Map<Integer,node> nodesList,String pointersPath){

        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(pointersPath));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] line = row.split(",");
                line[0]=line[0].replaceFirst("ï»¿", "");
                if(line[0].length()>6){line[0]=line[0].substring(1);}
                //find and returns the next complete token from this scanner
                //there is a pointer from node a to node b
                node a = nodesList.get(Integer.parseInt(line[0])),
                        b = nodesList.get(Integer.parseInt(line[1])) ;

                //add node b to adj list of node a
                a.adj.add(b);
            }
        }
        catch (Exception FileNotFoundException) {
            System.out.println("File not found");
        }
    }

    static void takeRoots(List<Integer> roots,String rootsPath){
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader(rootsPath));
            String row;
            while ((row = csvReader.readLine()) != null) {
                roots.add(Integer.parseInt(row));
            }
        }
        catch (Exception FileNotFoundException) {
            System.out.println("File not found");
        }
    }

    static void dfs(node s){
        if (s == null || s.marked) return;
        s.setMarked();
        for (node u:s.adj) {
            dfs(u);
        }
    }


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Heap Path : ");
        String heapPath = sc.nextLine();
        System.out.print("\nEnter Roots Path : ");
        String rootsPath = sc.nextLine();
        System.out.print("\nEnter Pointers Path : ");
        String pointersPath = sc.nextLine();
        System.out.print("\nEnter New Heap (Reult) Path : ");
        String resultPath = sc.nextLine();




        //take input from files
        Map<Integer,node> nodeslist = new LinkedHashMap<Integer,node>();
        takeHeap(nodeslist,heapPath);

        //take pointers from pointers.csv
        takePointers(nodeslist,pointersPath);

        //take input roots
        List<Integer> roots = new ArrayList<Integer>();
        takeRoots(roots,rootsPath);


        //mark by doing dfs for all roots
        for (int root : roots){
            dfs(nodeslist.get(root));
        }

        //compact
        int index = 0 ;
        for (node  n : nodeslist.values()){
            if(!n.marked) continue;
            int numOfBits = n.getEndingBite()-n.getStartingBite();
            n.setStartingBite(index);
            n.setEndingBite(index + numOfBits);
            index = n.getEndingBite()+1;
            //print result in console
            System.out.println(n.name+" "+n.getStartingBite()+" "+n.getEndingBite());
        }


        //then write new csv file which represent of the new heap
        FileWriter csvWriter = new FileWriter(resultPath+"/MarkCompact-new-heap.csv");
        for (node  n : nodeslist.values()){
            if(!n.marked) continue;
            List<String> newRow = new ArrayList<String>();
            newRow.add(String.valueOf(n.name));
            newRow.add(String.valueOf(n.startingBite));
            newRow.add(String.valueOf(n.endingBite));
            csvWriter.append(String.join(",",newRow));
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();

    }


}
