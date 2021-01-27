import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class NodeUrlCollector extends RecursiveAction {

    private volatile Node node;

    public NodeUrlCollector(Node node) {
        this.node = node;
    }

    @Override
    protected void compute() {
        try {
            boolean isntDuplicate = SiteMap.putUniqueURL(node.getValue());
            if (! isntDuplicate) {
                return;
            }
            List<NodeUrlCollector> taskList = new ArrayList<>();
            for (Node child : node.getChildren()) {
                NodeUrlCollector task = new NodeUrlCollector(child);
                task.fork();
                taskList.add(task);
            }
            for (NodeUrlCollector task : taskList) {
                task.join();
            }
        } catch (Exception ex) {
            System.out.println("EXEPTION : " + ex.getMessage());
        }
    }
}