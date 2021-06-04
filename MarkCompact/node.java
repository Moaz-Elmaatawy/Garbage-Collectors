import java.util.ArrayList;
import java.util.List;

public class node {
    int name ;
    int startingBite , endingBite ;
    List<node> adj ;
    boolean marked ;

    public node(int name, int startingBite, int endingBite) {
        this.name = name;
        this.startingBite = startingBite;
        this.endingBite = endingBite;
        this.adj = new ArrayList<node>();
        this.marked = false;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getStartingBite() {
        return startingBite;
    }

    public void setStartingBite(int startingBite) {
        this.startingBite = startingBite;
    }

    public int getEndingBite() {
        return endingBite;
    }

    public void setEndingBite(int endingBite) {
        this.endingBite = endingBite;
    }

    public List<node> getAdj() {
        return adj;
    }

    public void setAdj(List<node> adj) {
        this.adj = adj;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked() {
        this.marked = true;
    }
}
