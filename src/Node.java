/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hasaan Majeed
 */
public class Node {
    Node parent;
    String state;
    String action;

    public Node(Node parent, String state, String action) {
        this.parent = parent;
        this.state = state;
        this.action = action;
    }
    
    
}
