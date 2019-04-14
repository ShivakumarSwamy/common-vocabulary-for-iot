package de.uni.stuttgart.ipvs.sparql.update;

import de.uni.stuttgart.ipvs.sparql.prologue.Prologue;

import java.util.Collection;

public interface Update {

    Collection<Prologue> getPrologues();

    Collection<UpdateForm> getUpdateForms();

    String getString();

}
