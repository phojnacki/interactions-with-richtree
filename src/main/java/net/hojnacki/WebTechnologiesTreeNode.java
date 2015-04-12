package net.hojnacki;

import org.richfaces.model.TreeNodeImpl;

public class WebTechnologiesTreeNode extends TreeNodeImpl {

    private Object data;

    public WebTechnologiesTreeNode() {
        super();
    }

    public WebTechnologiesTreeNode(boolean leaf, Object data) {
        super(leaf);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {

        return data.toString();
    }

}