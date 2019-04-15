package de.uni.stuttgart.ipvs.results;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class Head {

    private Collection<String> vars = new ArrayList<>();

    public boolean isEmpty() {
        return vars.isEmpty();
    }
}
