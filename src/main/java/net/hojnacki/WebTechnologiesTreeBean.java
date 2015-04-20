package net.hojnacki;


import java.io.Serializable;
import org.richfaces.component.AbstractTree;
import org.richfaces.event.TreeSelectionChangeEvent;
import org.richfaces.model.SequenceRowKey;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.richfaces.component.UITree;

@ManagedBean
@SessionScoped
public class WebTechnologiesTreeBean implements Serializable, ITree {

    private WebTechnologiesTreeNode rootNode;

    private Collection<Object> selection = new ArrayList<Object>() {{add(new SequenceRowKey("frameworks", "0"));}};

    @ManagedProperty("#{browserBean}")
    private BrowserBean browserBean;
  
    @PostConstruct
    public void initialize() {
        rootNode = createRootNode();
    }

    private WebTechnologiesTreeNode createRootNode() {
        WebTechnologiesTreeNode result = new WebTechnologiesTreeNode.NodeBuilder().build();
        WebTechnologiesTreeNode frameworkNode = new WebTechnologiesTreeNode.NodeBuilder()
                .withData(new SimpleNodeData("Web frameworks"))
                .withChildLeaf(new WebLinkNodeData("Java server faces", "https://javaserverfaces.java.net/"))
                .withChildLeaf(new WebLinkNodeData("Vaadin", "https://vaadin.com/home"))
                .withChildLeaf(new WebLinkNodeData("Spring MVC", "http://projects.spring.io/spring-framework/"))
                .withChildLeaf(new WebLinkNodeData("Play", "https://www.playframework.com/"))
                .withChildLeaf(new WebLinkNodeData("Struts", "https://struts.apache.org/"))
                .build();
        WebTechnologiesTreeNode containerNode = new WebTechnologiesTreeNode.NodeBuilder()
                .withData(new SimpleNodeData("Web containers"))
                .withChildLeaf(new WebLinkNodeData("Tomcat", "http://tomcat.apache.org/"))
                .withChildLeaf(new WebLinkNodeData("Glassfish", "https://glassfish.java.net/"))
                .withChildLeaf(new WebLinkNodeData("JBoss", "http://www.jboss.org/"))
                .build();
        result.addChild("frameworks", frameworkNode);
        result.addChild("containers", containerNode);
        return result;
    }

   


    @Override
    public SequenceRowKey getSingleSelection() {
        if (selection.isEmpty()) {
            return null;
        }
        List<Object> selectionList = new ArrayList<>(selection);
        SequenceRowKey selectionKey = (SequenceRowKey) selectionList.get(0);
        return selectionKey;
    }

    public void processTreeSelectionChange(TreeSelectionChangeEvent selectionChangeEvent) {
        Collection<Object> userSelection = selectionChangeEvent.getNewSelection();
        AbstractTree tree = (AbstractTree) selectionChangeEvent.getSource();
        
        List<Object> userSelectionList = new ArrayList<>(userSelection);
        Object userSelectionKey = userSelectionList.get(0);

        tree.setRowKey(userSelectionKey);
        
        WebTechnologiesTreeNode selectedNode = (WebTechnologiesTreeNode) tree.getRowData();
        if (selectedNode.getData() instanceof WebLinkNodeData) {
            browserBean.setUrl(((WebLinkNodeData) selectedNode.getData()).getUrl());
        } else {
            browserBean.setUrl("about:blank");
        }
    }

    @Override
    public WebTechnologiesTreeNode getRootNode() {
        return rootNode;
    }

    @Override
    public Collection<Object> getSelection() {
        return selection;
    }

    public void setSelection(Collection<Object> selection) {
        this.selection = selection;
    }

    public BrowserBean getBrowserBean() {
        return browserBean;
    }

    public void setBrowserBean(BrowserBean browserBean) {
        this.browserBean = browserBean;
    }

    public void switchContainersExpansion() {
        WebTechnologiesTreeNode node = (WebTechnologiesTreeNode) rootNode.getChild("containers");
        node.setExpanded(!node.isExpanded());
    }
    
    public String getContainersExpansionCss() {
        WebTechnologiesTreeNode node = (WebTechnologiesTreeNode) rootNode.getChild("containers");
        return node.isExpanded() ? "collapseButton" : "expandButton";
    }
}