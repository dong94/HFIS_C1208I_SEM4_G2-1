/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Tour;

/**
 *
 * @author Khachuong
 */
public class TourDetail extends ActionSupport {

    private List<Tour> listTour = new ArrayList<Tour>();
    private String tourID;

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public List<Tour> getListTour() {
        return listTour;
    }

    public void setListTour(List<Tour> listTour) {
        this.listTour = listTour;
    }

    @Override
    public String execute() throws Exception {
        Connection con = da.ConnectionDB.getConnection();
        String sql = "exec GetTourByID ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, tourID);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Tour t = new Tour();
            t.setTourID(rs.getInt("TourID"));
            t.setTourName(rs.getString("TourName"));
            t.setCityID(rs.getInt("CityID"));
            t.setTourDepart(rs.getDate("TourDepart"));
            t.setTourArrive(rs.getDate("TourArrive"));
            t.setCostAdults(rs.getDouble("CostAdults"));
            t.setCostChild(rs.getDouble("CostChild"));
            t.setTotalSeat(rs.getInt("TotalSeat"));
            t.setSeatAvailable(rs.getInt("SeatAvailable"));
            t.setImg(rs.getString("images"));
            t.setDescriptions(rs.getString("Descriptions"));
            t.setDates(rs.getInt("Dates"));
            listTour.add(t);
        }
        return SUCCESS;
    }

}
