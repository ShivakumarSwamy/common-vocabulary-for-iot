package de.uni.stuttgart.ipvs.results;

import lombok.Data;

@Data
public class SelectResults {

    private Head head = new Head();
    private Results results = new Results();

    public boolean isEmpty() {
        return this.head.isEmpty() && this.results.isEmpty();
    }

}
