/**
 * DemoRailwayNet-Class represents
 *
 * @author stofers
 * @Version V1.0
 * @date 2019-05-23
 */
package ch.hslu.ad.exercises.sw14;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DemoRailwayNet {

    private static final Logger LOG = LogManager.getLogger(DemoRailwayNet.class);
    private static List<RailwayStation> stationArrayList = new ArrayList<>();
    private static final String[] locations = {"Olten","Zofingen","Luzern","Arth-Goldau","Pfäffikon","Zug","Rotkreuz","Lenzburg",
            "Wohlen","Aarau","Zürich","Dietikon","Brugg"};

    public DemoRailwayNet() {

    }

    public static void main(String[] args) {
        RailwayNet railwayNet = new RailwayNet();
        for (int i = 0; i < locations.length; i++){
            stationArrayList.add(new RailwayStation(locations[i]));
        }
        railwayNet.addEdge(stationArrayList.get(0),stationArrayList.get(1));
        railwayNet.addEdge(stationArrayList.get(0),stationArrayList.get(9));
        railwayNet.addEdge(stationArrayList.get(0),stationArrayList.get(10));
        railwayNet.addEdge(stationArrayList.get(1),stationArrayList.get(3));
        railwayNet.addEdge(stationArrayList.get(1),stationArrayList.get(7));
        railwayNet.addEdge(stationArrayList.get(2),stationArrayList.get(3));
        railwayNet.addEdge(stationArrayList.get(2),stationArrayList.get(6));
        railwayNet.addEdge(stationArrayList.get(2),stationArrayList.get(7));
        railwayNet.addEdge(stationArrayList.get(3),stationArrayList.get(4));
        railwayNet.addEdge(stationArrayList.get(3),stationArrayList.get(5));
        railwayNet.addEdge(stationArrayList.get(3),stationArrayList.get(6));
        railwayNet.addEdge(stationArrayList.get(4),stationArrayList.get(10));
        railwayNet.addEdge(stationArrayList.get(4),stationArrayList.get(10));
        railwayNet.addEdge(stationArrayList.get(5),stationArrayList.get(6));
        railwayNet.addEdge(stationArrayList.get(5),stationArrayList.get(10));
        railwayNet.addEdge(stationArrayList.get(6),stationArrayList.get(8));
        railwayNet.addEdge(stationArrayList.get(7),stationArrayList.get(8));
        railwayNet.addEdge(stationArrayList.get(7),stationArrayList.get(9));
        railwayNet.addEdge(stationArrayList.get(7),stationArrayList.get(10));
        railwayNet.addEdge(stationArrayList.get(7),stationArrayList.get(11));
        railwayNet.addEdge(stationArrayList.get(7),stationArrayList.get(12));
        railwayNet.addEdge(stationArrayList.get(8),stationArrayList.get(11));
        railwayNet.addEdge(stationArrayList.get(9),stationArrayList.get(12));
        railwayNet.addEdge(stationArrayList.get(10),stationArrayList.get(11));
        railwayNet.addEdge(stationArrayList.get(11),stationArrayList.get(12));

        //LOG.info(railwayNet.getAllAdjaNodes(stationArrayList.get(7)).iterator().toString());

        Iterator<RailwayStation> rs = railwayNet.getAllAdjaNodes(stationArrayList.get(7)).iterator();
        while (rs.hasNext()){
            LOG.info(rs.toString());

        }

    }
}