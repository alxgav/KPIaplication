package kpiaplication.model;

import javafx.collections.ObservableList;

public interface HierarchyData <T extends HierarchyData> {
    ObservableList<T> getChildren();
}
