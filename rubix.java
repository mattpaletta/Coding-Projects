java.util.*;

public class rubix {
    Stacks path = new Stacks();
    
    
    public static void main(String[] args) {
        
        Cube c = new Cube();
        c.solve();
        
    }
    
    public boolean solve(Cube c) {
        
        
        return true;
    }
}


public class Cube {
    String[][] repren = new String[6][6];
    
    public Cube() {
        repren = {
            {"GOW", "GO", "GOY", "GY", "GOR", "GR", "GRW", "GW"},
            {"OGW", "OG", "OGY", "OY", "OYB", "OB", "OBW", "OW"},
            {"WRG", "WG", "WGO", "WO", "WBO", "WB", "WBR", "WR"},
            {"RGW", "RW", "RWB", "RB", "RBO", "RY", "RGY", "RG"},
            {"YG", "YGR", "YR", "YRB", "YB", "YOB", "YO", "YOG"},
            {"BY", "BYO", "BO", "BYO", "BW", "BWR", "BR", "BRY"}
        };
        
    }
    
    public void rotateF() {
        
    }
    
    public void rotateB() {
        
    }
    
    public void rotateR() {
        
    }
    
    public void rotateL() {
        
    }
    
    public void rotateT() {
        
    }
    
    public void rotateD() {
        
    }
    
    
    
    public boolean isSolved() {
        
    }
}


/*

GREEN
BLUE

RED
ORANGE

YELLOW
WHITE

*/
