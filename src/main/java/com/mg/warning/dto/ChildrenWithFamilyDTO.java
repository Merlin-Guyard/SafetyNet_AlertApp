package com.mg.warning.dto;

import java.util.ArrayList;
import java.util.List;

public class ChildrenWithFamilyDTO {

    private List<ChildrenDTO> children = new ArrayList<>();
    private List<FamilyDTO> family = new ArrayList<>();

    public List<ChildrenDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenDTO> children) {
        this.children = children;
    }

    public List<FamilyDTO> getFamily() {
        return family;
    }

    public void setFamily(List<FamilyDTO> family) {
        this.family = family;
    }




}
