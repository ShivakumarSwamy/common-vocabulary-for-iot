package de.uni.stuttgart.ipvs.results;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class Results {

    private Collection<Map<String, VariableBinding>> bindings = new ArrayList<>();

    public boolean isEmpty() {
        return bindings.isEmpty();
    }

}
