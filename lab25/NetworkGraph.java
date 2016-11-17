/**
 * A simple graph with round nodes and straight edges.
 */
public class NetworkGraph extends Graph
{
   public Node[] getNodePrototypes()
   {
      Node[] nodeTypes = { new PersonNode() };
      return nodeTypes;
   }

   public Edge[] getEdgePrototypes()
   {
      Edge[] edgeTypes = { new LineEdge() };
      return edgeTypes;
   }
}
