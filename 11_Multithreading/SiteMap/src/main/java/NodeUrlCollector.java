import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class NodeUrlCollector extends RecursiveTask<String> {

    private final Node node;

    public NodeUrlCollector(Node node) {
        this.node = node;
    }

    @Override
    protected String compute() {
        String exit = "";
        try {
            Boolean back = SiteMap.putUniqueURL(node.getValue());
            if (back != null) {
                return null;
            }
            List<NodeUrlCollector> taskList = new ArrayList<>();
            for (Node child : node.getChildren()) {
                NodeUrlCollector task = new NodeUrlCollector(child);
                task.fork();
                taskList.add(task);
            }
            for (NodeUrlCollector task : taskList) {
                exit = task.join();
            }
        } catch (Exception ex) {
            System.out.println("EXEPTION : " + ex.getMessage());
        }
        return exit;
    }
}