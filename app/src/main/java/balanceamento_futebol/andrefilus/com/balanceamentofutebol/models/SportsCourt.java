package balanceamento_futebol.andrefilus.com.balanceamentofutebol.models;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by André Filus on 11/04/2018.
 */

public class SportsCourt {
    @StringDef({Court.SOCIETY, Court.INDOOR, Court.GRASS, Court.SAND})
    @Retention(RetentionPolicy.SOURCE)
    @interface Court {
        String SOCIETY = "Society";
        String INDOOR = "Salão";
        String GRASS = "Grama";
        String SAND = "Areia";
    }

    public List<String> getPositionsOfCourt(String typeCourt) {
        switch (typeCourt) {
            case Court.INDOOR:
                return new SportsCourt().getIndoorPositions();
            case Court.GRASS:
                return new SportsCourt().getGrassPositions();
            case Court.SAND:
                return new SportsCourt().getSandPositions();
            default:
                return new SportsCourt().getSocietyPositions();
        }
    }

    public SportsCourt() {
    }

    public List<String> societyPositions = new ArrayList<>();
    public List<String> indoorPositions = new ArrayList<>();
    public List<String> grassPositions = new ArrayList<>();
    public List<String> sandPositions = new ArrayList<>();
    @Court
    public String sportsCourtTypeOfCourt;
    public String courtText;

    public List<String> getSocietyPositions() {
        societyPositions.add("Goleiro");
        societyPositions.add("Zagueiro");
        societyPositions.add("Ala");
        societyPositions.add("Meia");
        societyPositions.add("Atacante");
        return societyPositions;
    }

    public List<String> getIndoorPositions() {
        indoorPositions.add("Goleiro");
        indoorPositions.add("Fixo");
        indoorPositions.add("Ala");
        indoorPositions.add("Pivô");
        return indoorPositions;
    }

    public List<String> getGrassPositions() {
        grassPositions.add("Goleiro");
        grassPositions.add("Zagueiro");
        grassPositions.add("Lateral-direito");
        grassPositions.add("Lateral-esquerdo");
        grassPositions.add("Volante");
        grassPositions.add("Meia");
        grassPositions.add("Atacante");
        return grassPositions;
    }

    public List<String> getSandPositions() {
        sandPositions.add("Goleiro");
        sandPositions.add("Zagueiro");
        sandPositions.add("Ala");
        sandPositions.add("Meio");
        sandPositions.add("Atacante");
        return sandPositions;
    }
}
