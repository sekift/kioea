package com.kioea.www.geoutil;

/**
 * 通过经纬度计算两个点的之间的距离的算法
 *
 * @author jiangmindi
 */
public class DistanceUtil {
    private static final double EARTH_RADIUS = 6378137;
    private static final double PI = 3.14159265;

    private static final double RAD = Math.PI / 180.0;

    /**
     * 根据提供的经度和纬度、以及半径，取得此半径内的最大最小经纬度
     *
     * @param lat
     * @param lon
     * @param raidus 单位米
     * @return
     */
    public static double[] getAround(double lon, double lat, int raidus) {

        Double latitude = lat;
        Double longitude = lon;

        Double degree = (24901 * 1609) / 360.0;
        double raidusMile = raidus;

        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;

        Double mpdLng = degree * Math.cos(latitude * (PI / 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;
        //System.out.println("["+minLat+","+minLng+","+maxLat+","+maxLng+"]");
        return new double[]{minLat, minLng, maxLat, maxLng};
    }

    /** */
    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     *
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     */

    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = lat1 * RAD;
        double radLat2 = lat2 * RAD;

        double a = radLat1 - radLat2;
        double b = (lng1 - lng2) * RAD;

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }


    public static void main(String[] args) {
        Double lat1 = 20.032871;//纬度
        Double lon1 = 110.31990;//经度

        int radius = 1000 * 30;
        //[34.25566276027792,108.94186385411045,34.27363323972208,108.96360814588955]
        double[] around = getAround(lon1, lat1, radius);
        Double minLat = around[0];
        Double minLng = around[1];
        Double maxLat = around[2];
        Double maxLng = around[3];
        String sql = " longitude > " + minLng + " and longitude < " + maxLng + " and latitude > " + minLat + " and latitude < " + maxLat;
        System.out.println(sql);
        //911717.0   34.264648,108.952736,39.904549,116.407288
        double dis = DistanceUtil.getDistance(108.952736, 34.264648, 116.407288, 39.904549);
        //System.out.println(dis);
    }

}
