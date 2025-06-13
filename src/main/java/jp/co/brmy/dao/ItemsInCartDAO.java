package jp.co.brmy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.brmy.dto.ItemsDTO;
import jp.co.brmy.dto.ItemsInCartDTO;

public class ItemsInCartDAO extends BaseDAO{


	public ItemsInCartDAO(Connection conn) {
		super(conn);
	}

	public List<ItemsInCartDTO> findAll() throws SQLException{
        String sql = "select items_in_cart.user_id,items_in_cart.item_id,items_in_cart.amount,items_in_cart.booked_date,\n"
        		+ "items.name,items.manufacturer,items.category_id,items.color,items.price,items.stock,items.recommended\n"
        		+ "from items_in_cart inner join items on items_in_cart.item_id= items.item_id";
        List<ItemsInCartDTO> cartlist = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(sql)){

        	ResultSet rs = ps.executeQuery();

            while(rs.next()){
            	ItemsInCartDTO dto = new ItemsInCartDTO();
            	ItemsDTO item=new ItemsDTO();
            	
                dto.setUserId(rs.getString("user_id"));
                dto.setItemId(rs.getInt("item_id"));
                dto.setAmount(rs.getInt("amount"));
                dto.setBookedDate(rs.getDate("booked_date"));
                
                item.setCategoryId(rs.getInt("category_id"));
                item.setColor(rs.getString("color"));
              //  item.setItemId(rs.getInt("item_id"));
                item.setManufacturer(rs.getString("manufacturer"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getInt("price"));
                item.setRecommended(rs.getBoolean("recommended"));
                item.setStock(rs.getInt("stock"));
                
                dto.setItemsDTO(item);
                cartlist.add(dto);

            }

        }
        return cartlist;
 }
	
	public List<ItemsInCartDTO> findByUserId(String userid) throws SQLException{
        String sql = "select items_in_cart.user_id,items_in_cart.item_id,items_in_cart.amount,items_in_cart.booked_date,\n"
        		+ "items.name,items.manufacturer,items.category_id,items.color,items.price,items.stock,items.recommended\n"
        		+ "from items_in_cart inner join items on items_in_cart.item_id= items.item_id where user_id=?";
        List<ItemsInCartDTO> cartlist = null;
        try(PreparedStatement ps = conn.prepareStatement(sql)){
        	ps.setString(1, userid);
        	ResultSet rs = ps.executeQuery();
        	int i=0;

            while(rs.next()){
            	if(i==0) {
                	cartlist= new ArrayList<>();
                	i++;
            	}
            	
            	ItemsInCartDTO dto = new ItemsInCartDTO();
            	ItemsDTO item=new ItemsDTO();

                dto.setUserId(rs.getString("user_id"));
                dto.setItemId(rs.getInt("item_id"));
                dto.setAmount(rs.getInt("amount"));
                dto.setBookedDate(rs.getDate("booked_date"));
                
                item.setCategoryId(rs.getInt("category_id"));
                item.setColor(rs.getString("color"));
              //  item.setItemId(rs.getInt("item_id"));
                item.setManufacturer(rs.getString("manufacturer"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getInt("price"));
                item.setRecommended(rs.getBoolean("recommended"));
                item.setStock(rs.getInt("stock"));
                dto.setItemsDTO(item);

                
                cartlist.add(dto);

            }

        }
        return cartlist;
 }
	
	
	public int deleteItemsInCart(String userid) throws SQLException{
        String sql = "delete from items_in_cart where user_id=?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
        	ps.setString(1, userid);
        	int a = ps.executeUpdate();

            return a;
        }
 }
	
	public int insertItemsInCart(ItemsInCartDTO dto) throws SQLException{
        String sql = "insert into items_in_cart values(?,?,?,CURRENT_DATE)";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
        	ps.setString(1, dto.getUserId());
        	ps.setInt(2, dto.getItemId());
        	ps.setInt(3, dto.getAmount());
        	int a = ps.executeUpdate();

            return a;
        }
 }
	
}
