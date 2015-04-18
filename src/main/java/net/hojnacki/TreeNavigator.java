package net.hojnacki;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.richfaces.model.SequenceRowKey;


@ManagedBean
@SessionScoped
public class TreeNavigator {

    private List<SequenceRowKey> flatTraversalOrder;

    @ManagedProperty("#{webTechnologiesTreeBean}")
    private ITree treeBean;
    
    @ManagedProperty("#{browserBean}")
    private BrowserBean browserBean;
    
    @PostConstruct
    public void initialize() {
        flatTraversalOrder = createTraversalOrder(treeBean.getRootNode());
    }

    private List<SequenceRowKey> createTraversalOrder(WebTechnologiesTreeNode rootNode) {
        List<SequenceRowKey> result = new ArrayList<>();
        for (Iterator<Object> i = rootNode.getChild("frameworks").getChildrenKeysIterator(); i.hasNext();) {
            result.add(new SequenceRowKey("frameworks", i.next()));
        }
        for (Iterator<Object> i = rootNode.getChild("containers").getChildrenKeysIterator(); i.hasNext();) {
            result.add(new SequenceRowKey("containers", i.next()));
        }
        return result;
    }
    
    public void selectNext() {
        SequenceRowKey selectionKey = treeBean.getSingleSelection();
        int indexInTraversalList = flatTraversalOrder.indexOf(selectionKey);
        if ((indexInTraversalList == -1) || (indexInTraversalList == flatTraversalOrder.size() - 1)) {
            return;
        }
        SequenceRowKey toBeSelectedKey = flatTraversalOrder.get(indexInTraversalList + 1);
        traverseToKey(toBeSelectedKey);
    }

    public void selectPrevious() {
        SequenceRowKey selectionKey = treeBean.getSingleSelection();
        int indexInTraversalList = flatTraversalOrder.indexOf(selectionKey);
        if ((indexInTraversalList == -1) || (indexInTraversalList == 0)) {
            return;
        }
        SequenceRowKey toBeSelected = flatTraversalOrder.get(indexInTraversalList - 1);
        traverseToKey(toBeSelected);
    }

    private void traverseToKey(SequenceRowKey toBeSelectedKey) {
        WebTechnologiesTreeNode parentNode = (WebTechnologiesTreeNode) treeBean.getRootNode().getChild(toBeSelectedKey.getParent().getSimpleKeys()[0]);
        WebTechnologiesTreeNode toBeSelectedNode = (WebTechnologiesTreeNode) parentNode.getChild(toBeSelectedKey.getSimpleKeys()[1]);
        parentNode.setExpanded(true);

        treeBean.getSelection().clear();
        treeBean.getSelection().add(toBeSelectedKey);
        browserBean.setUrl(((WebLinkNodeData) toBeSelectedNode.getData()).getUrl());
    }

    public BrowserBean getBrowserBean() {
        return browserBean;
    }

    public void setBrowserBean(BrowserBean browserBean) {
        this.browserBean = browserBean;
    }

    public ITree getTreeBean() {
        return treeBean;
    }

    public void setTreeBean(ITree treeBean) {
        this.treeBean = treeBean;
    }
    
    
    
}
