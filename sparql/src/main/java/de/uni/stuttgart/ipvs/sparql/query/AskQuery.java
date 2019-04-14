package de.uni.stuttgart.ipvs.sparql.query;

public class AskQuery extends AbstractQueryForm {

    @Override
    public String getString() {
        return "ASK " + super.getString();
    }
}
