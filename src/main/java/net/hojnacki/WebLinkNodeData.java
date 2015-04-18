package net.hojnacki;

public class WebLinkNodeData implements INodeData {

    private String name;
    private String url;

    public WebLinkNodeData(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String getName() {
        return name;
    }


    public String getUrl() {
        return url;
    }

}
