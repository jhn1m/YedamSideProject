package domain.restaurant.vo;

import lombok.Data;

@Data
public class RestaurantVO {
	private long restaurantId;
	private long managerId;
	private String name;
	private String tel;
	private int reserve_count;
	private String location;
	private String content;
	private String operationTimeStart;
	private String operationTimeEnd;
	private String breakTimeStart;
	private String breakTimeEnd;
	private int maxPersonnel;
	private int rnum;

}
