/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hojnacki;

import java.util.Collection;
import org.richfaces.model.SequenceRowKey;

/**
 *
 * @author dell
 */
public interface ITree {

    WebTechnologiesTreeNode getRootNode();

    Collection<Object> getSelection();

    SequenceRowKey getSingleSelection();
    
}
