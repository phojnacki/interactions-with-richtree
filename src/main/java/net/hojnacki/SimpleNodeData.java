package net.hojnacki;

public class SimpleNodeData implements INodeData {

    private String name;


    public SimpleNodeData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
