package com.android.loushi.loushi.jsonbean;

import java.util.List;

/**
 * @author  江镇雄
 * @date 创建时间：2016年7月22日 下午3:51:21 
 * @version 1.0 
 */
public class Area{

	private int id;
	private boolean isValid;
	private String province;
	private String city;
	private String district;
	/**
	 * body : [{"area":null,"id":1,"name":"北京大学","valid":false},{"area":null,"id":2,"name":"中国人民大学","valid":false},{"area":null,"id":3,"name":"清华大学","valid":false},{"area":null,"id":4,"name":"北京交通大学","valid":false},{"area":null,"id":5,"name":"北京科技大学","valid":false},{"area":null,"id":6,"name":"北京化工大学","valid":false},{"area":null,"id":7,"name":"北京邮电大学","valid":false},{"area":null,"id":8,"name":"中国农业大学","valid":false},{"area":null,"id":9,"name":"北京林业大学","valid":false},{"area":null,"id":10,"name":"北京中医药大学","valid":false},{"area":null,"id":11,"name":"北京师范大学","valid":false},{"area":null,"id":12,"name":"北京外国语大学","valid":false},{"area":null,"id":13,"name":"北京语言大学","valid":false},{"area":null,"id":14,"name":"中央财经大学","valid":false},{"area":null,"id":15,"name":"对外经济贸易大学","valid":false},{"area":null,"id":16,"name":"中国政法大学","valid":false},{"area":null,"id":17,"name":"华北电力大学","valid":false},{"area":null,"id":18,"name":"中国石油大学","valid":false},{"area":null,"id":19,"name":"中央民族大学","valid":false},{"area":null,"id":20,"name":"中国人民公安大学","valid":false},{"area":null,"id":21,"name":"北京协和医学院","valid":false},{"area":null,"id":22,"name":"北京体育大学","valid":false},{"area":null,"id":23,"name":"北京航空航天大学","valid":false},{"area":null,"id":24,"name":"北京理工大学","valid":false},{"area":null,"id":25,"name":"中国科学院大学","valid":false},{"area":null,"id":26,"name":"北京工业大学","valid":false},{"area":null,"id":27,"name":"北方工业大学","valid":false},{"area":null,"id":28,"name":"北京工商大学","valid":false},{"area":null,"id":29,"name":"北京建筑大学","valid":false},{"area":null,"id":30,"name":"首都医科大学","valid":false},{"area":null,"id":31,"name":"首都师范大学","valid":false},{"area":null,"id":32,"name":"首都经济贸易大学","valid":false},{"area":null,"id":33,"name":"北京信息科技大学","valid":false},{"area":null,"id":34,"name":"北京联合大学","valid":false},{"area":null,"id":35,"name":"中国传媒大学","valid":false},{"area":null,"id":36,"name":"国际关系学院","valid":false},{"area":null,"id":37,"name":"中央音乐学院","valid":false},{"area":null,"id":38,"name":"中央美术学院","valid":false},{"area":null,"id":39,"name":"中央戏剧学院","valid":false},{"area":null,"id":40,"name":"北京电子科技学院","valid":false},{"area":null,"id":41,"name":"外交学院","valid":false},{"area":null,"id":42,"name":"中国劳动关系学院","valid":false},{"area":null,"id":43,"name":"中国青年政治学院","valid":false},{"area":null,"id":44,"name":"中华女子学院","valid":false},{"area":null,"id":45,"name":"北京服装学院","valid":false},{"area":null,"id":46,"name":"北京印刷学院","valid":false},{"area":null,"id":47,"name":"北京石油化工学院","valid":false},{"area":null,"id":48,"name":"北京农学院","valid":false},{"area":null,"id":49,"name":"首都体育学院","valid":false},{"area":null,"id":50,"name":"北京第二外国语学院","valid":false},{"area":null,"id":51,"name":"北京物资学院","valid":false},{"area":null,"id":52,"name":"中国音乐学院","valid":false},{"area":null,"id":53,"name":"中国戏曲学院","valid":false},{"area":null,"id":54,"name":"北京电影学院","valid":false},{"area":null,"id":55,"name":"北京舞蹈学院","valid":false},{"area":null,"id":56,"name":"北京城市学院","valid":false},{"area":null,"id":57,"name":"首钢工学院","valid":false},{"area":null,"id":58,"name":"北京警察学院","valid":false},{"area":null,"id":59,"name":"北京青年政治学院","valid":false},{"area":null,"id":60,"name":"北京工业职业技术学院","valid":false},{"area":null,"id":61,"name":"北京信息职业技术学院","valid":false},{"area":null,"id":62,"name":"北京电子科技职业学院","valid":false},{"area":null,"id":63,"name":"北京吉利大学","valid":false},{"area":null,"id":64,"name":"北京社会管理职业学院","valid":false},{"area":null,"id":65,"name":"北京新圆明职业学院","valid":false},{"area":null,"id":66,"name":"北京体育职业学院","valid":false},{"area":null,"id":67,"name":"北京交通运输职业学院","valid":false},{"area":null,"id":68,"name":"北京卫生职业学院","valid":false},{"area":null,"id":69,"name":"北京京北职业技术学院","valid":false},{"area":null,"id":70,"name":"北京交通职业技术学院","valid":false},{"area":null,"id":71,"name":"北京农业职业学院","valid":false},{"area":null,"id":72,"name":"北京政法职业学院","valid":false},{"area":null,"id":73,"name":"北京财贸职业学院","valid":false},{"area":null,"id":74,"name":"北京北大方正软件职业技术学院","valid":false},{"area":null,"id":75,"name":"北京经贸职业学院","valid":false},{"area":null,"id":76,"name":"北京经济技术职业学院","valid":false},{"area":null,"id":77,"name":"北京戏曲艺术职业学院","valid":false},{"area":null,"id":78,"name":"北京汇佳职业学院","valid":false},{"area":null,"id":79,"name":"北京现代职业技术学院","valid":false},{"area":null,"id":80,"name":"北京科技经营管理学院","valid":false},{"area":null,"id":81,"name":"北京科技职业学院","valid":false},{"area":null,"id":82,"name":"北京培黎职业学院","valid":false},{"area":null,"id":83,"name":"北京经济管理职业学院","valid":false},{"area":null,"id":84,"name":"北京劳动保障职业学院","valid":false}]
	 * code : null
	 * return_info : null
	 * state : true
	 */

	private String code;
	private String return_info;
	private boolean state;
	/**
	 * area : null
	 * id : 1
	 * name : 北京大学
	 * valid : false
	 */

	private List<BodyBean> body;

	@Override
	public String toString() {
		return "Area{" +
				"id=" + id +
				", isValid=" + isValid +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", district='" + district + '\'' +
				'}';
	}

	public Area(){
		super();
	}
	
	public Area(int id,boolean isValid,String province,String city,String district){
		this.id = id;
		this.isValid = isValid;
		this.province = province;
		this.city = city;
		this.district = district;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReturn_info() {
		return return_info;
	}

	public void setReturn_info(String return_info) {
		this.return_info = return_info;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public List<BodyBean> getBody() {
		return body;
	}

	public void setBody(List<BodyBean> body) {
		this.body = body;
	}

	public static class BodyBean {
		private Object area;
		private int id;
		private String name;
		private boolean valid;

		public Object getArea() {
			return area;
		}

		public void setArea(Object area) {
			this.area = area;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isValid() {
			return valid;
		}

		public void setValid(boolean valid) {
			this.valid = valid;
		}
	}
}
