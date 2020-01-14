package umd.cs.shop;

import java.util.Vector;

public interface MCTSExpand {

    public Vector<MCTSNode> expand(MCTSNode node);

    public static MCTSExpand getPolicy(String policy, boolean recursive) {
        switch (policy) {
            case "simple":
                return new MCTSExpansionSimple(recursive, false);
            case "primitive":
                return new MCTSExpansionPrimitive(recursive);
            case "deadEnd":
                return new MCTSExpansionSimple(recursive, true);
            default:
                System.err.println("Unknown expansion policy name: " + policy);
                System.err.println("Options are: simple primitive deadEnd");
                System.exit(-1);
        }
        return null;
    }
}
