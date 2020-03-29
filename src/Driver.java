
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hasaan Majeed
 */
public class Driver {
    
    private static final ArrayList<String> states = new ArrayList<>();
    private static final ArrayList<String> actions = new ArrayList<>();
    private static final ArrayList<ArrayList<Integer>> transitionMatrix= new ArrayList<>();
        
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        //System.out.println("MNT");
        String[] MNT = input.nextLine().split(" ");
        //String[] arrMNT = MNT.split(" ");
        int M = Integer.parseInt(MNT[0]);
        int N = Integer.parseInt(MNT[1]);
        int T = Integer.parseInt(MNT[2]);
        
        //System.out.println("States");
        input.nextLine();
        for(int i=0; i<M; i++){
            states.add(input.nextLine());
        }
        
        //System.out.println("Actions");
        input.nextLine();
        for(int i=0; i<N; i++){
            actions.add(input.nextLine());
        }
        
        //System.out.println("Transition Matrix");
        input.nextLine();
        for(int i=0;i<M;i++){
            String[] row = input.nextLine().split(" ");
            //String[] arrRow =  row.split(" ");
            ArrayList<Integer> list = new ArrayList<>(); 
            for(int j=0; j<row.length; j++){
                list.add(Integer.parseInt(row[j]));
            }
            transitionMatrix.add(list);
        }
        
        //System.out.println("Test Cases");
        input.nextLine();
        ArrayList<String[]> testCases = new ArrayList<>();
        for(int i=0;i<T;i++){
            String[] seq = input.nextLine().split("\t");
            //String[] seqArr = seq.split("\t");
            testCases.add(seq);
        }
        
        //Print Sequence
        System.out.println("\nRequired Plans: ");
        for(int i=0;i<T;i++){
            ArrayList<String> solution = search(testCases.get(i));
            for(int j=0; j<solution.size(); j++){
                System.out.print(solution.get(j));
                if(j != solution.size()-1){
                    System.out.print("->");
                }
            }
            System.out.println("");
        }
    }
    
    private static ArrayList<String> search(String[] testCase) {
        String start = testCase[0];
        String goal = testCase[1];
        ArrayList<String> solution = new ArrayList<>();
        Node initState = new Node(null,start,null);
        if(initState.state.equals(goal)){
            solution.add("Do Nothing!");
            return solution;
        } 
        Queue<Node> frontier = new LinkedList<>();
        ArrayList<Integer> exploredSet = new ArrayList<>();
        int count;
        frontier.add(initState);
        while(true){
            if(frontier.isEmpty()){
                solution.clear();
                solution.add("Goal Not Found");
                return solution;
            }
            Node node = frontier.remove();
            int index = states.indexOf(node.state);
            count = 0;
            exploredSet.add(index);
            
            //Inserting children in frontier
            for(int i:transitionMatrix.get(index)){
                if( !exploredSet.contains(i) && i != index){
                    if(goal.equals(node.state))
                        return null;
                    exploredSet.add(i);
                    solution.add(actions.get(count));
                    if(goal.equals(states.get(i)))
                        return solution;
                    
                    frontier.add(new Node(node,states.get(i),actions.get(count)));
                    break;
                }
                count++;
            }
            
        }
        
    }
    
}
