import java.util.List;

public class LevDistance {
	
    public static int distance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
 
    public static int getAvgDistance(List<String> args) {
    	int avg = 0;
    	for (int i = 0; i < args.size(); i++) {
    		  for (int j = i+1; j < args.size(); j++) {
    		    avg = avg + distance(args.get(i),args.get(j));
    		    System.out.println(args.get(i)+", "+args.get(j));
    		  }
    		}
    	return avg/args.size();
    }

}
