public class Main2 {

    Node getNodeById(Node root, Integer idToSearch) {
        Node result = null;
        if (root.getId().equals(idToSearch)) {
            return root;
        }
        for (Node node : root.getChildren()) {
            if (node.getId().equals(idToSearch)) {
                return node;
            }
            if (node.getChildren() != null) {
                result = getNodeById(node, idToSearch);
                if (result != null) {
                    return result;
                }
            }
        }
        return result;
    }
}
