package net.hojnacki;

import java.io.Serializable;
import org.richfaces.model.TreeNodeImpl;

public class WebTechnologiesTreeNode extends TreeNodeImpl implements Serializable{

    private INodeData data;

    private boolean expanded = false;
    
    private WebTechnologiesTreeNode(boolean leaf) {
        super(leaf);
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public void setData(INodeData data) {
        this.data = data;
    }

    public INodeData getData() {
        return data;
    }

    public static class NodeBuilder {

        private WebTechnologiesTreeNode node;
        private int nodeIndex = 0;

        public NodeBuilder() {
            this.node = new WebTechnologiesTreeNode(false);
        }

        public NodeBuilder withData(INodeData nodeData) {
            this.node.setData(nodeData);
            return this;
        }

        public NodeBuilder withChildLeaf(INodeData nodeData) {
            WebTechnologiesTreeNode leafNode = new WebTechnologiesTreeNode(true);
            leafNode.setData(nodeData);
            this.node.addChild(String.valueOf(nodeIndex++), leafNode);
            return this;
        }

        public WebTechnologiesTreeNode build() {
            return this.node;
        }

    }

}