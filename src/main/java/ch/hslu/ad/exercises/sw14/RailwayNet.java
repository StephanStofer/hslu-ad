/**
 * RailwayNet-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-05-21
 */
package ch.hslu.ad.exercises.sw14;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RailwayNet {

    private static final Logger LOG = LogManager.getLogger(RailwayNet.class);

    private Map<RailwayStation, Set<RailwayStation>> stations;
    private boolean[][] adjaMx; // represents station-connection matrix

    public RailwayNet() {
        stations = new HashMap<>();
    }

    public boolean isStationInGraph(final RailwayStation railwayStation){
        return stations.containsKey(railwayStation);
    }

    public boolean addRailwayStation(final RailwayStation railwayStation){
        if (!isStationInGraph(railwayStation)){
            stations.put(railwayStation, new HashSet<>());
            return true;
        } else {
            return false;
        }
    }

    public boolean isEdgeInGraph(final RailwayStation from, final RailwayStation to){
        if (isStationInGraph(from)){
            return stations.get(from).contains(to);
        } else {
            return false;
        }
    }

    public boolean addEdge(final RailwayStation from, final RailwayStation to){
        if (isStationInGraph(from) && isStationInGraph(to)) {
            if (!isEdgeInGraph(from, to)) {
                stations.get(from).add(to);
                return true;
            } else {
                return false;
            }
        } else {
                return false;
        }
    }

    public int getNoOfStations(){
        return stations.size();
    }

    public Set<RailwayStation> getAllAdjaNodes(final RailwayStation railwayStation){
        if (isStationInGraph(railwayStation)){
            return stations.get(railwayStation);
        } else{
            return new HashSet<>();
        }
    }
}
