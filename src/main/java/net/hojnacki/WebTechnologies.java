package net.hojnacki;


import org.richfaces.component.UITree;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class WebTechnologies {

    private WebTechnologiesTreeNode stationRoot;

    private WebTechnologiesTreeNode rootNodes;

    private UITree binding;

    public void setBinding(UITree binding) {
        this.binding = binding;
    }

    public UITree getBinding() {
        return binding;
    }

    public WebTechnologiesTreeNode getRootNodes() {
        if (rootNodes == null) {
            String[] kickRadioFeed = {"Hall & Oates - Kiss On My List",
                    "David Bowie - Let's Dance",
                    "Lyn Collins - Think (About It)",
                    "Kim Carnes - Bette Davis Eyes",
                    "KC & the Sunshine Band - Give It Up"};
            stationRoot = new WebTechnologiesTreeNode(false, "KickRadio");
            for (int i = 0; i<kickRadioFeed.length; i++) {
                WebTechnologiesTreeNode child = new WebTechnologiesTreeNode(true, kickRadioFeed[i]);
                stationRoot.addChild(i, child);
            }
            rootNodes = new WebTechnologiesTreeNode();
            rootNodes.addChild(0, stationRoot);
        }
        return rootNodes;
    }

    public void expandAll() {
            System.out.print(binding);
    }
}