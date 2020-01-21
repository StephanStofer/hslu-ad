/**
 * RailwayStation-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-05-21
 */
package ch.hslu.ad.exercises.sw14;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.Objects;

public class RailwayStation {

    private static final Logger LOG = LogManager.getLogger(RailwayStation.class);
    private String name;
    private Color color;

    public RailwayStation(final String name) {
        this.name = name;
        this.color = Color.WHITE;
    }

    public void setColor(final Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RailwayStation)){
            return false;
        }
        final RailwayStation rs = (RailwayStation) obj;
        return this.color == rs.color;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.color);
    }

    @Override
    public String toString() {
        return "Station: "+this.name +" , color: "+this.color;
    }
}